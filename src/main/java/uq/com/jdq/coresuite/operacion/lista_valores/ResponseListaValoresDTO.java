package uq.com.jdq.coresuite.operacion.lista_valores;

import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.time.LocalDateTime;

public record ResponseListaValoresDTO(
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
