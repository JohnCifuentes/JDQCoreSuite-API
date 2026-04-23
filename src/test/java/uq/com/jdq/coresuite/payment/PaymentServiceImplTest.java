package uq.com.jdq.coresuite.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uq.com.jdq.coresuite.sistema.plan.PlanService;
import uq.com.jdq.coresuite.sistema.plan.ResponsePlanDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para el flujo de pagos con Wompi.
 */
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PlanService planService;

    @Mock
    private WompiService wompiService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreatePendingPaymentFromPlan() throws Exception {
        ResponsePlanDTO plan = new ResponsePlanDTO(
                10L,
                5,
                "Plan Pro",
                new BigDecimal("15000.00"),
                "Plan mensual",
                "A",
                "SYSTEM",
                LocalDateTime.now(),
                null,
                null
        );

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(planService.getPlanById(10L)).thenReturn(plan);
        when(wompiService.generateIntegritySignature(anyString(), eq(1500000L), eq("COP")))
                .thenReturn("mock-integrity-hash");
        when(wompiService.getPublicKey()).thenReturn("pub_test_mock");

        CreatePaymentRequest request = new CreatePaymentRequest(10L);
        CreatePaymentResponse response = paymentService.createPayment(request);

        assertNotNull(response.reference());
        assertEquals(1500000L, response.amountInCents());
        assertEquals("COP", response.currency());
        assertEquals("pub_test_mock", response.publicKey());
        assertEquals("mock-integrity-hash", response.integritySignature());
        assertNotNull(response.redirectUrl());

        verify(paymentRepository, times(1)).save(paymentCaptor.capture());
        Payment saved = paymentCaptor.getValue();
        assertEquals(PaymentStatus.PENDING, saved.getStatus());
        assertEquals(10L, saved.getPlanId());
        assertEquals(1500000L, saved.getAmountInCents());
    }

    @Test
    void shouldReturnStoredPaymentStatusByReference() throws Exception {
        Payment payment = new Payment();
        payment.setReference("PAY-REF-1");
        payment.setStatus(PaymentStatus.APPROVED);
        payment.setPlanId(10L);
        payment.setAmountInCents(1500000L);

        when(paymentRepository.findByReference("PAY-REF-1")).thenReturn(Optional.of(payment));

        PaymentStatusResponse response = paymentService.getPaymentStatus("PAY-REF-1");

        assertEquals("PAY-REF-1", response.reference());
        assertEquals(PaymentStatus.APPROVED, response.status());
    }
}
