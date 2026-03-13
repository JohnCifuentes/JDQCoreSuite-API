package uq.com.jdq.coresuite.seguridad.usuario;

/**
 * DTO de entrada para confirmar un codigo de verificacion de usuario.
 */
public record UsuarioConfirmarCodigo(
        String correoElectronico,
        String codigo
) {
}
