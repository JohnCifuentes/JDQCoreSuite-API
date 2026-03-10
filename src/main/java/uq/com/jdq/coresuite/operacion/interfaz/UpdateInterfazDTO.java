package uq.com.jdq.coresuite.operacion.interfaz;

public record UpdateInterfazDTO(
    Long moduloId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
