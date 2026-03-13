package uq.com.jdq.coresuite.operacion.interfaz;

import java.time.LocalDateTime;
import uq.com.jdq.coresuite.operacion.modulo.Modulo;

/**
 * DTO de salida para transferir informacion de interfaces.
 */
public record ResponseInterfazDTO(
    Long id,
    Modulo modulo,
    String nombre,
    String descripcion,
    Integer indice,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
