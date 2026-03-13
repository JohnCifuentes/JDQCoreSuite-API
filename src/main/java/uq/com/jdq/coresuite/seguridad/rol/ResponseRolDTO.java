package uq.com.jdq.coresuite.seguridad.rol;

import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

/**
 * DTO de respuesta con la informacion de un rol.
 */
public record ResponseRolDTO(
    Long id,
    Empresa empresa,
    String nombre,
    String descripcion,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
