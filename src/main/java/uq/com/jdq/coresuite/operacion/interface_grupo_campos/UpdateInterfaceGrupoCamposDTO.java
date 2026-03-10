package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

public record UpdateInterfaceGrupoCamposDTO(
    Long interfazId,
    String nombre,
    String descripcion,
    Integer indice
) {
}
