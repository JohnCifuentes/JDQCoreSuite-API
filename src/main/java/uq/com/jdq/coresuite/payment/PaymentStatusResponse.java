package uq.com.jdq.coresuite.payment;

import java.time.LocalDateTime;

/**
 * Respuesta con el estado actual de un pago.
 * @param reference referencia unica del pago.
 * @param status estado actual persistido.
 * @param planId plan asociado.
 * @param amountInCents valor del pago en centavos.
 * @param currency moneda de la transaccion.
 * @param createdAt fecha de creacion.
 * @param updatedAt fecha de la ultima actualizacion.
 */
public record PaymentStatusResponse(
        String reference,
        PaymentStatus status,
        Long planId,
        Long amountInCents,
        String currency,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
