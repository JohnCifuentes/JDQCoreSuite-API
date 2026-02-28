package uq.com.jdq.coresuite.catalogo.departamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/catalogo/departamentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<RespuestaDTO<List<DepartamentoDTO>>> getAllDepartamentos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.departamentoService.getAllDepartamentos()));
    }

    @GetMapping("/{paisId}/pais")
    public ResponseEntity<RespuestaDTO<List<DepartamentoDTO>>> getAllDepartamentosByPais(Long paisId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.departamentoService.getAllDepartamentosByPais(paisId)));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<RespuestaDTO<DepartamentoDTO>> getDepartamentoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.departamentoService.getDepartamentoById(id)));
    }

}
