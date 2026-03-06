package uq.com.jdq.coresuite.seguridad.codigo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seguridad/codigo")
public class CodigoController {
    private final CodigoService codigoService;

    @PostMapping("/generar")
    public ResponseEntity<RespuestaDTO<String>> generate(CreateCodigoDTO codigoDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.codigoService.generate(codigoDTO)));
    };

    @PostMapping("/confirmar")
    public ResponseEntity<RespuestaDTO<String>> confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.codigoService.confirmarCodigo(codigoDTO)));
    };

}
