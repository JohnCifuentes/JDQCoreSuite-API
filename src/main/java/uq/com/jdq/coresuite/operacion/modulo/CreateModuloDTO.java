package uq.com.jdq.coresuite.operacion.modulo;

/**
 * DTO de entrada para la creacion de modulos.
 */
public record CreateModuloDTO(
    Long empresaId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
