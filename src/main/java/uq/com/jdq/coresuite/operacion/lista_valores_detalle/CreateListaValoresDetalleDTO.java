package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

/**
 * DTO de entrada para la creacion de detalles de lista de valores.
 */
public record CreateListaValoresDetalleDTO(
    Long listaValoresId,
    String nombre
) {
}
