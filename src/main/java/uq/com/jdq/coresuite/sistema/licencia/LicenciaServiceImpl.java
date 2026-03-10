package uq.com.jdq.coresuite.sistema.licencia;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
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
    public ResponseLicenciaDTO createLicencia(CreateLicenciaDTO createLicenciaDTO) throws Exception {
        Licencia licencia = licenciaMapper.toEntity(createLicenciaDTO);
        Empresa empresa = empresaRepository.findById(createLicenciaDTO.empresaId()).orElseThrow(
                () -> new NoExisteException("No existe la empresa")
        );
        Plan plan = planRepository.findById(createLicenciaDTO.planId()).orElseThrow(
                () -> new NoExisteException("No existe el plan")
        );
        licencia.setEmpresa(empresa);
        licencia.setPlan(plan);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

    @Override
    @Transactional
    public ResponseLicenciaDTO updateLicencia(Long id, UpdateLicenciaDTO updateLicenciaDTO) throws Exception {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe la licencia"));
        Empresa empresa = empresaRepository.findById(updateLicenciaDTO.empresaId()).orElseThrow(
                () -> new NoExisteException("No existe la empresa")
        );
        Plan plan = planRepository.findById(updateLicenciaDTO.planId()).orElseThrow(
                () -> new NoExisteException("No existe el plan")
        );
        licenciaMapper.updateEntityFromDTO(updateLicenciaDTO, licencia);
        licencia.setEmpresa(empresa);
        licencia.setPlan(plan);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

    @Override
    @Transactional
    public ResponseLicenciaDTO inactiveLicencia(Long id, InactiveLicenciaDTO inactiveLicenciaDTO) throws Exception {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe la licencia"));
        licenciaMapper.inactiveEntityFromDTO(inactiveLicenciaDTO, licencia);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseLicenciaDTO> getAllLicencias() throws Exception {
        return licenciaRepository.findAll().stream()
                .map(licenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseLicenciaDTO getLicenciaById(Long id) throws Exception {
        return licenciaRepository.findById(id)
                .map(licenciaMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe la licencia"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseLicenciaDTO> getLicenciasByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(
                () -> new NoExisteException("No existe la empresa")
        );
        return licenciaRepository.findByEmpresa(empresa);
    }

}
