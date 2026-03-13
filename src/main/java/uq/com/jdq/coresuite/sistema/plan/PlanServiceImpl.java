package uq.com.jdq.coresuite.sistema.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio encargado de administrar planes.
 */
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    /**
     * Crea un nuevo plan.
     * @param createPlanDTO datos del plan.
     * @return plan creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @Override
    @Transactional
    public ResponsePlanDTO createPlan(CreatePlanDTO createPlanDTO) throws Exception {
        Plan plan = planMapper.toEntity(createPlanDTO);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    /**
     * Actualiza un plan existente.
     * @param id identificador del plan.
     * @param updatePlanDTO nuevos datos del plan.
     * @return plan actualizado.
     * @throws Exception si el plan no existe.
     */
    @Override
    @Transactional
    public ResponsePlanDTO updatePlan(Long id, UpdatePlanDTO updatePlanDTO) throws Exception {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el plan"));
        planMapper.updateEntityFromDTO(updatePlanDTO, plan);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    /**
     * Cambia el estado de un plan.
     * @param id identificador del plan.
     * @param inactivePlanDTO datos del nuevo estado.
     * @return plan actualizado.
     * @throws Exception si el plan no existe.
     */
    @Override
    @Transactional
    public ResponsePlanDTO inactivePlan(Long id, InactivePlanDTO inactivePlanDTO) throws Exception {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el plan"));
        planMapper.inactiveEntityFromDTO(inactivePlanDTO, plan);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    /**
     * Obtiene todos los planes registrados.
     * @return lista de planes.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponsePlanDTO> getAllPlanes() throws Exception {
        return planRepository.findAll().stream()
                .map(planMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Consulta un plan por identificador.
     * @param id identificador del plan.
     * @return plan encontrado.
     * @throws Exception si el plan no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponsePlanDTO getPlanById(Long id) throws Exception {
        return planRepository.findById(id)
                .map(planMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el plan"));
    }

}
