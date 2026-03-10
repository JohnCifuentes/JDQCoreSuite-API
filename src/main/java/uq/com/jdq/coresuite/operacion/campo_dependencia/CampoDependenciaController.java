package uq.com.jdq.coresuite.operacion.campo_dependencia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/campo-dependencia")
public class CampoDependenciaController {

    private final CampoDependenciaService campoDependenciaService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new campo dependencia", description = "Creates a new campo dependencia with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDependenciaDTO>> createCampoDependencia(@RequestBody CreateCampoDependenciaDTO createCampoDependenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.createCampoDependencia(createCampoDependenciaDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campo dependencia", description = "Updates the campo dependencia with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDependenciaDTO>> updateCampoDependencia(@PathVariable Long id, @RequestBody UpdateCampoDependenciaDTO updateCampoDependenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.updateCampoDependencia(id, updateCampoDependenciaDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all campo dependencias", description = "Retrieves a list of all campo dependencias")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDependenciaDTO>>> getAllCampoDependencias() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.getAllCampoDependencias()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a campo dependencia by ID", description = "Retrieves a specific campo dependencia by its ID")
    public ResponseEntity<RespuestaDTO<ResponseCampoDependenciaDTO>> getCampoDependenciaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.getCampoDependenciaById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{campoId}/campo")
    @Operation(summary = "Get campo dependencias by campo", description = "Retrieves all campo dependencias for a specific campo")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDependenciaDTO>>> getCampoDependenciasByCampo(@PathVariable Long campoId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.getCampoDependenciasByCampo(campoId)));
    }

}
