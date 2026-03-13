package uq.com.jdq.coresuite.seguridad.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion de usuarios y sus credenciales.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seguridad/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Crea un nuevo usuario en el sistema.
     * @param createUsuarioDTO datos del usuario a registrar.
     * @return respuesta con el usuario creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new usuario", description = "Creates a new usuario with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> createUsuario(@RequestBody CreateUsuarioDTO createUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.createUsuario(createUsuarioDTO)));
    }

    /**
     * Actualiza la informacion de un usuario existente.
     * @param id identificador del usuario.
     * @param updateUsuarioDTO datos actualizados del usuario.
     * @return respuesta con el usuario actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing usuario", description = "Updates the usuario with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> updateUsuario(@PathVariable Long id, @RequestBody UpdateUsuarioDTO updateUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.updateUsuario(id, updateUsuarioDTO)));
    }

    /**
     * Inactiva un usuario registrado.
     * @param id identificador del usuario.
     * @param inactiveUsuarioDTO datos del cambio de estado.
     * @return respuesta con el usuario inactivado.
     * @throws Exception si ocurre un error durante la inactivacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a usuario", description = "Inactive the usuario with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> inactiveUsuario(@PathVariable Long id, @RequestBody InactiveUsuarioDTO inactiveUsuarioDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.inactiveUsuario(id, inactiveUsuarioDTO)));
    }

    /**
     * Obtiene todos los usuarios registrados.
     * @return respuesta con la lista de usuarios.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all usuarios", description = "Retrieves a list of all usuarios")
    public ResponseEntity<RespuestaDTO<List<ResponseUsuarioDTO>>> getAllUsuarios() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getAllUsuarios()));
    }

    /**
     * Consulta un usuario por su identificador.
     * @param id identificador del usuario.
     * @return respuesta con la informacion del usuario.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a usuario by ID", description = "Retrieves a specific usuario by its ID")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> getUsuarioById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getUsuarioById(id)));
    }

    /**
     * Obtiene los usuarios asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return respuesta con la lista de usuarios.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<List<ResponseUsuarioDTO>>> getUsuariosByEmpresa(@PathVariable Long empresaId) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getUsuariosByEmpresa(empresaId)));
    };


    /**
     * Consulta un usuario usando sus credenciales de acceso.
     * @param correoElectronico correo electronico del usuario.
     * @param password contrasena del usuario.
     * @return respuesta con la entidad del usuario autenticado.
     * @throws Exception si ocurre un error durante la validacion.
     */
    @GetMapping("/obtener/{correoElectronico}/{password}/usuario")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<Usuario>> getUsuarioByCorreoElectronicoAndPassword(@PathVariable String correoElectronico, @PathVariable String password) throws Exception{
        UsuarioCredencialesDTO usuarioCredencialesDTO = new UsuarioCredencialesDTO(correoElectronico, password);
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.getUsuarioByCorreoElectronicoAndPassword(usuarioCredencialesDTO)));
    };

    /**
     * Recupera la contrasena de un usuario.
     * @param usuarioCredencialesDTO credenciales con el correo y la nueva contrasena.
     * @return respuesta con el usuario actualizado.
     * @throws Exception si ocurre un error durante el proceso.
     */
    @PutMapping("/recuperar/password")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> recuperarPassword(@RequestBody UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.recuperarPassword(usuarioCredencialesDTO)));
    }

    /**
     * Actualiza la contrasena de un usuario autenticado.
     * @param usuarioCredencialesDTO credenciales con el correo y la nueva contrasena.
     * @return respuesta con el usuario actualizado.
     * @throws Exception si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/actualizar/password")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> actualizarPassword(@RequestBody UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception{
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.actualizarPassword(usuarioCredencialesDTO)));
    };

    /**
     * Bloquea un usuario usando su correo electronico.
     * @param correoElectronico correo electronico del usuario.
     * @return respuesta con el usuario bloqueado.
     * @throws Exception si ocurre un error durante el bloqueo.
     */
    @PutMapping("/{correoElectronico}/bloquear/usuario")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> blockUsuario(@PathVariable String correoElectronico) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.blockUsuario(correoElectronico)));
    }

    /**
     * Desbloquea un usuario por su identificador.
     * @param usuarioId identificador del usuario.
     * @return respuesta con el usuario desbloqueado.
     * @throws Exception si ocurre un error durante el desbloqueo.
     */
    @PutMapping("/{usuarioId}/desbloquear/usuario")
    public ResponseEntity<RespuestaDTO<ResponseUsuarioDTO>> unblockUsuario(@PathVariable Long usuarioId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.usuarioService.unblockUsuario(usuarioId)));
    }

}
