package uq.com.jdq.coresuite.operacion.tipo_validacion;

import java.time.LocalDateTime;

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
