package uq.com.jdq.coresuite.operacion.modulo;

public record UpdateModuloDTO(
    Long empresaId,
    String nombre,
    String descripcion,
    Integer indice,
    String estado
) {
}
