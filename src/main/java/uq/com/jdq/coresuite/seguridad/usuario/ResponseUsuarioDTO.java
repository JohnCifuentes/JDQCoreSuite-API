package uq.com.jdq.coresuite.seguridad.usuario;

import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

public record ResponseUsuarioDTO(
    Long id,
    Empresa empresa,
    TipoIdentificacion tipoIdentificacion,
    String numeroIdentificacion,
    String nombre1,
    String nombre2,
    String apellido1,
    String apellido2,
    String telefono,
    String correoElectronico,
    boolean primerAcceso,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
