package uq.com.jdq.coresuite.catalogo.genero;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la consulta de generos.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalogo/generos")
public class GeneroController {
    private final GeneroService generoService;

    /**
     * Obtiene la lista completa de generos.
     * @return respuesta con el listado de generos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    public ResponseEntity<RespuestaDTO<List<GeneroDTO>>> listarGeneros() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.generoService.getAllGeneros()));
    }

    /**
     * Obtiene un genero por su identificador.
     * @param id identificador del genero.
     * @return respuesta con el genero encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<GeneroDTO>> getGeneroById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.generoService.getGeneroById(id)));
    }

}
