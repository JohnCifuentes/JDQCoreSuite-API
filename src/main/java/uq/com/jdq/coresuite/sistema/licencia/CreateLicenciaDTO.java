package uq.com.jdq.coresuite.sistema.licencia;

import java.time.LocalDate;

/**
 * DTO de entrada para registrar una licencia.
 */
public record CreateLicenciaDTO(
    Long empresaId,
    Long planId,
    LocalDate fechaCompra,
    LocalDate fechaExpiracion,
    Boolean activo
) {
}
