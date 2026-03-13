package uq.com.jdq.coresuite.operacion.lista_valores;

/**
 * DTO de entrada para la creacion de listas de valores.
 */
public record CreateListaValoresDTO(
    Long empresaId,
    String nombre,
    String descripcion
) {
}
