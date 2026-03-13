package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

/**
 * DTO de entrada para la actualizacion de grupos de campos por interfaz.
 */
public record UpdateInterfaceGrupoCamposDTO(
    Long interfazId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
