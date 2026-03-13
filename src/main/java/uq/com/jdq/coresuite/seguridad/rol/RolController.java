package uq.com.jdq.coresuite.seguridad.rol;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion de roles de seguridad.
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/seguridad/rol")
public class RolController {

    private final RolService rolService;

    /**
     * Crea un nuevo rol para la empresa indicada.
     * @param createRolDTO datos necesarios para registrar el rol.
     * @return respuesta con el rol creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @PostMapping
    @Operation(summary = "Create a new rol", description = "Creates a new rol with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> createRol(@RequestBody CreateRolDTO createRolDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.createRol(createRolDTO)));
    }

    /**
     * Actualiza la informacion de un rol existente.
     * @param id identificador del rol a actualizar.
     * @param updateRolDTO datos actualizados del rol.
     * @return respuesta con el rol actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing rol", description = "Updates the rol with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> updateRol(@PathVariable Long id, @RequestBody UpdateRolDTO updateRolDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.updateRol(id, updateRolDTO)));
    }

    /**
     * Inactiva un rol registrado.
     * @param id identificador del rol a inactivar.
     * @param inactiveRolDTO datos para actualizar el estado del rol.
     * @return respuesta con el rol inactivado.
     * @throws Exception si ocurre un error durante la inactivacion.
     */
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a rol", description = "Inactive the rol with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> inactiveRol(@PathVariable Long id, @RequestBody InactiveRolDTO inactiveRolDTO)  throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.inactiveRol(id, inactiveRolDTO)));
    }

    /**
     * Obtiene todos los roles registrados.
     * @return respuesta con la lista de roles.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    @Operation(summary = "Get all roles", description = "Retrieves a list of all roles")
    public ResponseEntity<RespuestaDTO<List<ResponseRolDTO>>> getAllRoles() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.rolService.getAllRoles()));
    }

    /**
     * Consulta un rol por su identificador.
     * @param id identificador del rol a consultar.
     * @return respuesta con la informacion del rol.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a rol by ID", description = "Retrieves a specific rol by its ID")
    public ResponseEntity<RespuestaDTO<ResponseRolDTO>> getRolById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.rolService.getRolById(id)));
    }

    /**
     * Obtiene los roles asociados a una empresa.
     * @param empresaId identificador de la empresa a consultar.
     * @return respuesta con la lista de roles de la empresa.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<List<ResponseRolDTO>>> getRolsByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(true, this.rolService.getRolsByEmpresa(empresaId)));
    }

}
