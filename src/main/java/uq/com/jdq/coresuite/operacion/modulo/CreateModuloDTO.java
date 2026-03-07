package uq.com.jdq.coresuite.operacion.modulo;

public record CreateModuloDTO(
    Long empresaId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
