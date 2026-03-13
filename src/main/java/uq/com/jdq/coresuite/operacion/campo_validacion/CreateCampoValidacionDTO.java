package uq.com.jdq.coresuite.operacion.campo_validacion;

/**
 * DTO de entrada para la creacion de validaciones de campo.
 */
public record CreateCampoValidacionDTO(
    Long campoId,
    Long tipoValidacionId,
    String valor,
    Long campoReferenciaId
) {
}
