package uq.com.jdq.coresuite.sistema.sesion;

import java.time.LocalDateTime;

/**
 * DTO de entrada para actualizar una sesion.
 */
public record UpdateSesionDTO(
    Long empresaId,
    Long usuarioId,
    LocalDateTime fechaCierre,
    String estado
) {
}
