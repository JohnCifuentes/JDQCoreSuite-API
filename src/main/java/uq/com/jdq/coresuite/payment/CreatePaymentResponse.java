package uq.com.jdq.coresuite.payment;

/**
 * Respuesta que el frontend usa para inicializar Wompi Checkout.
 * @param reference referencia unica de la transaccion.
 * @param amountInCents valor del plan en centavos.
 * @param currency moneda de la transaccion.
 * @param signature firma de integridad requerida por Wompi.
 */
public record CreatePaymentResponse(
        String reference,
        Long amountInCents,
        String currency,
        String signature
) {
}
