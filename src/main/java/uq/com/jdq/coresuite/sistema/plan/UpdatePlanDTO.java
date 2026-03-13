package uq.com.jdq.coresuite.sistema.plan;

import java.math.BigDecimal;

/**
 * DTO de entrada para actualizar un plan.
 */
public record UpdatePlanDTO(
    Integer cantidadUsuarios,
    String nombre,
    BigDecimal valor,
    String descripcion
) {
}
