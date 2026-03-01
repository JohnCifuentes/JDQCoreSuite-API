package uq.com.jdq.coresuite.sistema.empresa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sistema/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping
    @Operation(summary = "Create a new empresa", description = "Creates a new empresa with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> createEmpresa(@RequestBody CreateEmpresaDTO createEmpresaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.createEmpresa(createEmpresaDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing empresa", description = "Updates the empresa with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> updateEmpresa(@PathVariable Long id, @RequestBody UpdateEmpresaDTO updateEmpresaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.updateEmpresa(id, updateEmpresaDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a empresa", description = "Inactive the empresa with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> inactiveEmpresa(@PathVariable Long id, @RequestBody InactiveEmpresaDTO inactiveEmpresaDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.inactiveEmpresa(id, inactiveEmpresaDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all empresas", description = "Retrieves a list of all empresas")
    public ResponseEntity<RespuestaDTO<List<ResponseEmpresaDTO>>> getAllEmpresas() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.getAllEmpresas()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a empresa by ID", description = "Retrieves a specific empresa by its ID")
    public ResponseEntity<RespuestaDTO<ResponseEmpresaDTO>> getEmpresaById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.empresaService.getEmpresaById(id)));
    }

}
