package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;

import java.time.LocalDateTime;

/**
 * DTO de salida para transferir informacion de detalles de listas de valores.
 */
public record ResponseListaValoresDetalleDTO(
    Long id,
    ListaValores listaValores,
    String nombre,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
