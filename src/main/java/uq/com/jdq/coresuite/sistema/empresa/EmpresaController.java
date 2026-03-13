package uq.com.jdq.coresuite.sistema.empresa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion de empresas.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sistema/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    /**
     * Crea una nueva empresa.
     * @param createEmpresaDTO datos de la empresa a registrar.
     * @return respuesta con la empresa creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @PostMapping
    @Operation(summary = "Create a new empresa", description = "Creates a new empresa with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> createEmpresa(@RequestBody CreateEmpresaDTO createEmpresaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.createEmpresa(createEmpresaDTO)));
    }

    /**
     * Actualiza una empresa existente.
     * @param id identificador de la empresa.
     * @param updateEmpresaDTO nuevos datos de la empresa.
     * @return respuesta con la empresa actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing empresa", description = "Updates the empresa with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> updateEmpresa(@PathVariable Long id, @RequestBody UpdateEmpresaDTO updateEmpresaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.updateEmpresa(id, updateEmpresaDTO)));
    }

    /**
     * Inactiva una empresa.
     * @param id identificador de la empresa.
     * @param inactiveEmpresaDTO datos del cambio de estado.
     * @return respuesta con la empresa inactivada.
     * @throws Exception si ocurre un error durante la inactivacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a empresa", description = "Inactive the empresa with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> inactiveEmpresa(@PathVariable Long id, @RequestBody InactiveEmpresaDTO inactiveEmpresaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.inactiveEmpresa(id, inactiveEmpresaDTO)));
    }

    /**
     * Obtiene todas las empresas registradas.
     * @return respuesta con la lista de empresas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all empresas", description = "Retrieves a list of all empresas")
    public ResponseEntity<RespuestaDTO<List<ResponseEmpresaDTO>>> getAllEmpresas() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.getAllEmpresas()));
    }

    /**
     * Consulta una empresa por su identificador.
     * @param id identificador de la empresa.
     * @return respuesta con la empresa encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a empresa by ID", description = "Retrieves a specific empresa by its ID")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> getEmpresaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.getEmpresaById(id)));
    }

}
