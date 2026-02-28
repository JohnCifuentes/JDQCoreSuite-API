package uq.com.jdq.coresuite.sistema.sesion;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequestMapping("api/sistema/sesion")
@RequiredArgsConstructor
public class SesionController {

    private final SesionService sesionService;

    @PostMapping
    @Operation(summary = "Create a new sesion", description = "Creates a new sesion with the provided data")
    public ResponseEntity<RespuestaDTO<ResponseSesionDTO>> createSesion(@RequestBody CreateSesionDTO createSesionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.createSesion(createSesionDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing sesion", description = "Updates the sesion with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponseSesionDTO>> updateSesion(@PathVariable Long id, @RequestBody UpdateSesionDTO updateSesionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.updateSesion(id, updateSesionDTO)));
    }

    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a sesion", description = "Inactive the sesion with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponseSesionDTO>> inactiveSesion(@PathVariable Long id, @RequestBody InactiveSesionDTO inactiveSesionDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.inactiveSesion(id, inactiveSesionDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all sesiones", description = "Retrieves a list of all sesiones")
    public ResponseEntity<RespuestaDTO<List<ResponseSesionDTO>>> getAllSesiones() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.getAllSesiones()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a sesion by ID", description = "Retrieves a specific sesion by its ID")
    public ResponseEntity<RespuestaDTO<ResponseSesionDTO>> getSesionById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.getSesionById(id)));
    }

    @GetMapping("/{empresaId}/empresa")
    @Operation(summary = "", description = "")
    public ResponseEntity<RespuestaDTO<List<ResponseSesionDTO>>> getAllSesionesByEmpresa(@PathVariable Long empresaId) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.sesionService.getSesionesByEmpresa(empresaId)));
    }

}
