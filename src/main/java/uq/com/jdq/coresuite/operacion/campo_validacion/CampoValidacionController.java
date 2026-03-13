package uq.com.jdq.coresuite.operacion.campo_validacion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de validaciones de campo.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/campo-validacion")
public class CampoValidacionController {

    private final CampoValidacionService campoValidacionService;

    /**
     * Crea una nueva validacion asociada a un campo.
     * @param createCampoValidacionDTO datos de creacion.
     * @return respuesta con la validacion creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new campo validacion", description = "Creates a new campo validacion with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoValidacionDTO>> createCampoValidacion(@RequestBody CreateCampoValidacionDTO createCampoValidacionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.createCampoValidacion(createCampoValidacionDTO)));
    }

    /**
     * Actualiza una validacion de campo existente.
     * @param id identificador de la validacion.
     * @param updateCampoValidacionDTO datos actualizados.
     * @return respuesta con la validacion actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campo validacion", description = "Updates the campo validacion with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoValidacionDTO>> updateCampoValidacion(@PathVariable Long id, @RequestBody UpdateCampoValidacionDTO updateCampoValidacionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.updateCampoValidacion(id, updateCampoValidacionDTO)));
    }

    /**
     * Obtiene la lista completa de validaciones de campo.
     * @return respuesta con el listado de validaciones.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all campo validaciones", description = "Retrieves a list of all campo validaciones")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoValidacionDTO>>> getAllCampoValidaciones() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.getAllCampoValidaciones()));
    }

    /**
     * Obtiene una validacion de campo por identificador.
     * @param id identificador de la validacion.
     * @return respuesta con la validacion encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a campo validacion by ID", description = "Retrieves a specific campo validacion by its ID")
    public ResponseEntity<RespuestaDTO<ResponseCampoValidacionDTO>> getCampoValidacionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.getCampoValidacionById(id)));
    }

    /**
     * Obtiene las validaciones asociadas a un campo.
     * @param campoId identificador del campo.
     * @return respuesta con las validaciones relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{campoId}/campo")
    @Operation(summary = "Get campo validaciones by campo", description = "Retrieves all campo validaciones for a specific campo")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoValidacionDTO>>> getCampoValidacionesByCampo(@PathVariable Long campoId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoValidacionService.getCampoValidacionesByCampo(campoId)));
    }

}
