package uq.com.jdq.coresuite.operacion.campo_validacion;

import java.time.LocalDateTime;
import uq.com.jdq.coresuite.operacion.campo.Campo;
import uq.com.jdq.coresuite.operacion.tipo_validacion.TipoValidacion;

/**
 * DTO de salida para transferir informacion de validaciones de campo.
 */
public record ResponseCampoValidacionDTO(
    Long id,
    Campo campo,
    TipoValidacion tipoValidacion,
    String valor,
    Campo campoReferencia,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
