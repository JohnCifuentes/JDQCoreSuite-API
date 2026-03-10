package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import java.time.LocalDateTime;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;

public record ResponseInterfaceGrupoCamposDTO(
    Long id,
    Interfaz interfaz,
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
