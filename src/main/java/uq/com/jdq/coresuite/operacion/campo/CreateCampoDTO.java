package uq.com.jdq.coresuite.operacion.campo;

/**
 * DTO de entrada para la creacion de campos.
 */
public record CreateCampoDTO(
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
