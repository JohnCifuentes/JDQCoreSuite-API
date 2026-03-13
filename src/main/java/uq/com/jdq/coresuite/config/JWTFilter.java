package uq.com.jdq.coresuite.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Define la estructura y comportamiento de class JWTFilter.
 */
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtil;

    /**
     * Ejecuta la operacion doFilterInternal.
     * @param request parametro de entrada.
     * @param response parametro de entrada.
     * @param chain parametro de entrada.
     * @throws ServletException en caso de error durante la operacion.
     * @throws IOException en caso de error durante la operacion.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)  throws ServletException, IOException {
        //Obtener el token del header de la solicitud
        String token = getToken(request);
        //Si no hay token, continuar con la cadena de filtros
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        try {
            //Validar el token y obtener el payload
            Jws<Claims> payload = jwtUtil.parseJwt(token);
            String username = payload.getPayload().getSubject();
            //Si el usuario no estÃ¡ autenticado, crear un nuevo objeto de autenticaciÃ³n
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Crear un objeto UserDetails con el nombre de usuario y el rol
                UserDetails userDetails = new User(
                        username,
                        "",
                        List.of()
                );
                //Crear un objeto de autenticaciÃ³n y establecerlo en el contexto de seguridad
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            //Si el token no es vÃ¡lido, enviar un error 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        //Continuar con la cadena de filtros
        chain.doFilter(request, response);
    }

    /**
     * Ejecuta la operacion getToken.
     * @param req parametro de entrada.
     * @return resultado de la operacion.
     */
    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ") ? header.replace("Bearer ", "") : null;
    }

}
