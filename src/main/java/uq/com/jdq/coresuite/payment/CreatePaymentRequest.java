package uq.com.jdq.coresuite.payment;

import jakarta.validation.constraints.NotNull;

/**
 * Solicitud para iniciar un pago asociado a un plan.
 * @param planId identificador del plan a pagar.
 */
public record CreatePaymentRequest(
        @NotNull(message = "El planId es obligatorio")
        Long planId
) {
}
