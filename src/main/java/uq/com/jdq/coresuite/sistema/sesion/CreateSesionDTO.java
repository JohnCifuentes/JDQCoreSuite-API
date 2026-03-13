package uq.com.jdq.coresuite.sistema.sesion;

/**
 * DTO de entrada para registrar una sesion.
 */
public record CreateSesionDTO(
    Long empresaId,
    Long usuarioId
) {
}
