package uq.com.jdq.coresuite.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Define la estructura y comportamiento de class AutenticacionEntryPoint.
 */
@Component
public class AutenticacionEntryPoint implements AuthenticationEntryPoint {

    /**
     * Ejecuta la operacion commence.
     * @param request parametro de entrada.
     * @param response parametro de entrada.
     * @param authException parametro de entrada.
     * @throws IOException en caso de error durante la operacion.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        String mensaje = authException.getMessage();

        RespuestaDTO<String> dto = new RespuestaDTO<>(true, mensaje);

        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();

    }

}
