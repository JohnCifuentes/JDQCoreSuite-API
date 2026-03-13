package uq.com.jdq.coresuite.sistema.sesion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la consulta y creacion de sesiones.
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/sistema/sesion")
public class SesionController {

    private final SesionService sesionService;

    /**
     * Crea una nueva sesion para un usuario.
     * @param createSesionDTO datos de la sesion a registrar.
     * @return respuesta con la sesion creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @PostMapping
    @Operation(summary = "Create a new sesion", description = "Creates a new sesion with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseSesionDTO>> createSesion(@RequestBody CreateSesionDTO createSesionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.createSesion(createSesionDTO)));
    }

    /**
     * Obtiene todas las sesiones registradas.
     * @return respuesta con la lista de sesiones.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    @Operation(summary = "Get all sesiones", description = "Retrieves a list of all sesiones")
    public ResponseEntity<RespuestaDTO<List<ResponseSesionDTO>>> getAllSesiones() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.getAllSesiones()));
    }

    /**
     * Consulta una sesion por su identificador.
     * @param id identificador de la sesion.
     * @return respuesta con la sesion encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a sesion by ID", description = "Retrieves a specific sesion by its ID")
    public ResponseEntity<RespuestaDTO<ResponseSesionDTO>> getSesionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.getSesionById(id)));
    }

    /**
     * Obtiene las sesiones asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return respuesta con la lista de sesiones.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<List<ResponseSesionDTO>>> getAllSesionesByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.getSesionesByEmpresa(empresaId)));
    }

}
