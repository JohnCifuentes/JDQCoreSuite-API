package uq.com.jdq.coresuite.operacion.campo_dependencia;

import java.time.LocalDateTime;
import uq.com.jdq.coresuite.operacion.campo.Campo;

/**
 * DTO de salida para transferir informacion de dependencias de campo.
 */
public record ResponseCampoDependenciaDTO(
    Long id,
    Campo campo,
    Campo campoDependiente,
    String operador,
    String valor,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
