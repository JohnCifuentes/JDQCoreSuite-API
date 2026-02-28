package uq.com.jdq.coresuite.sistema.licencia;

import java.time.LocalDate;

public record CreateLicenciaDTO(
    Long empresaId,
    Long planId,
    LocalDate fechaCompra,
    LocalDate fechaExpiracion,
    Boolean activo
) {
}
