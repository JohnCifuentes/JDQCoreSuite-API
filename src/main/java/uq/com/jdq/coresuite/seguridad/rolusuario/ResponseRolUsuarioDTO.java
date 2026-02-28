package uq.com.jdq.coresuite.seguridad.rolusuario;

import uq.com.jdq.coresuite.seguridad.rol.Rol;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

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
