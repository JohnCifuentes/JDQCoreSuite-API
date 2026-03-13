package uq.com.jdq.coresuite.seguridad.codigo;

/**
 * DTO de entrada para confirmar un codigo de verificacion de usuario.
 */
public record ConfirmarUsuarioCodigoDTO(
        String correoElectronico,
        String codigo
) {
}
