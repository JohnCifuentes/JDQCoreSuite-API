package uq.com.jdq.coresuite.seguridad.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;
import uq.com.jdq.coresuite.config.TokenDTO;

/**
 * Controlador REST para autenticacion y cierre de sesion de usuarios.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
    private final LoginService loginService;

    /**
     * Autentica un usuario y retorna el token de acceso.
     * @param loginDTO credenciales de autenticacion.
     * @return respuesta con el token generado.
     * @throws Exception si ocurre un error durante el inicio de sesion.
     */
    @PostMapping
    public ResponseEntity<RespuestaDTO<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.loginService.login(loginDTO)));
    }

    /**
     * Cierra la sesion activa de un usuario.
     * @param usuarioId identificador del usuario.
     * @return respuesta con el resultado del cierre de sesion.
     * @throws Exception si ocurre un error durante el proceso.
     */
    @PutMapping
    public ResponseEntity<RespuestaDTO<String>> cerrarSesion(@RequestBody Long usuarioId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.loginService.cerrarSesion(usuarioId)));
    }

}
