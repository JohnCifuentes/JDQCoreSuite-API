package uq.com.jdq.coresuite.operacion.lista_valores;

/**
 * DTO de entrada para la actualizacion de listas de valores.
 */
public record UpdateListaValoresDTO(
    Long empresaId,
    String nombre,
    String descripcion,
    String estado
) {
}
