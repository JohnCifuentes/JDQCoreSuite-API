package uq.com.jdq.coresuite.catalogo.pais;

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
@RequestMapping("/api/catalogo/paises")
public class PaisController {
    private final PaisService paisService;

    @GetMapping
    public ResponseEntity<RespuestaDTO<List<PaisDTO>>> listarPaises() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.paisService.getAllPaises()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<PaisDTO>> getPaisById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.paisService.getPaisById(id)));
    }

}
