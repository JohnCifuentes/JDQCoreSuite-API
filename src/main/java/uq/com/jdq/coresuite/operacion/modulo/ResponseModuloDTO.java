package uq.com.jdq.coresuite.operacion.modulo;

import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

/**
 * DTO de salida para transferir informacion de modulos.
 */
public record ResponseModuloDTO(
    Long id,
    Empresa empresa,
    String nombre,
    String descripcion,
    Integer indice,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
