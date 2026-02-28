package uq.com.jdq.coresuite.seguridad.codigo;

public record ConfirmarUsuarioCodigoDTO(
        String correoElectronico,
        String codigo
) {
}
