package uq.com.jdq.coresuite.operacion.campo_validacion;

/**
 * DTO de entrada para la actualizacion de validaciones de campo.
 */
public record UpdateCampoValidacionDTO(
    Long campoId,
    Long tipoValidacionId,
    String valor,
    Long campoReferenciaId
) {
}
