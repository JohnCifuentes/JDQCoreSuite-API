package uq.com.jdq.coresuite.catalogo.municipio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la consulta de municipios.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalogo/ciudades")
public class MunicipioController {
    private final MunicipioService municipioService;

    /**
     * Obtiene la lista completa de municipios.
     * @return respuesta con el listado de municipios.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    public ResponseEntity<RespuestaDTO<List<MunicipioDTO>>> getAllMunicipios() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.municipioService.getAllMunicipios()));
    }

    /**
    * Obtiene los municipios asociados a un departamento.
    * @param departamentoId identificador del departamento.
    * @return respuesta con los municipios del departamento.
    * @throws Exception si ocurre un error durante la consulta.
    */
    @GetMapping("/{departamentoId}/departamento")
    public ResponseEntity<RespuestaDTO<List<MunicipioDTO>>> getAllMunicipiosByDepartamento(@PathVariable Long departamentoId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.municipioService.getAllMunicipiosByDepartamento(departamentoId)));
    }

    /**
     * Obtiene un municipio por su identificador.
     * @param id identificador del municipio.
     * @return respuesta con el municipio encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<MunicipioDTO>> getMunicipioById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.municipioService.getMunicipioById(id)));
    }

}
