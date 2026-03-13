package uq.com.jdq.coresuite.operacion.campo;

import java.time.LocalDateTime;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;
import uq.com.jdq.coresuite.operacion.interface_grupo_campos.InterfaceGrupoCampos;
import uq.com.jdq.coresuite.operacion.tipo_campo.TipoCampo;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;

/**
 * DTO de salida para transferir informacion de campos.
 */
public record ResponseCampoDTO(
    Long id,
    Interfaz interfaz,
    InterfaceGrupoCampos interfaceGrupoCampos,
    TipoCampo tipoCampo,
    ListaValores listaValores,
    String nombre,
    String etiqueta,
    String descripcion,
    Integer indice,
    Integer columnas,
    String valorDefecto,
    String estado,
    String usuarioCreacion,
    LocalDateTime fechaCreacion,
    String usuarioActualizacion,
    LocalDateTime fechaActualizacion
) {
}
