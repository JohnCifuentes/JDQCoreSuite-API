package uq.com.jdq.coresuite.seguridad.codigo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

/**
 * Controlador REST para la generacion y confirmacion de codigos de verificacion.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seguridad/codigo")
public class CodigoController {
    private final CodigoService codigoService;

    /**
     * Genera un nuevo codigo de verificacion para un usuario durante el proceso de Olvide Contraseña
     * @param codigoDTO datos necesarios para generar el codigo.
     * @return respuesta con el resultado de la generacion.
     * @throws Exception si ocurre un error durante el proceso.
     */
    @PostMapping("/generar")
    public ResponseEntity<RespuestaDTO<String>> generate(@RequestBody CreateCodigoDTO codigoDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.codigoService.generate(codigoDTO)));
    }

    /**
     * Genera un nuevo codigo de verificacion para un usuario durante el proceso de Login (DobleAutenticación)
     * @param codigoDTO datos necesarios para generar el codigo.
     * @return respuesta con el resultado de la generacion.
     * @throws Exception si ocurre un error durante el proceso.
     */
    @PostMapping("/generar/2FA")
    public ResponseEntity<RespuestaDTO<String>> generate2FA(@RequestBody CreateCodigoDTO codigoDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.codigoService.generate(codigoDTO)));
    }

    /**
     * Confirma un codigo de verificacion previamente generado.
     * @param codigoDTO datos del usuario y codigo a validar.
     * @return respuesta con el resultado de la confirmacion.
     * @throws Exception si ocurre un error durante la validacion.
     */
    @PostMapping("/confirmar")
    public ResponseEntity<RespuestaDTO<String>> confirmarCodigo(@RequestBody ConfirmarUsuarioCodigoDTO codigoDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.codigoService.confirmarCodigo(codigoDTO)));
    }

}
