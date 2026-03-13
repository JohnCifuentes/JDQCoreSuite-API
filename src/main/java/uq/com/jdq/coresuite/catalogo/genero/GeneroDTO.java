package uq.com.jdq.coresuite.catalogo.genero;

/**
 * DTO de salida para transferir informacion de genero.
 */
public record GeneroDTO(
        Long id,
        String nombre,
        String abreviatura
) {
}
