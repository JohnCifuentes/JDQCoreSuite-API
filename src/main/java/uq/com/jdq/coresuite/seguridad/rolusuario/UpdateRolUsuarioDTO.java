package uq.com.jdq.coresuite.seguridad.rolusuario;


/**
 * DTO de entrada para actualizar una asignacion rol-usuario.
 */
public record UpdateRolUsuarioDTO(
    Long empresaId,
    Long rolId,
    Long usuarioId
) {
}
