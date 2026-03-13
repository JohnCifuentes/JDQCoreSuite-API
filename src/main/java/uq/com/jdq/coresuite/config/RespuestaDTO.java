package uq.com.jdq.coresuite.config;

/**
 * Define la estructura y comportamiento de record RespuestaDTO.
 */
public record RespuestaDTO<T>(
        boolean error,
        T contenido
) {
}
