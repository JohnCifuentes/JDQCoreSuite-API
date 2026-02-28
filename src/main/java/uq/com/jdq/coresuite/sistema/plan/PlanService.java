package uq.com.jdq.coresuite.sistema.plan;

import java.util.List;

public interface PlanService {

    ResponsePlanDTO createPlan(CreatePlanDTO createPlanDTO) throws Exception;

    ResponsePlanDTO updatePlan(Long id, UpdatePlanDTO updatePlanDTO) throws Exception;

    ResponsePlanDTO inactivePlan(Long id, InactivePlanDTO inactivePlanDTO) throws Exception;

    List<ResponsePlanDTO> getAllPlanes() throws Exception;

    ResponsePlanDTO getPlanById(Long id) throws Exception;

}
