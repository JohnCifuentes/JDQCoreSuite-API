package uq.com.jdq.coresuite.sistema.licencia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la gestion de licencias.
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/sistema/licencia")
public class LicenciaController {

    private final LicenciaService licenciaService;

    /**
     * Crea una nueva licencia.
     * @param createLicenciaDTO datos de la licencia.
     * @return respuesta con la licencia creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @PostMapping
    @Operation(summary = "Create a new licencia", description = "Creates a new licencia with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> createLicencia(@RequestBody CreateLicenciaDTO createLicenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.createLicencia(createLicenciaDTO)));
    }

    /**
     * Actualiza una licencia existente.
     * @param id identificador de la licencia.
     * @param updateLicenciaDTO nuevos datos de la licencia.
     * @return respuesta con la licencia actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing licencia", description = "Updates the licencia with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> updateLicencia(@PathVariable Long id, @RequestBody UpdateLicenciaDTO updateLicenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.updateLicencia(id, updateLicenciaDTO)));
    }

    /**
     * Inactiva una licencia.
     * @param id identificador de la licencia.
     * @param inactiveLicenciaDTO datos del cambio de estado.
     * @return respuesta con la licencia inactivada.
     * @throws Exception si ocurre un error durante la inactivacion.
     */
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a licencia", description = "Inactive the licencia with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> inactiveLicencia(@PathVariable Long id, @RequestBody InactiveLicenciaDTO inactiveLicenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.inactiveLicencia(id, inactiveLicenciaDTO)));
    }

    /**
     * Obtiene todas las licencias registradas.
     * @return respuesta con la lista de licencias.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    @Operation(summary = "Get all licencias", description = "Retrieves a list of all licencias")
    public ResponseEntity<RespuestaDTO<List<ResponseLicenciaDTO>>> getAllLicencias() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.getAllLicencias()));
    }

    /**
     * Consulta una licencia por su identificador.
     * @param id identificador de la licencia.
     * @return respuesta con la licencia encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a licencia by ID", description = "Retrieves a specific licencia by its ID")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> getLicenciaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.getLicenciaById(id)));
    }

    /**
     * Obtiene las licencias asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return respuesta con la lista de licencias.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "Get a licencia by ID", description = "Retrieves a specific licencia by its ID")
    public ResponseEntity<RespuestaDTO<List<ResponseLicenciaDTO>>> getLicenciasByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.getLicenciasByEmpresa(empresaId)));
    }

}
