package uq.com.jdq.coresuite.operacion.interfaz;

public record CreateInterfazDTO(
    Long moduloId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
