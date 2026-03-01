package uq.com.jdq.coresuite.sistema.licencia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/sistema/licencia")
public class LicenciaController {

    private final LicenciaService licenciaService;

    @PostMapping
    @Operation(summary = "Create a new licencia", description = "Creates a new licencia with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> createLicencia(@RequestBody CreateLicenciaDTO createLicenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.createLicencia(createLicenciaDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing licencia", description = "Updates the licencia with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> updateLicencia(@PathVariable Long id, @RequestBody UpdateLicenciaDTO updateLicenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.updateLicencia(id, updateLicenciaDTO)));
    }

    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a licencia", description = "Inactive the licencia with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> inactiveLicencia(@PathVariable Long id, @RequestBody InactiveLicenciaDTO inactiveLicenciaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.inactiveLicencia(id, inactiveLicenciaDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all licencias", description = "Retrieves a list of all licencias")
    public ResponseEntity<RespuestaDTO<List<ResponseLicenciaDTO>>> getAllLicencias() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.getAllLicencias()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a licencia by ID", description = "Retrieves a specific licencia by its ID")
    public ResponseEntity<RespuestaDTO<ResponseLicenciaDTO>> getLicenciaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.getLicenciaById(id)));
    }

    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "Get a licencia by ID", description = "Retrieves a specific licencia by its ID")
    public ResponseEntity<RespuestaDTO<List<ResponseLicenciaDTO>>> getLicenciasByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.licenciaService.getLicenciasByEmpresa(empresaId)));
    }

}
