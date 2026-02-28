package uq.com.jdq.coresuite.sistema.sesion;

import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

public record ResponseSesionDTO(
    Long id,
    Empresa empresa,
    Usuario usuario,
    LocalDateTime fechaInicio,
    LocalDateTime fechaUltimoAcceso,
    LocalDateTime fechaCierre,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
