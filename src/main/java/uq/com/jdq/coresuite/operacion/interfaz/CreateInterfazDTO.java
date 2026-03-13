package uq.com.jdq.coresuite.operacion.interfaz;

/**
 * DTO de entrada para la creacion de interfaces.
 */
public record CreateInterfazDTO(
    Long moduloId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
