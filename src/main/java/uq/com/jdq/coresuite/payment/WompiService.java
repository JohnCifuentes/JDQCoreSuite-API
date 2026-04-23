package uq.com.jdq.coresuite.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio de integracion con Wompi para firma, validacion de eventos y consultas remotas.
 */
@Service
public class WompiService {

    private static final Logger log = LoggerFactory.getLogger(WompiService.class);
    private static final String DEFAULT_CURRENCY = "COP";

    private final String publicKey;
    private final String privateKey;
    private final String integritySecret;
    private final String eventsSecret;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;
    private final RestClient transactionRestClient;

    public WompiService(
            @Value("${wompi.public-key:}") String publicKey,
            @Value("${wompi.private-key:}") String privateKey,
            @Value("${wompi.integrity-secret:}") String integritySecret,
            @Value("${wompi.events-secret:}") String eventsSecret,
            @Value("${wompi.api-base-url:https://sandbox.wompi.co/v1}") String apiBaseUrl
    ) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.integritySecret = integritySecret;
        this.eventsSecret = eventsSecret;
        this.objectMapper = new ObjectMapper();
        this.restClient = RestClient.builder()
                .baseUrl("https://production.wompi.co/v1")
                .build();
        this.transactionRestClient = RestClient.builder()
                .baseUrl(apiBaseUrl)
                .build();
    }

    /**
     * Retorna la llave publica del comercio configurada para el widget.
     * @return llave publica de Wompi.
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Genera la firma de integridad requerida por Wompi Checkout.
     * Formula: SHA256(reference + amountInCents + currency + integritySecret)
     * @param reference referencia unica de pago.
     * @param amountInCents valor en centavos.
     * @param currency moneda de la transaccion.
     * @return firma de integridad en formato hexadecimal.
     */
    public String generateIntegritySignature(String reference, Long amountInCents, String currency) {
        if (reference == null || reference.isBlank()) {
            throw new IllegalArgumentException("La referencia del pago es obligatoria para generar la firma de integridad.");
        }
        if (amountInCents == null || amountInCents <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor que cero.");
        }
        String cleanIntegritySecret = integritySecret != null ? integritySecret.trim() : null;
        if (cleanIntegritySecret == null || cleanIntegritySecret.isBlank()) {
            log.error("No fue posible generar la firma Wompi porque WOMPI_INTEGRITY_SECRET no esta configurada.");
            throw new PaymentBusinessException("No fue posible iniciar el pago por una configuracion incompleta del sistema.");
        }
        String safeCurrency = (currency == null || currency.isBlank()) ? DEFAULT_CURRENCY : currency.trim();
        log.debug("Integrity secret length: {}", cleanIntegritySecret.length());
        if (publicKey != null && publicKey.startsWith("pub_test_") && !cleanIntegritySecret.startsWith("test_")) {
            log.warn("ATENCION: posible mezcla de ambientes. publicKey es de prueba (pub_test_) pero integritySecret no comienza con 'test_'. Verificar configuracion.");
        }
        log.info("Generando firma de integridad Wompi. reference={}, amountInCents={}, currency={}, integritySecretConfigured=true", reference, amountInCents, safeCurrency);
        String signature = sha256Hex(reference.trim() + amountInCents + safeCurrency + cleanIntegritySecret);
        log.info("Wompi debug -> reference: {}, amount: {}, currency: {}, signature: {}", reference, amountInCents, safeCurrency, signature);
        return signature;
    }

    /**
     * Firma el payload del webhook para pruebas locales y validacion defensiva.
     * @param payload cuerpo original recibido desde Wompi.
     * @return hash HMAC-SHA256 del payload.
     */
    public String signWebhookPayload(String payload) {
        if (eventsSecret == null || eventsSecret.isBlank()) {
            throw new IllegalStateException("La variable WOMPI_EVENTS_SECRET no se encuentra configurada.");
        }
        return hmacSha256Hex(payload == null ? "" : payload, eventsSecret);
    }

    /**
     * Valida la firma del webhook usando el secreto de eventos configurado.
     * Se soporta comparacion por HMAC-SHA256 y por SHA256(payload + secret) para mayor compatibilidad.
     * @param payload cuerpo original recibido.
     * @param providedSignature firma recibida.
     * @return true si la firma coincide.
     */
    public boolean isValidEventSignature(String payload, String providedSignature) {
        if (providedSignature == null || providedSignature.isBlank() || eventsSecret == null || eventsSecret.isBlank()) {
            return false;
        }

        String normalized = normalizeSignature(providedSignature);
        String hmacSignature = signWebhookPayload(payload == null ? "" : payload);
        String shaSignature = sha256Hex((payload == null ? "" : payload) + eventsSecret);

        return normalized.equalsIgnoreCase(hmacSignature) || normalized.equalsIgnoreCase(shaSignature);
    }

    /**
     * Lanza una excepcion si el evento recibido no pasa la verificacion de firma.
     * @param payload cuerpo original del request.
     * @param providedSignature firma recibida por header.
     * @throws Exception si la firma no es valida.
     */
    public void validateEventSignature(String payload, String providedSignature) throws Exception {
        String signatureToValidate = providedSignature;
        if (signatureToValidate == null || signatureToValidate.isBlank()) {
            signatureToValidate = extractSignatureFromPayload(payload).orElse(null);
        }

        if (!isValidEventSignature(payload, signatureToValidate)) {
            throw new IllegalArgumentException("La firma del webhook de Wompi no es valida.");
        }
    }

    /**
     * Crea una transaccion directamente en la API de Wompi.
     * @param reference referencia unica del pago.
     * @param amountInCents valor del pago en centavos.
     * @param currency moneda de la transaccion.
     * @return respuesta de Wompi con id y estado inicial de la transaccion.
     * @throws PaymentBusinessException si la llave privada no esta configurada o Wompi retorna error.
     */
    public WompiTransactionResponse createTransaction(String reference, Long amountInCents, String currency) {
        if (privateKey == null || privateKey.isBlank()) {
            throw new PaymentBusinessException("No fue posible crear la transaccion porque WOMPI_PRIVATE_KEY no esta configurada.");
        }
        String safeCurrency = (currency == null || currency.isBlank()) ? DEFAULT_CURRENCY : currency.trim();
        log.info("Creando transaccion en Wompi. reference={}, amountInCents={}, currency={}", reference, amountInCents, safeCurrency);

        try {
            Map<String, Object> requestBody = new LinkedHashMap<>();
            requestBody.put("amount_in_cents", amountInCents);
            requestBody.put("currency", safeCurrency);
            requestBody.put("reference", reference);
            requestBody.put("customer_email", "test@test.com");

            String body = objectMapper.writeValueAsString(requestBody);
            log.debug("Request body enviado a Wompi: {}", body);

            JsonNode response = transactionRestClient.post()
                    .uri("/transactions")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + privateKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(JsonNode.class);

            if (response == null) {
                throw new PaymentBusinessException("Wompi no retorno respuesta al crear la transaccion.");
            }

            JsonNode data = response.path("data");
            String transactionId = textOf(data, "id");
            String status = textOf(data, "status");

            log.info("Transaccion creada en Wompi. reference={}, transactionId={}, status={}", reference, transactionId, status);
            return new WompiTransactionResponse(transactionId, status);
        } catch (PaymentBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al crear transaccion en Wompi. reference={}", reference, e);
            throw new PaymentBusinessException("No fue posible conectar con Wompi para crear la transaccion. Intente mas tarde.");
        }
    }

    /**
     * Consulta directamente a Wompi el estado de una transaccion por referencia.
     * @param reference referencia unica del pago.
     * @return resultado de la transaccion si pudo obtenerse.
     */
    public Optional<WompiTransactionResult> queryTransactionByReference(String reference) {
        if (reference == null || reference.isBlank()) {
            return Optional.empty();
        }
        if (privateKey == null || privateKey.isBlank()) {
            log.warn("No se consulto Wompi porque WOMPI_PRIVATE_KEY no esta configurada.");
            return Optional.empty();
        }

        try {
            JsonNode response = restClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/transactions").queryParam("reference", reference).build())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + privateKey)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(JsonNode.class);

            if (response == null) {
                return Optional.empty();
            }

            JsonNode data = response.path("data");
            JsonNode transactionNode = data.isArray() && !data.isEmpty() ? data.get(0) : data;
            if (transactionNode == null || transactionNode.isMissingNode() || transactionNode.isNull()) {
                return Optional.empty();
            }

            String transactionId = textOf(transactionNode, "id");
            String status = textOf(transactionNode, "status");
            String txReference = textOf(transactionNode, "reference");

            if (txReference == null || txReference.isBlank()) {
                txReference = reference;
            }

            return Optional.of(new WompiTransactionResult(transactionId, PaymentStatus.fromWompiStatus(status), txReference));
        } catch (Exception e) {
            log.error("Error consultando el estado del pago {} en Wompi.", reference, e);
            return Optional.empty();
        }
    }

    /**
     * Obtiene el arbol JSON del payload recibido desde Wompi.
     * @param payload texto bruto del webhook.
     * @return nodo raiz del JSON.
     * @throws Exception si el cuerpo no es JSON valido.
     */
    public JsonNode readPayload(String payload) throws Exception {
        return objectMapper.readTree(payload);
    }

    /**
     * Resultado minimo de una transaccion consultada a Wompi.
     * @param transactionId identificador remoto.
     * @param status estado mapeado al enum interno.
     * @param reference referencia local.
     */
    public record WompiTransactionResult(String transactionId, PaymentStatus status, String reference) {
    }

    /**
     * Respuesta de la API de Wompi al crear una transaccion.
     * @param id identificador de transaccion asignado por Wompi.
     * @param status estado inicial de la transaccion.
     */
    public record WompiTransactionResponse(String id, String status) {
    }

    private Optional<String> extractSignatureFromPayload(String payload) {
        try {
            JsonNode root = objectMapper.readTree(payload);
            String checksum = textAt(root, "signature", "checksum");
            return Optional.ofNullable(checksum);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private String normalizeSignature(String signature) {
        String trimmed = signature.trim();
        if (trimmed.startsWith("sha256=")) {
            return trimmed.substring("sha256=".length());
        }
        return trimmed;
    }

    private String textOf(JsonNode node, String field) {
        JsonNode value = node.path(field);
        return value.isMissingNode() || value.isNull() ? null : value.asText(null);
    }

    private String textAt(JsonNode root, String... path) {
        JsonNode current = root;
        for (String segment : path) {
            current = current.path(segment);
        }
        return current.isMissingNode() || current.isNull() ? null : current.asText(null);
    }

    private String sha256Hex(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new IllegalStateException("No fue posible generar el hash SHA-256.", e);
        }
    }

    private String hmacSha256Hex(String value, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            return HexFormat.of().formatHex(mac.doFinal(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new IllegalStateException("No fue posible validar la firma del webhook.", e);
        }
    }
}
