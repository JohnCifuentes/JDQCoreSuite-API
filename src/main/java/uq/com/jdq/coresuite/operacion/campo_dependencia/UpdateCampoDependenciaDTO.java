package uq.com.jdq.coresuite.operacion.campo_dependencia;

/**
 * DTO de entrada para la actualizacion de dependencias entre campos.
 */
public record UpdateCampoDependenciaDTO(
    Long campoId,
    Long campoDependienteId,
    String operador,
    String valor
) {
}
