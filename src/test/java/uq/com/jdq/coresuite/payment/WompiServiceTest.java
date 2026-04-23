package uq.com.jdq.coresuite.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la firma e integridad de Wompi.
 */
class WompiServiceTest {

    private WompiService wompiService;

    @BeforeEach
    void setUp() {
        wompiService = new WompiService(
                "pub_test_123",
                "prv_test_123",
                "test_integrity_secret",
                "test_events_secret",
                "https://sandbox.wompi.co/v1"
        );
    }

    @Test
    void shouldGenerateIntegritySignature() {
        String signature = wompiService.generateIntegritySignature("REF-001", 1500000L, "COP");

        assertEquals("39b288784feb5a76cbe0b953fffa2f30c1efe2a4468b5b07d5b79aef55a24b26", signature);
    }

    @Test
    void shouldValidateEventSignature() {
        String payload = "{\"event\":\"transaction.updated\"}";
        String signature = wompiService.signWebhookPayload(payload);

        assertTrue(wompiService.isValidEventSignature(payload, signature));
        assertFalse(wompiService.isValidEventSignature(payload, "invalid-signature"));
    }
}
