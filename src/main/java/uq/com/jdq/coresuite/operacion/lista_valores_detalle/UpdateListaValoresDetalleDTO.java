package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

public record UpdateListaValoresDetalleDTO(
    Long listaValoresId,
    String nombre,
    String estado
) {
}
