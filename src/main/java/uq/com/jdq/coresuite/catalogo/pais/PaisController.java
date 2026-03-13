package uq.com.jdq.coresuite.catalogo.pais;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la consulta de paises.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalogo/paises")
public class PaisController {
    private final PaisService paisService;

    /**
     * Obtiene la lista completa de paises.
     * @return respuesta con el listado de paises.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    public ResponseEntity<RespuestaDTO<List<PaisDTO>>> listarPaises() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.paisService.getAllPaises()));
    }

    /**
     * Obtiene un pais por su identificador.
     * @param id identificador del pais.
     * @return respuesta con el pais encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<PaisDTO>> getPaisById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.paisService.getPaisById(id)));
    }

}
