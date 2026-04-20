package uq.com.jdq.coresuite.payment;

/**
 * Respuesta de confirmacion para el procesamiento de webhooks.
 * @param message resultado del procesamiento.
 * @param processed indica si el evento produjo un cambio.
 * @param reference referencia del pago procesado.
 * @param status estado final identificado.
 */
public record WebhookProcessResponse(
        String message,
        boolean processed,
        String reference,
        PaymentStatus status
) {
}
