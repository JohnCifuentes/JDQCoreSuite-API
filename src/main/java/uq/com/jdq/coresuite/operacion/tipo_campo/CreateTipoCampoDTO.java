package uq.com.jdq.coresuite.operacion.tipo_campo;

/**
 * DTO de entrada para la creacion de tipos de campo.
 */
public record CreateTipoCampoDTO(
    String nombre,
    String descripcion
) {
}
