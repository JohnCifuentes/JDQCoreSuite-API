package uq.com.jdq.coresuite.operacion.tipo_campo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/tipo-campo")
public class TipoCampoController {

    private final TipoCampoService tipoCampoService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new tipo campo", description = "Creates a new tipo campo with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseTipoCampoDTO>> createTipoCampo(@RequestBody CreateTipoCampoDTO createTipoCampoDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoCampoService.createTipoCampo(createTipoCampoDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing tipo campo", description = "Updates the tipo campo with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseTipoCampoDTO>> updateTipoCampo(@PathVariable Long id, @RequestBody UpdateTipoCampoDTO updateTipoCampoDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoCampoService.updateTipoCampo(id, updateTipoCampoDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all tipo campos", description = "Retrieves a list of all tipo campos")
    public ResponseEntity<RespuestaDTO<List<ResponseTipoCampoDTO>>> getAllTipoCampos() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoCampoService.getAllTipoCampos()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a tipo campo by ID", description = "Retrieves a specific tipo campo by its ID")
    public ResponseEntity<RespuestaDTO<ResponseTipoCampoDTO>> getTipoCampoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.tipoCampoService.getTipoCampoById(id)));
    }

}
