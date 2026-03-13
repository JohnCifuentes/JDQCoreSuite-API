package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de detalles de listas de valores.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/lista-valores-detalle")
public class ListaValoresDetalleController {

    private final ListaValoresDetalleService listaValoresDetalleService;

    /**
     * Crea un nuevo detalle de lista de valores.
     * @param createListaValoresDetalleDTO datos de creacion.
     * @return respuesta con el detalle creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new lista valores detalle", description = "Creates a new lista valores detalle with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDetalleDTO>> createListaValoresDetalle(@RequestBody CreateListaValoresDetalleDTO createListaValoresDetalleDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.createListaValoresDetalle(createListaValoresDetalleDTO)));
    }

    /**
     * Actualiza un detalle de lista de valores existente.
     * @param id identificador del detalle.
     * @param updateListaValoresDetalleDTO datos actualizados.
     * @return respuesta con el detalle actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing lista valores detalle", description = "Updates the lista valores detalle with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDetalleDTO>> updateListaValoresDetalle(@PathVariable Long id, @RequestBody UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.updateListaValoresDetalle(id, updateListaValoresDetalleDTO)));
    }

    /**
     * Obtiene la lista completa de detalles de listas de valores.
     * @return respuesta con el listado de detalles.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all lista valores detalle", description = "Retrieves a list of all lista valores detalle")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDetalleDTO>>> getAllListaValoresDetalle() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.getAllListaValoresDetalle()));
    }

    /**
     * Obtiene un detalle de lista de valores por identificador.
     * @param id identificador del detalle.
     * @return respuesta con el detalle encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a lista valores detalle by ID", description = "Retrieves a specific lista valores detalle by its ID")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDetalleDTO>> getListaValoresDetalleById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.getListaValoresDetalleById(id)));
    }

    /**
     * Obtiene los detalles asociados a una lista de valores.
     * @param listaValoresId identificador de la lista.
     * @return respuesta con los detalles relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{listaValoresId}/lista-valores")
    @Operation(summary = "Get lista valores detalle by lista valores", description = "Retrieves the lista valores detalle for a given lista valores")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDetalleDTO>>> getListaValoresDetalleByListaValores(@PathVariable Long listaValoresId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresDetalleService.getListaValoresDetalleByListaValores(listaValoresId)));
    }

}
