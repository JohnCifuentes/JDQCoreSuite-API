package uq.com.jdq.coresuite.sistema.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Override
    @Transactional
    public ResponsePlanDTO createPlan(CreatePlanDTO createPlanDTO) {
        Plan plan = planMapper.toEntity(createPlanDTO);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    @Override
    @Transactional
    public ResponsePlanDTO updatePlan(Long id, UpdatePlanDTO updatePlanDTO) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
        planMapper.updateEntityFromDTO(updatePlanDTO, plan);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    @Override
    @Transactional
    public ResponsePlanDTO inactivePlan(Long id, InactivePlanDTO inactivePlanDTO) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
        planMapper.inactiveEntityFromDTO(inactivePlanDTO, plan);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponsePlanDTO> getAllPlanes() {
        return planRepository.findAll().stream()
                .map(planMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponsePlanDTO getPlanById(Long id) {
        return planRepository.findById(id)
                .map(planMapper::toDTO)
                .orElse(null);
    }

}
