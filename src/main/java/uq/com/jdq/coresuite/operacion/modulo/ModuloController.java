package uq.com.jdq.coresuite.operacion.modulo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de modulos.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/modulo")
public class ModuloController {

    private final ModuloService moduloService;

    /**
     * Crea un nuevo modulo.
     * @param createModuloDTO datos de creacion.
     * @return respuesta con el modulo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new modulo", description = "Creates a new modulo with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseModuloDTO>> createModulo(@RequestBody CreateModuloDTO createModuloDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.createModulo(createModuloDTO)));
    }

    /**
     * Actualiza un modulo existente.
     * @param id identificador del modulo.
     * @param updateModuloDTO datos actualizados.
     * @return respuesta con el modulo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing modulo", description = "Updates the modulo with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseModuloDTO>> updateModulo(@PathVariable Long id, @RequestBody UpdateModuloDTO updateModuloDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.updateModulo(id, updateModuloDTO)));
    }

    /**
     * Obtiene la lista completa de modulos.
     * @return respuesta con el listado de modulos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all modulos", description = "Retrieves a list of all modulos")
    public ResponseEntity<RespuestaDTO<List<ResponseModuloDTO>>> getAllModulos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.getAllModulos()));
    }

    /**
     * Obtiene un modulo por identificador.
     * @param id identificador del modulo.
     * @return respuesta con el modulo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a modulo by ID", description = "Retrieves a specific modulo by its ID")
    public ResponseEntity<RespuestaDTO<ResponseModuloDTO>> getModuloById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.getModuloById(id)));
    }

    /**
     * Obtiene los modulos asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return respuesta con los modulos relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "Get modulos by empresa", description = "Retrieves the modulos for a given empresa")
    public ResponseEntity<RespuestaDTO<List<ResponseModuloDTO>>> getModulosByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.moduloService.getModulosByEmpresa(empresaId)));
    }

}
