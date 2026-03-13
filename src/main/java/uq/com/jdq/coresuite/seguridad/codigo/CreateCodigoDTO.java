package uq.com.jdq.coresuite.seguridad.codigo;

/**
 * DTO de entrada para solicitar la generacion de un codigo de verificacion.
 */
public record CreateCodigoDTO(
        String correoElectronico
) {
}
