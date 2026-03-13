package uq.com.jdq.coresuite.config.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uq.com.jdq.coresuite.config.RespuestaDTO;

/**
 * Define la estructura y comportamiento de class CustomExceptionHandler.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Ejecuta la operacion validarRegistroRepetido.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = RegistroRepetidoException.class)
    public ResponseEntity<RespuestaDTO<String>> validarRegistroRepetido(RegistroRepetidoException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(false, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarNoExisteException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = NoExisteException.class)
    public ResponseEntity<RespuestaDTO<String>> validarNoExisteException(NoExisteException e){
        return ResponseEntity.status(404).body(new RespuestaDTO<>(false, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarBadCredentials.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<RespuestaDTO<String>> validarBadCredentials(BadCredentialsException e){
        return ResponseEntity.status(401).body(new RespuestaDTO<>(false, e.getMessage()));
    }

    /**
     * Ejecuta la operacion validarReglasCodigoException.
     * @param e parametro de entrada.
     * @return resultado de la operacion.
     */
    @ExceptionHandler(value = ReglasCodigoException.class)
    public ResponseEntity<RespuestaDTO<String>> validarReglasCodigoException(ReglasCodigoException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(false, e.getMessage()));
    }

}
