package uq.com.jdq.coresuite.payment;

/**
 * Excepcion de negocio controlada para errores del flujo de pagos.
 */
public class PaymentBusinessException extends RuntimeException {

    public PaymentBusinessException(String message) {
        super(message);
    }

    public PaymentBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
