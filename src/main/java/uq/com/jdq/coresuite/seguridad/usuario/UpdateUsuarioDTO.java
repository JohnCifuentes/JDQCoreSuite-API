package uq.com.jdq.coresuite.seguridad.usuario;

public record UpdateUsuarioDTO(
    Long empresaId,
    Long tipoIdentificacionId,
    String numeroIdentificacion,
    String nombre1,
    String nombre2,
    String apellido1,
    String apellido2,
    String telefono,
    String correoElectronico
) {
}
