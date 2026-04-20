package uq.com.jdq.coresuite.payment;

/**
 * Contrato de negocio para la integracion de pagos con Wompi.
 */
public interface PaymentService {

    /**
     * Crea una transaccion local asociada a un plan y retorna los datos necesarios para el checkout.
     * @param request solicitud con el plan a pagar.
     * @return datos requeridos por el frontend para iniciar el checkout.
     * @throws Exception si ocurre un error de negocio.
     */
    CreatePaymentResponse createPayment(CreatePaymentRequest request) throws Exception;

    /**
     * Procesa el webhook enviado por Wompi y actualiza el estado del pago.
     * @param rawPayload cuerpo original del evento recibido.
     * @param providedSignature firma recibida por header o por payload.
     * @return resultado del procesamiento del evento.
     * @throws Exception si la firma o el contenido son invalidos.
     */
    WebhookProcessResponse processWebhook(String rawPayload, String providedSignature) throws Exception;

    /**
     * Consulta el estado actual del pago almacenado en el sistema.
     * @param reference referencia unica de la transaccion.
     * @return estado del pago.
     * @throws Exception si el pago no existe.
     */
    PaymentStatusResponse getPaymentStatus(String reference) throws Exception;

    /**
     * Sincroniza el estado local consultando directamente a Wompi.
     * @param reference referencia del pago.
     * @return estado actualizado del pago.
     * @throws Exception si la consulta no puede resolverse.
     */
    PaymentStatusResponse syncPaymentStatus(String reference) throws Exception;
}
