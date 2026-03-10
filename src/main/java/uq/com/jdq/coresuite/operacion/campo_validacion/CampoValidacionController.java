package uq.com.jdq.coresuite.operacion.campo_validacion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/campo-validacion")
public class CampoValidacionController {

    private final CampoValidacionService campoValidacionService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new campo validacion", description = "Creates a new campo validacion with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoValidacionDTO>> createCampoValidacion(@RequestBody CreateCampoValidacionDTO createCampoValidacionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.createCampoValidacion(createCampoValidacionDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campo validacion", description = "Updates the campo validacion with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoValidacionDTO>> updateCampoValidacion(@PathVariable Long id, @RequestBody UpdateCampoValidacionDTO updateCampoValidacionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.updateCampoValidacion(id, updateCampoValidacionDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all campo validaciones", description = "Retrieves a list of all campo validaciones")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoValidacionDTO>>> getAllCampoValidaciones() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.getAllCampoValidaciones()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a campo validacion by ID", description = "Retrieves a specific campo validacion by its ID")
    public ResponseEntity<RespuestaDTO<ResponseCampoValidacionDTO>> getCampoValidacionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.getCampoValidacionById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{campoId}/campo")
    @Operation(summary = "Get campo validaciones by campo", description = "Retrieves all campo validaciones for a specific campo")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoValidacionDTO>>> getCampoValidacionesByCampo(@PathVariable Long campoId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.getCampoValidacionesByCampo(campoId)));
    }

}
