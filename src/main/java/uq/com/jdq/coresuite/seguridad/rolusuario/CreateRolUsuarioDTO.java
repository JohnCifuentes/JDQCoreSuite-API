package uq.com.jdq.coresuite.seguridad.rolusuario;

public record CreateRolUsuarioDTO(
    Long empresaId,
    Long rolId,
    Long usuarioId
) {
}
