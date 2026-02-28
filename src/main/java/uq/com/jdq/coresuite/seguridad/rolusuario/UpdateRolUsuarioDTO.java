package uq.com.jdq.coresuite.seguridad.rolusuario;


public record UpdateRolUsuarioDTO(
    Long empresaId,
    Long rolId,
    Long usuarioId
) {
}
