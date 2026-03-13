package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

/**
 * DTO de entrada para la actualizacion de detalles de lista de valores.
 */
public record UpdateListaValoresDetalleDTO(
    Long listaValoresId,
    String nombre,
    String estado
) {
}
