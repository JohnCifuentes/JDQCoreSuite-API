package uq.com.jdq.coresuite.sistema.licencia;

import java.time.LocalDate;

/**
 * DTO de entrada para actualizar una licencia.
 */
public record UpdateLicenciaDTO(
    Long empresaId,
    Long planId,
    LocalDate fechaCompra,
    LocalDate fechaExpiracion,
    Boolean activo
) {
}
