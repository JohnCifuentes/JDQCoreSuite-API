package uq.com.jdq.coresuite.operacion.campo_validacion;

public record UpdateCampoValidacionDTO(
    Long campoId,
    Long tipoValidacionId,
    String valor,
    Long campoReferenciaId
) {
}
