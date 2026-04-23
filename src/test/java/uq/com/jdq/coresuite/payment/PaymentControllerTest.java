package uq.com.jdq.coresuite.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas del contrato HTTP expuesto para el frontend de pagos.
 */
class PaymentControllerTest {

    private MockMvc mockMvc;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = Mockito.mock(PaymentService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PaymentController(paymentService)).build();
    }

    @Test
    void shouldReturnWidgetPayloadForCreateEndpoint() throws Exception {
        when(paymentService.createPayment(any(CreatePaymentRequest.class)))
                .thenReturn(new CreatePaymentResponse(
                        "PAY-TEST-001",
                        1100000L,
                        "COP",
                        "pub_test_xxx",
                        "abc123integrityhash",
                        "http://localhost:4200/payment-response"
                ));

        mockMvc.perform(post("/api/payments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"planId\":11}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reference").value("PAY-TEST-001"))
                .andExpect(jsonPath("$.amountInCents").value(1100000))
                .andExpect(jsonPath("$.currency").value("COP"))
                .andExpect(jsonPath("$.publicKey").value("pub_test_xxx"))
                .andExpect(jsonPath("$.integritySignature").value("abc123integrityhash"))
                .andExpect(jsonPath("$.redirectUrl").value("http://localhost:4200/payment-response"));
    }
}
