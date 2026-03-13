package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la consulta de tipos de identificacion.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalogo/tipos-identificacion")
public class TipoIdentificacionController {
    private final TipoIdentificacionService tipoIdentificacionService;


    /**
     * Obtiene la lista completa de tipos de identificacion.
     * @return respuesta con el listado de tipos de identificacion.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    public ResponseEntity<RespuestaDTO<List<TipoIdentificacionDTO>>> getAllTiposIdentificacion() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoIdentificacionService.getAllTiposIdentificacion()));
    }

    /**
     * Obtiene un tipo de identificacion por su identificador.
     * @param id identificador del tipo de identificacion.
     * @return respuesta con el tipo de identificacion encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<TipoIdentificacionDTO>> getTipoIdentificacionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok((new RespuestaDTO<>(false, this.tipoIdentificacionService.getTipoIdentificacionById(id))));
    }
}
