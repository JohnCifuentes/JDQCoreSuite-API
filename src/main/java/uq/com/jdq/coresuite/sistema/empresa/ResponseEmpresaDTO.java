package uq.com.jdq.coresuite.sistema.empresa;

import uq.com.jdq.coresuite.catalogo.departamento.Departamento;
import uq.com.jdq.coresuite.catalogo.municipio.Municipio;
import uq.com.jdq.coresuite.catalogo.pais.Pais;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;

import java.time.LocalDateTime;

public record ResponseEmpresaDTO(
    Long id,
    TipoIdentificacion tipoIdentificacion,
    Pais pais,
    Departamento departamento,
    Municipio municipio,
    String numeroIdentificacion,
    String razonSocial,
    String direccion,
    String correoElectronico,
    String telefono,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
