package uq.com.jdq.coresuite.config.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = RegistroRepetidoException.class)
    public ResponseEntity<String> validarRegistroRepetido(RegistroRepetidoException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(value = NoExisteException.class)
    public ResponseEntity<String> validarNoExisteException(NoExisteException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(value = ReglasCodigoException.class)
    public ResponseEntity<String> validarReglasCodigoException(ReglasCodigoException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

}
