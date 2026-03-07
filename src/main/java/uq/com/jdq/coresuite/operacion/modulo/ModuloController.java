package uq.com.jdq.coresuite.operacion.modulo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/modulo")
public class ModuloController {

    private final ModuloService moduloService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new modulo", description = "Creates a new modulo with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseModuloDTO>> createModulo(@RequestBody CreateModuloDTO createModuloDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.createModulo(createModuloDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing modulo", description = "Updates the modulo with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseModuloDTO>> updateModulo(@PathVariable Long id, @RequestBody UpdateModuloDTO updateModuloDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.updateModulo(id, updateModuloDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all modulos", description = "Retrieves a list of all modulos")
    public ResponseEntity<RespuestaDTO<List<ResponseModuloDTO>>> getAllModulos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.getAllModulos()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a modulo by ID", description = "Retrieves a specific modulo by its ID")
    public ResponseEntity<RespuestaDTO<ResponseModuloDTO>> getModuloById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.getModuloById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "Get modulos by empresa", description = "Retrieves the modulos for a given empresa")
    public ResponseEntity<RespuestaDTO<List<ResponseModuloDTO>>> getModulosByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.getModulosByEmpresa(empresaId)));
    }

}
