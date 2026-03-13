package uq.com.jdq.coresuite.operacion.campo_dependencia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de dependencias de campo.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/campo-dependencia")
public class CampoDependenciaController {

    private final CampoDependenciaService campoDependenciaService;

    /**
     * Crea una nueva dependencia entre campos.
     * @param createCampoDependenciaDTO datos de creacion de la dependencia.
     * @return respuesta con la dependencia creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new campo dependencia", description = "Creates a new campo dependencia with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDependenciaDTO>> createCampoDependencia(@RequestBody CreateCampoDependenciaDTO createCampoDependenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.createCampoDependencia(createCampoDependenciaDTO)));
    }

    /**
     * Actualiza una dependencia de campo existente.
     * @param id identificador de la dependencia.
     * @param updateCampoDependenciaDTO datos actualizados de la dependencia.
     * @return respuesta con la dependencia actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campo dependencia", description = "Updates the campo dependencia with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDependenciaDTO>> updateCampoDependencia(@PathVariable Long id, @RequestBody UpdateCampoDependenciaDTO updateCampoDependenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.updateCampoDependencia(id, updateCampoDependenciaDTO)));
    }

    /**
     * Obtiene la lista completa de dependencias de campo.
     * @return respuesta con el listado de dependencias.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all campo dependencias", description = "Retrieves a list of all campo dependencias")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDependenciaDTO>>> getAllCampoDependencias() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.getAllCampoDependencias()));
    }

    /**
     * Obtiene una dependencia de campo por su identificador.
     * @param id identificador de la dependencia.
     * @return respuesta con la dependencia encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a campo dependencia by ID", description = "Retrieves a specific campo dependencia by its ID")
    public ResponseEntity<RespuestaDTO<ResponseCampoDependenciaDTO>> getCampoDependenciaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.getCampoDependenciaById(id)));
    }

    /**
     * Obtiene las dependencias asociadas a un campo.
     * @param campoId identificador del campo.
     * @return respuesta con las dependencias del campo.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{campoId}/campo")
    @Operation(summary = "Get campo dependencias by campo", description = "Retrieves all campo dependencias for a specific campo")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDependenciaDTO>>> getCampoDependenciasByCampo(@PathVariable Long campoId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoDependenciaService.getCampoDependenciasByCampo(campoId)));
    }

}
