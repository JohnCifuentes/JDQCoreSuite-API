package uq.com.jdq.coresuite.seguridad.rol;

import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

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
