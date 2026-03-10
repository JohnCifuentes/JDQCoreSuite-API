package uq.com.jdq.coresuite.operacion.campo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/campo")
public class CampoController {

    private final CampoService campoService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new campo", description = "Creates a new campo with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDTO>> createCampo(@RequestBody CreateCampoDTO createCampoDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.createCampo(createCampoDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campo", description = "Updates the campo with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDTO>> updateCampo(@PathVariable Long id, @RequestBody UpdateCampoDTO updateCampoDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.updateCampo(id, updateCampoDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all campos", description = "Retrieves a list of all campos")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDTO>>> getAllCampos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.getAllCampos()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a campo by ID", description = "Retrieves a specific campo by its ID")
    public ResponseEntity<RespuestaDTO<ResponseCampoDTO>> getCampoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.getCampoById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{interfazId}/interfaz")
    @Operation(summary = "Get campos by interfaz", description = "Retrieves all campos for a specific interfaz")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDTO>>> getCamposByInterfaz(@PathVariable Long interfazId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.getCamposByInterfaz(interfazId)));
    }

}
