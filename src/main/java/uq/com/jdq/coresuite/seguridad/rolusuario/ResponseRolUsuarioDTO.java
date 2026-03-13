package uq.com.jdq.coresuite.seguridad.rolusuario;

import uq.com.jdq.coresuite.seguridad.rol.Rol;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

/**
 * DTO de respuesta con la informacion de una asignacion rol-usuario.
 */
public record ResponseRolUsuarioDTO(
    Long id,
    Empresa empresa,
    Rol rol,
    Usuario usuario,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
