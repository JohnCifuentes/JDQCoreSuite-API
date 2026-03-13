package uq.com.jdq.coresuite.operacion.interfaz;

/**
 * DTO de entrada para la actualizacion de interfaces.
 */
public record UpdateInterfazDTO(
    Long moduloId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
