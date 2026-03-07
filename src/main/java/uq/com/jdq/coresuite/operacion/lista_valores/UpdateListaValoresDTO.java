package uq.com.jdq.coresuite.operacion.lista_valores;

public record UpdateListaValoresDTO(
    Long empresaId,
    String nombre,
    String descripcion,
    String estado
) {
}
