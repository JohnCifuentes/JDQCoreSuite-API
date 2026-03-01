package uq.com.jdq.coresuite.catalogo.municipio;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/catalogo/ciudades")
public class MunicipioController {
    private final MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<RespuestaDTO<List<MunicipioDTO>>> getAllMunicipios() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.municipioService.getAllMunicipios()));
    }

    @GetMapping("/{departamentoId}/departamento")
    public ResponseEntity<RespuestaDTO<List<MunicipioDTO>>> getAllMunicipiosByDepartamento(@PathVariable Long departamentoId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.municipioService.getAllMunicipiosByDepartamento(departamentoId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<MunicipioDTO>> getMunicipioById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.municipioService.getMunicipioById(id)));
    }

}
