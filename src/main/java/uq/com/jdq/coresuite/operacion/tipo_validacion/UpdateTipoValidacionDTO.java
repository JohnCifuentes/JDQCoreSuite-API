package uq.com.jdq.coresuite.operacion.tipo_validacion;

/**
 * DTO de entrada para la actualizacion de tipos de validacion.
 */
public record UpdateTipoValidacionDTO(
    String nombre,
    String descripcion
) {
}
