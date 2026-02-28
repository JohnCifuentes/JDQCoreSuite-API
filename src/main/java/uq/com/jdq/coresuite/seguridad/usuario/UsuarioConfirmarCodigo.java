package uq.com.jdq.coresuite.seguridad.usuario;

public record UsuarioConfirmarCodigo(
        String correoElectronico,
        String codigo
) {
}
