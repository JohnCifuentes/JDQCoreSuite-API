package uq.com.jdq.coresuite.seguridad.rolusuario;

/**
 * DTO de entrada para registrar una asignacion rol-usuario.
 */
public record CreateRolUsuarioDTO(
    Long empresaId,
    Long rolId,
    Long usuarioId
) {
}
