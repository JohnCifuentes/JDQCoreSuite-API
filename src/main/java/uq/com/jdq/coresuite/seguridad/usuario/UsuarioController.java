package uq.com.jdq.coresuite.seguridad.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seguridad/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new usuario", description = "Creates a new usuario with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> createUsuario(@RequestBody CreateUsuarioDTO createUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.createUsuario(createUsuarioDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing usuario", description = "Updates the usuario with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> updateUsuario(@PathVariable Long id, @RequestBody UpdateUsuarioDTO updateUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.updateUsuario(id, updateUsuarioDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a usuario", description = "Inactive the usuario with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> inactiveUsuario(@PathVariable Long id, @RequestBody InactiveUsuarioDTO inactiveUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.inactiveUsuario(id, inactiveUsuarioDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all usuarios", description = "Retrieves a list of all usuarios")
    public ResponseEntity<RespuestaDTO<List<ResponseUsuarioDTO>>> getAllUsuarios() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getAllUsuarios()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a usuario by ID", description = "Retrieves a specific usuario by its ID")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> getUsuarioById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getUsuarioById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<List<ResponseUsuarioDTO>>> getUsuariosByEmpresa(@PathVariable Long empresaId) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getUsuariosByEmpresa(empresaId)));
    };


    @GetMapping("/usuario/correo/password")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<Usuario>> getUsuarioByCorreoElectronicoAndPassword(@RequestBody UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getUsuarioByCorreoElectronicoAndPassword(usuarioCredencialesDTO)));
    };

    @PutMapping("/asignar/password")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> asignarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.asignarPassword(usuarioCredencialesDTO)));
    }

    @PutMapping("/recuperar/password")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> recuperarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.recuperarPassword(usuarioCredencialesDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/actualizar/password")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> actualizarPassword(@RequestBody UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.actualizarPassword(usuarioCredencialesDTO)));
    };

}
