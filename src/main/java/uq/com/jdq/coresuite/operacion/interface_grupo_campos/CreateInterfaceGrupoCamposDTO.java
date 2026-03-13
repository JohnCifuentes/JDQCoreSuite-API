package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

/**
 * DTO de entrada para la creacion de grupos de campos por interfaz.
 */
public record CreateInterfaceGrupoCamposDTO(
    Long interfazId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
