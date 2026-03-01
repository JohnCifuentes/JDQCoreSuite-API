package uq.com.jdq.coresuite.catalogo.genero;

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
@RequestMapping("/api/catalogo/generos")
public class GeneroController {
    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<RespuestaDTO<List<GeneroDTO>>> listarGeneros() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.generoService.getAllGeneros()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO<GeneroDTO>> getGeneroById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.generoService.getGeneroById(id)));
    }

}
