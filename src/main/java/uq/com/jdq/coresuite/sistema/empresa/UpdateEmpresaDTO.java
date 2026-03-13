package uq.com.jdq.coresuite.sistema.empresa;

/**
 * DTO de entrada para actualizar una empresa.
 */
public record UpdateEmpresaDTO(
    Long tipoIdentificacionId,
    Long paisId,
    Long departamentoId,
    Long municipioId,
    String numeroIdentificacion,
    String razonSocial,
    String direccion,
    String correoElectronico,
    String telefono
) {
}
