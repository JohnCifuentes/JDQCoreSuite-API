package uq.com.jdq.coresuite.payment;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

/**
 * Controlador REST para crear, consultar y actualizar pagos integrados con Wompi.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    /**
     * Crea una transaccion pendiente asociada a un plan.
     * @param request plan que sera pagado desde Wompi Checkout.
     * @return datos requeridos por el frontend para abrir el checkout.
     * @throws Exception si ocurre un error de negocio.
     */
    @PostMapping("/create")
    @Operation(summary = "Crear pago Wompi", description = "Genera la referencia local, la firma de integridad y deja el pago en estado PENDING")
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request) throws Exception {
        long start = System.nanoTime();
        log.info("Solicitud de creacion de pago recibida. planId={}", request != null ? request.planId() : null);
        CreatePaymentResponse response = paymentService.createPayment(request);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        log.info("Respuesta de creacion de pago enviada. reference={}, amountInCents={}, elapsedMs={}", response.reference(), response.amountInCents(), elapsedMs);
        return ResponseEntity.ok(response);
    }

    /**
     * Procesa el webhook enviado por Wompi y sincroniza el estado del pago local.
     * @param rawPayload cuerpo original del evento.
     * @param checksum firma enviada por Wompi.
     * @param wompiSignature header alternativo de firma.
     * @return resultado del procesamiento.
     * @throws Exception si la firma o el payload no son validos.
     */
    @PostMapping("/webhook")
    @Operation(summary = "Webhook de Wompi", description = "Valida la firma del evento, aplica idempotencia y actualiza el estado del pago")
    public ResponseEntity<RespuestaDTO<WebhookProcessResponse>> processWebhook(
            @RequestBody String rawPayload,
            @RequestHeader(value = "X-Event-Checksum", required = false) String checksum,
            @RequestHeader(value = "X-Wompi-Signature", required = false) String wompiSignature
    ) throws Exception {
        String signature = (checksum != null && !checksum.isBlank()) ? checksum : wompiSignature;
        return ResponseEntity.ok(new RespuestaDTO<>(false, paymentService.processWebhook(rawPayload, signature)));
    }

    /**
     * Retorna el estado actual del pago registrado en base de datos.
     * @param reference referencia unica del pago.
     * @return estado del pago.
     * @throws Exception si la referencia no existe.
     */
    @GetMapping("/{reference}")
    @Operation(summary = "Consultar pago", description = "Obtiene el estado actual del pago usando su referencia unica")
    public ResponseEntity<RespuestaDTO<PaymentStatusResponse>> getPaymentStatus(@PathVariable String reference) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, paymentService.getPaymentStatus(reference)));
    }

    /**
     * Fuerza una consulta directa a Wompi para refrescar el estado de un pago.
     * @param reference referencia unica del pago.
     * @return estado actualizado del pago.
     * @throws Exception si el pago no existe.
     */
    @GetMapping("/{reference}/sync")
    @Operation(summary = "Sincronizar pago", description = "Consulta directamente a Wompi con la llave privada para actualizar el estado local")
    public ResponseEntity<RespuestaDTO<PaymentStatusResponse>> syncPaymentStatus(@PathVariable String reference) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, paymentService.syncPaymentStatus(reference)));
    }
}
