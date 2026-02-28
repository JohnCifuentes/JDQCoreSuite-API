package uq.com.jdq.coresuite.sistema.plan;

import java.math.BigDecimal;

public record UpdatePlanDTO(
    Integer cantidadUsuarios,
    String nombre,
    BigDecimal valor,
    String descripcion
) {
}
