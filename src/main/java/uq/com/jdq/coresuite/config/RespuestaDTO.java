package uq.com.jdq.coresuite.config;

public record RespuestaDTO<T>(
        boolean error,
        T contenido
) {
}
