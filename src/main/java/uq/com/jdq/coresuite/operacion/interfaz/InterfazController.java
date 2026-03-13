package uq.com.jdq.coresuite.operacion.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de interfaces.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/interfaz")
public class InterfazController {

    private final InterfazService interfazService;

    /**
     * Crea una nueva interfaz.
     * @param createInterfazDTO datos de creacion.
     * @return respuesta con la interfaz creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new interfaz", description = "Creates a new interfaz with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseInterfazDTO>> createInterfaz(@RequestBody CreateInterfazDTO createInterfazDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.createInterfaz(createInterfazDTO)));
    }

    /**
     * Actualiza una interfaz existente.
     * @param id identificador de la interfaz.
     * @param updateInterfazDTO datos actualizados.
     * @return respuesta con la interfaz actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing interfaz", description = "Updates the interfaz with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseInterfazDTO>> updateInterfaz(@PathVariable Long id, @RequestBody UpdateInterfazDTO updateInterfazDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.updateInterfaz(id, updateInterfazDTO)));
    }

    /**
     * Obtiene la lista completa de interfaces.
     * @return respuesta con el listado de interfaces.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all interfaz", description = "Retrieves a list of all interfaz")
    public ResponseEntity<RespuestaDTO<List<ResponseInterfazDTO>>> getAllInterfaz() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.getAllInterfaz()));
    }

    /**
     * Obtiene una interfaz por identificador.
     * @param id identificador de la interfaz.
     * @return respuesta con la interfaz encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get an interfaz by ID", description = "Retrieves a specific interfaz by its ID")
    public ResponseEntity<RespuestaDTO<ResponseInterfazDTO>> getInterfazById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.getInterfazById(id)));
    }

    /**
     * Obtiene las interfaces asociadas a un modulo.
     * @param moduloId identificador del modulo.
     * @return respuesta con las interfaces relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{moduloId}/modulo")
    @Operation(summary = "Get interfaz by modulo", description = "Retrieves all interfaz for a specific modulo")
    public ResponseEntity<RespuestaDTO<List<ResponseInterfazDTO>>> getInterfazByModulo(@PathVariable Long moduloId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfazService.getInterfazByModulo(moduloId)));
    }

}
