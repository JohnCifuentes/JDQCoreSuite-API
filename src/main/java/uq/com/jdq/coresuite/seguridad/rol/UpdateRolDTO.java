package uq.com.jdq.coresuite.seguridad.rol;

/**
 * DTO de entrada para actualizar un rol.
 */
public record UpdateRolDTO(
    Long empresaId,
    String nombre,
    String descripcion
) {
}
