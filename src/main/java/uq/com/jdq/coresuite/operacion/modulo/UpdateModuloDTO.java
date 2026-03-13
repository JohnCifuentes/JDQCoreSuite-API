package uq.com.jdq.coresuite.operacion.modulo;

/**
 * DTO de entrada para la actualizacion de modulos.
 */
public record UpdateModuloDTO(
    Long empresaId,
    String nombre,
    String descripcion,
    Integer indice,
    String estado
) {
}
