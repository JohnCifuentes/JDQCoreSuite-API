package uq.com.jdq.coresuite.operacion.tipo_validacion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de tipos de validacion.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/tipo-validacion")
public class TipoValidacionController {

    private final TipoValidacionService tipoValidacionService;

    /**
     * Crea un nuevo tipo de validacion.
     * @param createTipoValidacionDTO datos de creacion.
     * @return respuesta con el tipo de validacion creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new tipo validacion", description = "Creates a new tipo validacion with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseTipoValidacionDTO>> createTipoValidacion(@RequestBody CreateTipoValidacionDTO createTipoValidacionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoValidacionService.createTipoValidacion(createTipoValidacionDTO)));
    }

    /**
     * Actualiza un tipo de validacion existente.
     * @param id identificador del tipo de validacion.
     * @param updateTipoValidacionDTO datos actualizados.
     * @return respuesta con el tipo de validacion actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing tipo validacion", description = "Updates the tipo validacion with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseTipoValidacionDTO>> updateTipoValidacion(@PathVariable Long id, @RequestBody UpdateTipoValidacionDTO updateTipoValidacionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoValidacionService.updateTipoValidacion(id, updateTipoValidacionDTO)));
    }

    /**
     * Obtiene la lista completa de tipos de validacion.
     * @return respuesta con el listado de tipos de validacion.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all tipos validacion", description = "Retrieves a list of all tipos validacion")
    public ResponseEntity<RespuestaDTO<List<ResponseTipoValidacionDTO>>> getAllTipoValidaciones() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoValidacionService.getAllTipoValidaciones()));
    }

    /**
     * Obtiene un tipo de validacion por identificador.
     * @param id identificador del tipo de validacion.
     * @return respuesta con el tipo de validacion encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a tipo validacion by ID", description = "Retrieves a specific tipo validacion by its ID")
    public ResponseEntity<RespuestaDTO<ResponseTipoValidacionDTO>> getTipoValidacionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoValidacionService.getTipoValidacionById(id)));
    }

}
