package uq.com.jdq.coresuite.seguridad.rol;

public record UpdateRolDTO(
    Long empresaId,
    String nombre,
    String descripcion
) {
}
