package uq.com.jdq.coresuite.config.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uq.com.jdq.coresuite.config.RespuestaDTO;
import uq.com.jdq.coresuite.payment.PaymentBusinessException;

/**
 * Define la estructura y comportamiento de class CustomExceptionHandler.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * Ejecuta la operacion validarRegistroRepetido.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = RegistroRepetidoException.class)
    public ResponseEntity<RespuestaDTO<String>> validarRegistroRepetido(RegistroRepetidoException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarNoExisteException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = NoExisteException.class)
    public ResponseEntity<RespuestaDTO<String>> validarNoExisteException(NoExisteException e){
        return ResponseEntity.status(404).body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarBadCredentials.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<RespuestaDTO<String>> validarBadCredentials(BadCredentialsException e){
        return ResponseEntity.status(401).body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarReglasCodigoException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = ReglasCodigoException.class)
    public ResponseEntity<RespuestaDTO<String>> validarReglasCodigoException(ReglasCodigoException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarIllegalArgumentException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<RespuestaDTO<String>> validarIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarPaymentBusinessException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = PaymentBusinessException.class)
    public ResponseEntity<RespuestaDTO<String>> validarPaymentBusinessException(PaymentBusinessException e){
        log.warn("Error de negocio en el flujo de pagos: {}", e.getMessage());
        return ResponseEntity.status(409).body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarIllegalStateException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<RespuestaDTO<String>> validarIllegalStateException(IllegalStateException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(true, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RespuestaDTO<String>> validarException(Exception e){
        log.error("Se produjo un error no controlado en la API.", e);
        return ResponseEntity.status(500).body(new RespuestaDTO<>(true, "Ocurrio un error interno en el servidor."));
    }

}
