package uq.com.jdq.coresuite.seguridad.rol;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;
import uq.com.jdq.coresuite.sistema.empresa.ResponseEmpresaDTO;

import java.util.List;

@RestController
@RequestMapping("api/seguridad/rol")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping
    @Operation(summary = "Create a new rol", description = "Creates a new rol with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> createRol(@RequestBody CreateRolDTO createRolDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.createRol(createRolDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing rol", description = "Updates the rol with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> updateRol(@PathVariable Long id, @RequestBody UpdateRolDTO updateRolDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.updateRol(id, updateRolDTO)));
    }

    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a rol", description = "Inactive the rol with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> inactiveRol(@PathVariable Long id, @RequestBody InactiveRolDTO inactiveRolDTO)  throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.inactiveRol(id, inactiveRolDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all roles", description = "Retrieves a list of all roles")
    public ResponseEntity<RespuestaDTO<List<ResponseRolDTO>>> getAllRoles() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.rolService.getAllRoles()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a rol by ID", description = "Retrieves a specific rol by its ID")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> getRolById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.getRolById(id)));
    }

    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<List<ResponseRolDTO>>> getRolsByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.rolService.getRolsByEmpresa(empresaId)));
    }

}
