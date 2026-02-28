package uq.com.jdq.coresuite.sistema.empresa;

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
