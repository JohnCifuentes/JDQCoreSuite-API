package uq.com.jdq.coresuite.payment;

import java.util.Locale;

/**
 * Estados soportados para el flujo de pagos.
 */
public enum PaymentStatus {
    PENDING,
    APPROVED,
    DECLINED,
    ERROR;

    /**
     * Convierte el estado recibido desde Wompi al enum interno del sistema.
     * @param wompiStatus estado recibido desde Wompi.
     * @return estado normalizado del pago.
     */
    public static PaymentStatus fromWompiStatus(String wompiStatus) {
        if (wompiStatus == null || wompiStatus.isBlank()) {
            return ERROR;
        }

        return switch (wompiStatus.trim().toUpperCase(Locale.ROOT)) {
            case "APPROVED" -> APPROVED;
            case "DECLINED", "VOIDED", "FAILED" -> DECLINED;
            case "PENDING", "CREATED", "PENDING_VALIDATION" -> PENDING;
            default -> ERROR;
        };
    }

    /**
     * Indica si el pago ya se encuentra en un estado terminal.
     * @return true si el estado no deberia cambiar por eventos repetidos.
     */
    public boolean isTerminal() {
        return this == APPROVED || this == DECLINED || this == ERROR;
    }
}
