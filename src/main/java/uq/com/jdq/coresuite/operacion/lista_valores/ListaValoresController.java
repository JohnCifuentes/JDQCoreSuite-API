package uq.com.jdq.coresuite.operacion.lista_valores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion y consulta de listas de valores.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operacion/lista-valores")
public class ListaValoresController {

    private final ListaValoresService listaValoresService;

    /**
     * Crea una nueva lista de valores.
     * @param createListaValoresDTO datos de creacion.
     * @return respuesta con la lista de valores creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Create a new lista valores", description = "Creates a new lista valores with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDTO>> createListaValores(@RequestBody CreateListaValoresDTO createListaValoresDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.createListaValores(createListaValoresDTO)));
    }

    /**
     * Actualiza una lista de valores existente.
     * @param id identificador de la lista.
     * @param updateListaValoresDTO datos actualizados.
     * @return respuesta con la lista de valores actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing lista valores", description = "Updates the lista valores with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDTO>> updateListaValores(@PathVariable Long id, @RequestBody UpdateListaValoresDTO updateListaValoresDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.updateListaValores(id, updateListaValoresDTO)));
    }

    /**
     * Obtiene la lista completa de listas de valores.
     * @return respuesta con el listado de listas de valores.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all lista valores", description = "Retrieves a list of all lista valores")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDTO>>> getAllListaValores() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.getAllListaValores()));
    }

    /**
     * Obtiene una lista de valores por identificador.
     * @param id identificador de la lista.
     * @return respuesta con la lista de valores encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Get a lista valores by ID", description = "Retrieves a specific lista valores by its ID")
    public ResponseEntity<RespuestaDTO<ResponseListaValoresDTO>> getListaValoresById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.getListaValoresById(id)));
    }

    /**
     * Obtiene las listas de valores asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return respuesta con las listas de valores relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "Get lista valores by empresa", description = "Retrieves the lista valores for a given empresa")
    public ResponseEntity<RespuestaDTO<List<ResponseListaValoresDTO>>> getListaValoresByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.listaValoresService.getListaValoresByEmpresa(empresaId)));
    }

}
