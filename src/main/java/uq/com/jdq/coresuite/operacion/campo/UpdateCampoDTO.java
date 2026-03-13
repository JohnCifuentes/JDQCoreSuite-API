package uq.com.jdq.coresuite.operacion.campo;

/**
 * DTO de entrada para la actualizacion de campos.
 */
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
