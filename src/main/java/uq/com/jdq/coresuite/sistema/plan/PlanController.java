package uq.com.jdq.coresuite.sistema.plan;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

/**
 * Controlador REST para la administracion de planes.
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/sistema/plan")
public class PlanController {

    private final PlanService planService;

    /**
     * Crea un nuevo plan.
     * @param createPlanDTO datos del plan.
     * @return respuesta con el plan creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @PostMapping
    @Operation(summary = "Create a new plan", description = "Creates a new plan with the provided data")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> createPlan(@RequestBody CreatePlanDTO createPlanDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.planService.createPlan(createPlanDTO)));
    }

    /**
     * Actualiza un plan existente.
     * @param id identificador del plan.
     * @param updatePlanDTO nuevos datos del plan.
     * @return respuesta con el plan actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing plan", description = "Updates the plan with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> updatePlan(@PathVariable Long id, @RequestBody UpdatePlanDTO updatePlanDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.planService.updatePlan(id, updatePlanDTO)));
    }

    /**
     * Inactiva un plan.
     * @param id identificador del plan.
     * @param inactivePlanDTO datos del cambio de estado.
     * @return respuesta con el plan inactivado.
     * @throws Exception si ocurre un error durante la inactivacion.
     */
    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a plan", description = "Inactive the plan with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> inactivePlan(@PathVariable Long id, @RequestBody InactivePlanDTO inactivePlanDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.planService.inactivePlan(id, inactivePlanDTO)));
    }

    /**
     * Obtiene todos los planes registrados.
     * @return respuesta con la lista de planes.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping
    @Operation(summary = "Get all planes", description = "Retrieves a list of all plans")
    public ResponseEntity<RespuestaDTO<List<ResponsePlanDTO>>> getAllPlanes() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, planService.getAllPlanes()));
    }

    /**
     * Consulta un plan por su identificador.
     * @param id identificador del plan.
     * @return respuesta con el plan encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a plan by ID", description = "Retrieves a specific plan by its ID")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> getPlanById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, planService.getPlanById(id)));
    }

}
