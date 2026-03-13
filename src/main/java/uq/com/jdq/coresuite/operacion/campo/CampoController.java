package uq.com.jdq.coresuite.operacion.campo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de campos.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/campo")
public class CampoController {

    private final CampoService campoService;

    /**
     * Crea un nuevo campo.
     * @param createCampoDTO datos de creacion del campo.
     * @return respuesta con el campo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new campo", description = "Creates a new campo with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDTO>> createCampo(@RequestBody CreateCampoDTO createCampoDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.createCampo(createCampoDTO)));
    }

    /**
     * Actualiza un campo existente.
     * @param id identificador del campo.
     * @param updateCampoDTO datos actualizados del campo.
     * @return respuesta con el campo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campo", description = "Updates the campo with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseCampoDTO>> updateCampo(@PathVariable Long id, @RequestBody UpdateCampoDTO updateCampoDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.updateCampo(id, updateCampoDTO)));
    }

    /**
     * Obtiene la lista completa de campos.
     * @return respuesta con el listado de campos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all campos", description = "Retrieves a list of all campos")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDTO>>> getAllCampos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.getAllCampos()));
    }

    /**
     * Obtiene un campo por su identificador.
     * @param id identificador del campo.
     * @return respuesta con el campo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a campo by ID", description = "Retrieves a specific campo by its ID")
    public ResponseEntity<RespuestaDTO<ResponseCampoDTO>> getCampoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.getCampoById(id)));
    }

    /**
     * Obtiene los campos asociados a una interfaz.
     * @param interfazId identificador de la interfaz.
     * @return respuesta con los campos de la interfaz.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{interfazId}/interfaz")
    @Operation(summary = "Get campos by interfaz", description = "Retrieves all campos for a specific interfaz")
    public ResponseEntity<RespuestaDTO<List<ResponseCampoDTO>>> getCamposByInterfaz(@PathVariable Long interfazId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.campoService.getCamposByInterfaz(interfazId)));
    }

}
