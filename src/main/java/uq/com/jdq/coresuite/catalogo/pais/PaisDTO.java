package uq.com.jdq.coresuite.catalogo.pais;

/**
 * DTO de salida para transferir informacion de paises.
 */
public record PaisDTO(
        Long id,
        String codigo,
        String iso3,
        String nombre
) {
}
