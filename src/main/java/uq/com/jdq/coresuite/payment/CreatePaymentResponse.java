package uq.com.jdq.coresuite.payment;

/**
 * Respuesta que el frontend recibe al crear un pago.
 * Contiene los datos necesarios para inicializar el Widget de Wompi.
 * @param reference referencia unica del pago generada por el backend.
 * @param amountInCents monto del pago en centavos.
 * @param currency moneda de la transaccion.
 * @param publicKey llave publica del comercio requerida por el widget.
 * @param integritySignature firma de integridad SHA256 generada en el servidor.
 * @param redirectUrl URL base del frontend a la que Wompi redirigira al finalizar el pago.
 *                    El frontend debe agregar el query param {@code ref} con la referencia antes de asignarla al widget.
 */
public record CreatePaymentResponse(
        String reference,
        Long amountInCents,
        String currency,
        String publicKey,
        String integritySignature,
        String redirectUrl
) {
}
