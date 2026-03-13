package uq.com.jdq.coresuite.operacion.tipo_campo;

/**
 * DTO de entrada para la actualizacion de tipos de campo.
 */
public record UpdateTipoCampoDTO(
    String nombre,
    String descripcion,
    String estado
) {
}
