package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

/**
 * DTO de salida para transferir informacion de tipos de identificacion.
 */
public record TipoIdentificacionDTO(
        Long id,
        String codigo,
        String nombre,
        String estado
) {
}
