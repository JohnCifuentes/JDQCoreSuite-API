package uq.com.jdq.coresuite.operacion.tipo_validacion;

import java.time.LocalDateTime;

/**
 * DTO de salida para transferir informacion de tipos de validacion.
 */
public record ResponseTipoValidacionDTO(
    Long id,
    String nombre,
    String descripcion,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
