package uq.com.jdq.coresuite.config.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uq.com.jdq.coresuite.config.RespuestaDTO;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RegistroRepetidoException.class)
    public ResponseEntity<RespuestaDTO<String>> validarRegistroRepetido(RegistroRepetidoException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(false, e.getMessage()));
    }

    @ExceptionHandler(value = NoExisteException.class)
    public ResponseEntity<RespuestaDTO<String>> validarNoExisteException(NoExisteException e){
        return ResponseEntity.status(404).body(new RespuestaDTO<>(false, e.getMessage()));
    }

    @ExceptionHandler(value = ReglasCodigoException.class)
    public ResponseEntity<RespuestaDTO<String>> validarReglasCodigoException(ReglasCodigoException e){
        return ResponseEntity.status(409).body(new RespuestaDTO<>(false, e.getMessage()));
    }

}
