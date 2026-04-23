package uq.com.jdq.coresuite.payment;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.sistema.plan.PlanService;
import uq.com.jdq.coresuite.sistema.plan.ResponsePlanDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementacion del flujo de pagos con persistencia local e integracion con Wompi.
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private static final String DEFAULT_CURRENCY = "COP";

    private final PaymentRepository paymentRepository;
    private final PlanService planService;
    private final WompiService wompiService;

    @Value("${wompi.redirect-url:http://localhost:4200/payment-response}")
    private String redirectUrlBase;

    /**
     * Crea el pago local y retorna la informacion necesaria para el checkout.
     * @param request plan a pagar.
     * @return datos requeridos por Wompi Checkout.
     * @throws Exception si el plan no existe o no es valido.
     */
    @Override
    @Transactional
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) throws Exception {
        if (request == null || request.planId() == null) {
            throw new PaymentBusinessException("No fue posible iniciar el pago porque el plan es obligatorio.");
        }

        log.info("Iniciando creacion de pago para planId={}", request.planId());
        ResponsePlanDTO plan = planService.getPlanById(request.planId());
        if (plan.valor() == null || plan.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentBusinessException("No fue posible iniciar el pago porque el valor del plan es invalido.");
        }

        String reference = generateUniqueReference();
        Long amountInCents = toAmountInCents(plan.valor());

        Payment payment = new Payment();
        payment.setReference(reference);
        payment.setPlanId(plan.id());
        payment.setAmountInCents(amountInCents);
        payment.setCurrency(DEFAULT_CURRENCY);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setStatusMessage("Transaccion pendiente de confirmacion por Wompi Widget.");
        paymentRepository.save(payment);

        String integritySignature = wompiService.generateIntegritySignature(reference, amountInCents, DEFAULT_CURRENCY);
        log.info("Pago creado para widget. reference={}, planId={}, amountInCents={}", reference, plan.id(), amountInCents);

        return new CreatePaymentResponse(reference, amountInCents, DEFAULT_CURRENCY, wompiService.getPublicKey(), integritySignature, redirectUrlBase);
    }

    /**
     * Procesa el webhook de Wompi con control de firma e idempotencia.
     * @param rawPayload cuerpo recibido desde Wompi.
     * @param providedSignature firma recibida en headers o payload.
     * @return resultado del procesamiento.
     * @throws Exception si el payload es invalido o la firma falla.
     */
    @Override
    @Transactional
    public WebhookProcessResponse processWebhook(String rawPayload, String providedSignature) throws Exception {
        wompiService.validateEventSignature(rawPayload, providedSignature);

        JsonNode payload = wompiService.readPayload(rawPayload);
        String reference = readFirstNonBlank(
                payload.path("data").path("transaction").path("reference").asText(null),
                payload.path("data").path("reference").asText(null),
                payload.path("reference").asText(null)
        );
        String statusText = readFirstNonBlank(
                payload.path("data").path("transaction").path("status").asText(null),
                payload.path("data").path("status").asText(null),
                payload.path("status").asText(null)
        );
        String wompiTransactionId = readFirstNonBlank(
                payload.path("data").path("transaction").path("id").asText(null),
                payload.path("data").path("id").asText(null),
                payload.path("id").asText(null)
        );

        if (reference == null || reference.isBlank()) {
            throw new IllegalArgumentException("El webhook recibido no contiene una referencia valida.");
        }

        PaymentStatus newStatus = PaymentStatus.fromWompiStatus(statusText);
        Optional<Payment> paymentOptional = paymentRepository.findByReference(reference);
        if (paymentOptional.isEmpty()) {
            log.warn("Se recibio un webhook para una referencia no registrada: {}", reference);
            return new WebhookProcessResponse("Referencia no registrada en el sistema.", false, reference, newStatus);
        }

        Payment payment = paymentOptional.get();
        if (payment.getStatus() == newStatus) {
            log.info("Webhook repetido ignorado por idempotencia. reference={}, status={}", reference, newStatus);
            return new WebhookProcessResponse("Evento repetido ignorado.", false, reference, payment.getStatus());
        }

        if (payment.getStatus() != null && payment.getStatus().isTerminal()) {
            log.info("Se ignora el webhook por llegar despues de un estado terminal. reference={}, currentStatus={}, incomingStatus={}",
                    reference, payment.getStatus(), newStatus);
            return new WebhookProcessResponse("El pago ya habia sido finalizado previamente.", false, reference, payment.getStatus());
        }

        payment.setStatus(newStatus);
        payment.setWompiTransactionId(wompiTransactionId);
        payment.setStatusMessage("Estado actualizado por webhook Wompi a " + newStatus);
        payment.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        log.info("Pago actualizado por webhook. reference={}, status={}, wompiTransactionId={}", reference, newStatus, wompiTransactionId);
        return new WebhookProcessResponse("Webhook procesado correctamente.", true, reference, newStatus);
    }

    /**
     * Consulta el estado local de un pago por referencia.
     * @param reference referencia del pago.
     * @return informacion del pago.
     * @throws Exception si la referencia no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public PaymentStatusResponse getPaymentStatus(String reference) throws Exception {
        Payment payment = paymentRepository.findByReference(reference)
                .orElseThrow(() -> new NoExisteException("No existe un pago con la referencia indicada."));
        return mapToStatusResponse(payment);
    }

    /**
     * Sincroniza el estado local haciendo una consulta remota a Wompi.
     * @param reference referencia del pago.
     * @return estado actualizado del pago.
     * @throws Exception si la referencia local no existe.
     */
    @Override
    @Transactional
    public PaymentStatusResponse syncPaymentStatus(String reference) throws Exception {
        Payment payment = paymentRepository.findByReference(reference)
                .orElseThrow(() -> new NoExisteException("No existe un pago con la referencia indicada."));

        wompiService.queryTransactionByReference(reference)
                .ifPresent(transaction -> {
                    if (payment.getStatus() == null || !payment.getStatus().isTerminal()) {
                        payment.setStatus(transaction.status());
                        payment.setWompiTransactionId(transaction.transactionId());
                        payment.setStatusMessage("Estado sincronizado directamente desde Wompi.");
                        paymentRepository.save(payment);
                        log.info("Pago sincronizado con Wompi. reference={}, status={}", reference, transaction.status());
                    }
                });

        return mapToStatusResponse(payment);
    }

    /**
     * Cancela un pago pendiente cuando el usuario cierra el widget sin completar la transaccion.
     * @param reference referencia unica del pago.
     * @return estado actualizado del pago.
     * @throws Exception si el pago no existe o ya se encuentra en estado terminal.
     */
    @Override
    @Transactional
    public PaymentStatusResponse cancelPayment(String reference) throws Exception {
        Payment payment = paymentRepository.findByReference(reference)
                .orElseThrow(() -> new NoExisteException("No existe un pago con la referencia indicada."));

        if (payment.getStatus() != null && payment.getStatus().isTerminal()) {
            throw new PaymentBusinessException(
                    "No es posible cancelar el pago porque ya se encuentra en estado " + payment.getStatus() + ".");
        }

        payment.setStatus(PaymentStatus.CANCELLED);
        payment.setStatusMessage("Pago cancelado por el usuario al cerrar el widget sin completar la transaccion.");
        paymentRepository.save(payment);

        log.info("Pago cancelado por el usuario. reference={}", reference);
        return mapToStatusResponse(payment);
    }

    private PaymentStatusResponse mapToStatusResponse(Payment payment) {
        return new PaymentStatusResponse(
                payment.getReference(),
                payment.getStatus(),
                payment.getPlanId(),
                payment.getAmountInCents(),
                payment.getCurrency(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }

    private Long toAmountInCents(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP)
                .longValueExact();
    }

    private String generateUniqueReference() {
        String reference;
        do {
            reference = UUID.randomUUID().toString().replace("-", "");
        } while (paymentRepository.existsByReference(reference));
        return reference;
    }

    private String readFirstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }
}
