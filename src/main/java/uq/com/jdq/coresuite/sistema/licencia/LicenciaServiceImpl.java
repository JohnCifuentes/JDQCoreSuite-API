package uq.com.jdq.coresuite.sistema.licencia;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;
import uq.com.jdq.coresuite.sistema.empresa.EmpresaRepository;
import uq.com.jdq.coresuite.sistema.plan.Plan;
import uq.com.jdq.coresuite.sistema.plan.PlanRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicenciaServiceImpl implements LicenciaService {

    private final LicenciaRepository licenciaRepository;
    private final LicenciaMapper licenciaMapper;
    private final EmpresaRepository empresaRepository;
    private final PlanRepository planRepository;

    @Override
    @Transactional
    public ResponseLicenciaDTO createLicencia(CreateLicenciaDTO createLicenciaDTO) {
        Licencia licencia = licenciaMapper.toEntity(createLicenciaDTO);
        Empresa empresa = empresaRepository.findById(createLicenciaDTO.empresaId()).orElseThrow(
                () -> new RuntimeException("Empresa no encontrada")
        );
        Plan plan = planRepository.findById(createLicenciaDTO.planId()).orElseThrow(
                () -> new RuntimeException("Plan no encontrado")
        );
        licencia.setEmpresa(empresa);
        licencia.setPlan(plan);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

    @Override
    @Transactional
    public ResponseLicenciaDTO updateLicencia(Long id, UpdateLicenciaDTO updateLicenciaDTO) {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
        Empresa empresa = empresaRepository.findById(updateLicenciaDTO.empresaId()).orElseThrow(
                () -> new RuntimeException("Empresa no encontrada")
        );
        Plan plan = planRepository.findById(updateLicenciaDTO.planId()).orElseThrow(
                () -> new RuntimeException("Plan no encontrado")
        );
        licenciaMapper.updateEntityFromDTO(updateLicenciaDTO, licencia);
        licencia.setEmpresa(empresa);
        licencia.setPlan(plan);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

    @Override
    @Transactional
    public ResponseLicenciaDTO inactiveLicencia(Long id, InactiveLicenciaDTO inactiveLicenciaDTO) {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
        licenciaMapper.inactiveEntityFromDTO(inactiveLicenciaDTO, licencia);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseLicenciaDTO> getAllLicencias() {
        return licenciaRepository.findAll().stream()
                .map(licenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseLicenciaDTO getLicenciaById(Long id) {
        return licenciaRepository.findById(id)
                .map(licenciaMapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseLicenciaDTO> getLicenciasByEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(
                () -> new RuntimeException("Empresa no encontrada")
        );
        return licenciaRepository.findByEmpresa(empresa);
    }

}
