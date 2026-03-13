package uq.com.jdq.coresuite.sistema.licencia;

import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.plan.Plan;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de respuesta con la informacion de una licencia.
 */
public record ResponseLicenciaDTO(
    Long id,
    Empresa empresa,
    Plan plan,
    LocalDate fechaCompra,
    LocalDate fechaExpiracion,
    Boolean activo,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
