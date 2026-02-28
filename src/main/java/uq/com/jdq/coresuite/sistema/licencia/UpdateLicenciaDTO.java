package uq.com.jdq.coresuite.sistema.licencia;

import java.time.LocalDate;

public record UpdateLicenciaDTO(
    Long empresaId,
    Long planId,
    LocalDate fechaCompra,
    LocalDate fechaExpiracion,
    Boolean activo
) {
}
