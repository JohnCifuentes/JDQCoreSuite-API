package uq.com.jdq.coresuite.operacion.lista_valores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/lista-valores")
public class ListaValoresController {

    private final ListaValoresService listaValoresService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new lista valores", description = "Creates a new lista valores with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDTO>> createListaValores(@RequestBody CreateListaValoresDTO createListaValoresDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.createListaValores(createListaValoresDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing lista valores", description = "Updates the lista valores with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDTO>> updateListaValores(@PathVariable Long id, @RequestBody UpdateListaValoresDTO updateListaValoresDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.updateListaValores(id, updateListaValoresDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all lista valores", description = "Retrieves a list of all lista valores")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDTO>>> getAllListaValores() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.getAllListaValores()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a lista valores by ID", description = "Retrieves a specific lista valores by its ID")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDTO>> getListaValoresById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.getListaValoresById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "Get lista valores by empresa", description = "Retrieves the lista valores for a given empresa")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDTO>>> getListaValoresByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.getListaValoresByEmpresa(empresaId)));
    }

}
