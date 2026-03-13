package uq.com.jdq.coresuite.operacion.campo_dependencia;

/**
 * DTO de entrada para la creacion de dependencias entre campos.
 */
public record CreateCampoDependenciaDTO(
    Long campoId,
    Long campoDependienteId,
    String operador,
    String valor
) {
}
