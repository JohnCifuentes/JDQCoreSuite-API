package uq.com.jdq.coresuite.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * Define la estructura y comportamiento de class JWTUtils.
 */
@Component
public class JWTUtils {

    /**
     * Ejecuta la operacion generateToken.
     * @param id parametro de entrada.
     * @param claims parametro de entrada.
     * @return resultado de la operacion.
     */
    public String generateToken(String id, Map<String, String> claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(id)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(1L, ChronoUnit.HOURS)))
                .signWith( getKey() )
                .compact();
    }

    /**
     * Ejecuta la operacion parseJwt.
     * @param jwtString parametro de entrada.
     * @return resultado de la operacion.
     * @throws ExpiredJwtException en caso de error durante la operacion.
     * @throws UnsupportedJwtException en caso de error durante la operacion.
     * @throws MalformedJwtException en caso de error durante la operacion.
     * @throws IllegalArgumentException en caso de error durante la operacion.
     */
    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith( getKey() ).build();
        return jwtParser.parseSignedClaims(jwtString);
    }

    /**
     * Ejecuta la operacion getKey.
     * @return resultado de la operacion.
     */
    private SecretKey getKey(){
        String claveSecreta = "secretsecretsecretsecretsecretsecretsecretsecret";
        byte[] secretKeyBytes = claveSecreta.getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }

}
