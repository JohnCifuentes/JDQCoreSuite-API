package uq.com.jdq.coresuite.seguridad.rolusuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Controlador REST para la administracion de asignaciones entre roles y usuarios.
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/seguridad/rol-usuario")
public class RolUsuarioController {

    private final RolUsuarioService rolUsuarioService;

    /**
     * Crea una nueva asignacion de rol para un usuario.
     * @param createRolUsuarioDTO datos de la asignacion a registrar.
     * @return respuesta con la asignacion creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @PostMapping
    @Operation(summary = "Create a new rol-usuario", description = "Creates a new rol-usuario with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> createRolUsuario(@RequestBody CreateRolUsuarioDTO createRolUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.createRolUsuario(createRolUsuarioDTO)));
    }

    /**
     * Actualiza una asignacion existente entre rol y usuario.
     * @param id identificador de la asignacion.
     * @param updateRolUsuarioDTO datos actualizados de la asignacion.
     * @return respuesta con la asignacion actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing rol-usuario", description = "Updates the rol-usuario with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> updateRolUsuario(@PathVariable Long id, @RequestBody UpdateRolUsuarioDTO updateRolUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.updateRolUsuario(id, updateRolUsuarioDTO)));
    }

    /**
     * Inactiva una asignacion entre rol y usuario.
     * @param id identificador de la asignacion.
     * @param inactiveRolUsuarioDTO datos del cambio de estado.
     * @return respuesta con la asignacion inactivada.
     * @throws Exception si ocurre un error durante la inactivacion.
     */
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a rol-usuario", description = "Inactive the rol-usuario with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> inactiveRolUsuario(@PathVariable Long id, @RequestBody InactiveRolUsuarioDTO inactiveRolUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.inactiveRolUsuario(id, inactiveRolUsuarioDTO)));
    }

    /**
     * Obtiene todas las asignaciones entre roles y usuarios.
     * @return respuesta con la lista de asignaciones.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    @Operation(summary = "Get all rol-usuarios", description = "Retrieves a list of all rol-usuarios")
    public ResponseEntity<RespuestaDTO<List<ResponseRolUsuarioDTO>>> getAllRolUsuarios() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.getAllRolUsuarios()));
    }

    /**
     * Consulta una asignacion por su identificador.
     * @param id identificador de la asignacion.
     * @return respuesta con la asignacion encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a rol-usuario by ID", description = "Retrieves a specific rol-usuario by its ID")
    public ResponseEntity<RespuestaDTO<ResponseRolUsuarioDTO>> getRolUsuarioById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolUsuarioService.getRolUsuarioById(id)));
    }

    /**
     * Obtiene las asignaciones de rol-usuario asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de asignaciones de la empresa.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<List<ResponseRolUsuarioDTO>> getRolUsuariosByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(rolUsuarioService.getRolUsuariosByEmpresa(empresaId));
    }

}
