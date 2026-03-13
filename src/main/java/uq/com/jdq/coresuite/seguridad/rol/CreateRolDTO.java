package uq.com.jdq.coresuite.seguridad.rol;

/**
 * DTO de entrada para registrar un rol.
 */
public record CreateRolDTO(
    Long empresaId,
    String nombre,
    String descripcion
) {
}
