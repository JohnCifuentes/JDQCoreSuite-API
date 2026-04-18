package uq.com.jdq.coresuite.seguridad.login;

import uq.com.jdq.coresuite.config.TokenDTO;
import uq.com.jdq.coresuite.seguridad.codigo.ConfirmarUsuarioCodigoDTO;

/**
 * Contrato de negocio para autenticacion y cierre de sesion.
 */
public interface LoginService {

    /**
     * Autentica un usuario y genera su token de acceso.
     * @param loginDTO credenciales de autenticacion.
     * @return token generado para la sesion.
     * @throws Exception si ocurre un error durante la autenticacion.
     */
    String login(LoginDTO loginDTO) throws Exception;

    /**
     * Autentica un usuario y genera su token de acceso. Doble Autenticación
     * @param usuarioCodigoDTO credenciales de autenticacion.
     * @return token generado para la sesion.
     * @throws Exception si ocurre un error durante la autenticacion.
     */
    TokenDTO login2FA(ConfirmarUsuarioCodigoDTO usuarioCodigoDTO) throws Exception;

    /**
     * Cierra la sesion activa de un usuario.
     * @param usuarioId identificador del usuario.
     * @return mensaje con el resultado del cierre.
     * @throws Exception si ocurre un error durante el proceso.
     */
    String cerrarSesion(Long usuarioId) throws Exception;

}
