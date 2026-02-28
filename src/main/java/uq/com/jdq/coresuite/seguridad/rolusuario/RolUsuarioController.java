package uq.com.jdq.coresuite.seguridad.rolusuario;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/seguridad/rol-usuario")
@RequiredArgsConstructor
public class RolUsuarioController {

    private final RolUsuarioService rolUsuarioService;

    @PostMapping
    @Operation(summary = "Create a new rol-usuario", description = "Creates a new rol-usuario with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> createRolUsuario(@RequestBody CreateRolUsuarioDTO createRolUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.createRolUsuario(createRolUsuarioDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing rol-usuario", description = "Updates the rol-usuario with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> updateRolUsuario(@PathVariable Long id, @RequestBody UpdateRolUsuarioDTO updateRolUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.updateRolUsuario(id, updateRolUsuarioDTO)));
    }

    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a rol-usuario", description = "Inactive the rol-usuario with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> inactiveRolUsuario(@PathVariable Long id, @RequestBody InactiveRolUsuarioDTO inactiveRolUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.inactiveRolUsuario(id, inactiveRolUsuarioDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all rol-usuarios", description = "Retrieves a list of all rol-usuarios")
    public ResponseEntity<RespuestaDTO<List<ResponseRolUsuarioDTO>>> getAllRolUsuarios() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.getAllRolUsuarios()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a rol-usuario by ID", description = "Retrieves a specific rol-usuario by its ID")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> getRolUsuarioById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.getRolUsuarioById(id)));
    }

    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<List<ResponseRolUsuarioDTO>> getRolUsuariosByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(rolUsuarioService.getRolUsuariosByEmpresa(empresaId));
    }

}
