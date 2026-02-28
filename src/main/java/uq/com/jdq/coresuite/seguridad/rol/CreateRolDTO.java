package uq.com.jdq.coresuite.seguridad.rol;

public record CreateRolDTO(
    Long empresaId,
    String nombre,
    String descripcion
) {
}
