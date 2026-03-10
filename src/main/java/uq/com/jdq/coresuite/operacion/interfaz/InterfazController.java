package uq.com.jdq.coresuite.operacion.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/interfaz")
public class InterfazController {

    private final InterfazService interfazService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new interfaz", description = "Creates a new interfaz with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseInterfazDTO>> createInterfaz(@RequestBody CreateInterfazDTO createInterfazDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.createInterfaz(createInterfazDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing interfaz", description = "Updates the interfaz with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseInterfazDTO>> updateInterfaz(@PathVariable Long id, @RequestBody UpdateInterfazDTO updateInterfazDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.updateInterfaz(id, updateInterfazDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all interfaz", description = "Retrieves a list of all interfaz")
    public ResponseEntity<RespuestaDTO<List<ResponseInterfazDTO>>> getAllInterfaz() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.getAllInterfaz()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get an interfaz by ID", description = "Retrieves a specific interfaz by its ID")
    public ResponseEntity<RespuestaDTO<ResponseInterfazDTO>> getInterfazById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.getInterfazById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{moduloId}/modulo")
    @Operation(summary = "Get interfaz by modulo", description = "Retrieves all interfaz for a specific modulo")
    public ResponseEntity<RespuestaDTO<List<ResponseInterfazDTO>>> getInterfazByModulo(@PathVariable Long moduloId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.getInterfazByModulo(moduloId)));
    }

}
