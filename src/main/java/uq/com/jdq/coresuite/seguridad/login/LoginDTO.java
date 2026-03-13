package uq.com.jdq.coresuite.seguridad.login;

/**
 * DTO de entrada para autenticar un usuario en el sistema.
 */
public record LoginDTO(
        String correoElectronico,
        String password
) {
}
