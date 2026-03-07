package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/lista-valores-detalle")
public class ListaValoresDetalleController {

    private final ListaValoresDetalleService listaValoresDetalleService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new lista valores detalle", description = "Creates a new lista valores detalle with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDetalleDTO>> createListaValoresDetalle(@RequestBody CreateListaValoresDetalleDTO createListaValoresDetalleDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.createListaValoresDetalle(createListaValoresDetalleDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing lista valores detalle", description = "Updates the lista valores detalle with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDetalleDTO>> updateListaValoresDetalle(@PathVariable Long id, @RequestBody UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.updateListaValoresDetalle(id, updateListaValoresDetalleDTO)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all lista valores detalle", description = "Retrieves a list of all lista valores detalle")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDetalleDTO>>> getAllListaValoresDetalle() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.getAllListaValoresDetalle()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a lista valores detalle by ID", description = "Retrieves a specific lista valores detalle by its ID")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDetalleDTO>> getListaValoresDetalleById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.getListaValoresDetalleById(id)));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{listaValoresId}/lista-valores")
    @Operation(summary = "Get lista valores detalle by lista valores", description = "Retrieves the lista valores detalle for a given lista valores")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDetalleDTO>>> getListaValoresDetalleByListaValores(@PathVariable Long listaValoresId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.getListaValoresDetalleByListaValores(listaValoresId)));
    }

}
