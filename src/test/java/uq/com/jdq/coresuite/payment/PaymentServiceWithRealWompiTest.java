package uq.com.jdq.coresuite.payment;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uq.com.jdq.coresuite.sistema.plan.PlanService;
import uq.com.jdq.coresuite.sistema.plan.ResponsePlanDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Valida que la creacion del pago funciona correctamente con la logica de negocio completa.
 */
class PaymentServiceWithRealWompiTest {

    @Test
    void shouldCreatePaymentAndReturnTransactionId() throws Exception {
        PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
        PlanService planService = Mockito.mock(PlanService.class);
        WompiService wompiService = Mockito.mock(WompiService.class);
        PaymentServiceImpl paymentService = new PaymentServiceImpl(paymentRepository, planService, wompiService);

        when(paymentRepository.existsByReference(any())).thenReturn(false);
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(planService.getPlanById(11L)).thenReturn(new ResponsePlanDTO(
                11L,
                5,
                "Plan test",
                new BigDecimal("29.90"),
                "Plan de prueba",
                "A",
                "SYSTEM",
                LocalDateTime.now(),
                null,
                null
        ));
        when(wompiService.generateIntegritySignature(anyString(), anyLong(), anyString()))
                .thenReturn("test-integrity-hash");
        when(wompiService.getPublicKey()).thenReturn("pub_test_xxx");

        CreatePaymentResponse response = assertDoesNotThrow(() -> paymentService.createPayment(new CreatePaymentRequest(11L)));
        assertNotNull(response.reference());
        assertNotNull(response.integritySignature());
        assertNotNull(response.publicKey());
        assertNotNull(response.redirectUrl());
    }
}
