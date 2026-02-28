package uq.com.jdq.coresuite.sistema.sesion;

import java.time.LocalDateTime;

public record UpdateSesionDTO(
    Long empresaId,
    Long usuarioId,
    LocalDateTime fechaInicio,
    LocalDateTime fechaUltimoAcceso,
    LocalDateTime fechaCierre
) {
}
