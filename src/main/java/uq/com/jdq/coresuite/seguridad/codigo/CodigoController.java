package uq.com.jdq.coresuite.seguridad.codigo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/seguridad/codigo")
public class CodigoController {
    private final CodigoService codigoService;

    @PostMapping("/generar")
    void generate(CreateCodigoDTO codigoDTO) throws Exception{
        this.codigoService.generate(codigoDTO);
    };

    @PostMapping("/confirmar")
    boolean confirmarCodigo(ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception{
        return this.codigoService.confirmarCodigo(codigoDTO);
    };

}
