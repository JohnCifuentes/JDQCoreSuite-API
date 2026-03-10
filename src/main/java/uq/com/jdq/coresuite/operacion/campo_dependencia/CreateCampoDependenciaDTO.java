package uq.com.jdq.coresuite.operacion.campo_dependencia;

public record CreateCampoDependenciaDTO(
    Long campoId,
    Long campoDependienteId,
    String operador,
    String valor
) {
}
