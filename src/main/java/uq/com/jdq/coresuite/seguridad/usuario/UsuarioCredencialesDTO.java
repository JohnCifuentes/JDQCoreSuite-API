package uq.com.jdq.coresuite.seguridad.usuario;

/**
 * DTO de entrada con las credenciales de un usuario.
 */
public record UsuarioCredencialesDTO(
        String correoElectronico,
        String password
) {
}
