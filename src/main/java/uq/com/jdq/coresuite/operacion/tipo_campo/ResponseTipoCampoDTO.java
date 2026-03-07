package uq.com.jdq.coresuite.operacion.tipo_campo;

import java.time.LocalDateTime;

public record ResponseTipoCampoDTO(
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
