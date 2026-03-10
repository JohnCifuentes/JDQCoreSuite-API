package uq.com.jdq.coresuite.operacion.campo_dependencia;

public record UpdateCampoDependenciaDTO(
    Long campoId,
    Long campoDependienteId,
    String operador,
    String valor
) {
}
