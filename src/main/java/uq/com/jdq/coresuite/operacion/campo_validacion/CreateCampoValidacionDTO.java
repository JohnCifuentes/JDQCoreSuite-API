package uq.com.jdq.coresuite.operacion.campo_validacion;

public record CreateCampoValidacionDTO(
    Long campoId,
    Long tipoValidacionId,
    String valor,
    Long campoReferenciaId
) {
}
