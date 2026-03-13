package uq.com.jdq.coresuite.sistema.plan;

import java.util.List;

/**
 * Contrato de negocio para la gestion de planes.
 */
public interface PlanService {

    /**
     * Registra un nuevo plan.
     * @param createPlanDTO datos del plan.
     * @return plan creado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponsePlanDTO createPlan(CreatePlanDTO createPlanDTO) throws Exception;

    /**
     * Actualiza un plan existente.
     * @param id identificador del plan.
     * @param updatePlanDTO nuevos datos del plan.
     * @return plan actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponsePlanDTO updatePlan(Long id, UpdatePlanDTO updatePlanDTO) throws Exception;

    /**
     * Inactiva un plan.
     * @param id identificador del plan.
     * @param inactivePlanDTO datos del cambio de estado.
     * @return plan actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponsePlanDTO inactivePlan(Long id, InactivePlanDTO inactivePlanDTO) throws Exception;

    /**
     * Lista todos los planes.
     * @return lista de planes.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponsePlanDTO> getAllPlanes() throws Exception;

    /**
     * Consulta un plan por identificador.
     * @param id identificador del plan.
     * @return plan encontrado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponsePlanDTO getPlanById(Long id) throws Exception;

}
