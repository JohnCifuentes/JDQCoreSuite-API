package uq.com.jdq.coresuite.sistema.plan;

import java.math.BigDecimal;

/**
 * DTO de entrada para registrar un plan.
 */
public record CreatePlanDTO(
    Integer cantidadUsuarios,
    String nombre,
    BigDecimal valor,
    String descripcion
) {
}
