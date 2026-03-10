package uq.com.jdq.coresuite.operacion.campo;

public record UpdateCampoDTO(
    Long interfazId,
    Long interfaceGrupoCamposId,
    Long tipoCampoId,
    Long listaValoresId,
    String nombre,
    String etiqueta,
    String descripcion,
    Integer indice,
    Integer columnas,
    String valorDefecto
) {
}
