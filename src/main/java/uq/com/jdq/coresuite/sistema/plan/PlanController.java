package uq.com.jdq.coresuite.sistema.plan;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uq.com.jdq.coresuite.config.RespuestaDTO;

import java.util.List;

@RestController
@RequestMapping("api/sistema/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    @Operation(summary = "Create a new plan", description = "Creates a new plan with the provided data")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> createPlan(@RequestBody CreatePlanDTO createPlanDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.planService.createPlan(createPlanDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing plan", description = "Updates the plan with the specified ID using the provided data")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> updatePlan(@PathVariable Long id, @RequestBody UpdatePlanDTO updatePlanDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.planService.updatePlan(id, updatePlanDTO)));
    }

    @PutMapping("/{id}/inactive")
    @Operation(summary = "Inactive a plan", description = "Inactive the plan with the specified ID")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> inactivePlan(@PathVariable Long id, @RequestBody InactivePlanDTO inactivePlanDTO) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, this.planService.inactivePlan(id, inactivePlanDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all planes", description = "Retrieves a list of all plans")
    public ResponseEntity<RespuestaDTO<List<ResponsePlanDTO>>> getAllPlanes() throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, planService.getAllPlanes()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a plan by ID", description = "Retrieves a specific plan by its ID")
    public ResponseEntity<RespuestaDTO<ResponsePlanDTO>> getPlanById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new RespuestaDTO<>(false, planService.getPlanById(id)));
    }

}
