package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de grupos de campos por interfaz.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/interface-grupo-campos")
public class InterfaceGrupoCamposController {

    private final InterfaceGrupoCamposService interfaceGrupoCamposService;

    /**
     * Crea un nuevo grupo de campos asociado a una interfaz.
     * @param createInterfaceGrupoCamposDTO datos de creacion.
     * @return respuesta con el grupo de campos creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new interface grupo campos", description = "Creates a new interface grupo campos with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseInterfaceGrupoCamposDTO>> createInterfaceGrupoCampos(@RequestBody CreateInterfaceGrupoCamposDTO createInterfaceGrupoCamposDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfaceGrupoCamposService.createInterfaceGrupoCampos(createInterfaceGrupoCamposDTO)));
    }

    /**
     * Actualiza un grupo de campos existente.
     * @param id identificador del grupo de campos.
     * @param updateInterfaceGrupoCamposDTO datos actualizados.
     * @return respuesta con el grupo de campos actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing interface grupo campos", description = "Updates the interface grupo campos with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseInterfaceGrupoCamposDTO>> updateInterfaceGrupoCampos(@PathVariable Long id, @RequestBody UpdateInterfaceGrupoCamposDTO updateInterfaceGrupoCamposDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfaceGrupoCamposService.updateInterfaceGrupoCampos(id, updateInterfaceGrupoCamposDTO)));
    }

    /**
     * Obtiene la lista completa de grupos de campos.
     * @return respuesta con el listado de grupos de campos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all interface grupo campos", description = "Retrieves a list of all interface grupo campos")
    public ResponseEntity<RespuestaDTO<List<ResponseInterfaceGrupoCamposDTO>>> getAllInterfaceGrupoCampos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfaceGrupoCamposService.getAllInterfaceGrupoCampos()));
    }

    /**
     * Obtiene un grupo de campos por identificador.
     * @param id identificador del grupo de campos.
     * @return respuesta con el grupo de campos encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get an interface grupo campos by ID", description = "Retrieves a specific interface grupo campos by its ID")
    public ResponseEntity<RespuestaDTO<ResponseInterfaceGrupoCamposDTO>> getInterfaceGrupoCamposById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfaceGrupoCamposService.getInterfaceGrupoCamposById(id)));
    }

    /**
     * Obtiene los grupos de campos asociados a una interfaz.
     * @param interfazId identificador de la interfaz.
     * @return respuesta con los grupos de campos relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{interfazId}/interfaz")
    @Operation(summary = "Get interface grupo campos by interfaz", description = "Retrieves all interface grupo campos for a specific interfaz")
    public ResponseEntity<RespuestaDTO<List<ResponseInterfaceGrupoCamposDTO>>> getInterfaceGrupoCamposByInterfaz(@PathVariable Long interfazId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.interfaceGrupoCamposService.getInterfaceGrupoCamposByInterfaz(interfazId)));
    }

}
