package uq.com.jdq.coresuite.catalogo.departamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la consulta de departamentos.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalogo/departamentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    /**
     * Obtiene la lista completa de departamentos.
     * @return respuesta con el listado de departamentos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    public ResponseEntity<RespuestaDTO<List<DepartamentoDTO>>> getAllDepartamentos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.departamentoService.getAllDepartamentos()));
    }

    /**
     * Obtiene los departamentos asociados a un pais.
     * @param paisId identificador del pais.
     * @return respuesta con los departamentos del pais.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{paisId}/pais")
    public ResponseEntity<RespuestaDTO<List<DepartamentoDTO>>> getAllDepartamentosByPais(@PathVariable Long paisId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.departamentoService.getAllDepartamentosByPais(paisId)));
    }

    /**
     * Obtiene un departamento por su identificador.
     * @param id identificador del departamento.
     * @return respuesta con el departamento encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}/")
    public ResponseEntity<RespuestaDTO<DepartamentoDTO>> getDepartamentoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.departamentoService.getDepartamentoById(id)));
    }

}
