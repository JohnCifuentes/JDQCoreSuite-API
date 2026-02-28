package uq.com.jdq.coresuite.sistema.plan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResponsePlanDTO(
    Long id,
    Integer cantidadUsuarios,
    String nombre,
    BigDecimal valor,
    String descripcion,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
