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

/**
 * Implementacion del servicio encargado de administrar licencias.
 */
@Service
@RequiredArgsConstructor
public class LicenciaServiceImpl implements LicenciaService {

    private final LicenciaRepository licenciaRepository;
    private final LicenciaMapper licenciaMapper;
    private final EmpresaRepository empresaRepository;
    private final PlanRepository planRepository;

        /**
         * Crea una licencia y asocia la empresa y el plan correspondientes.
         * @param createLicenciaDTO datos de la licencia.
         * @return licencia creada.
         * @throws Exception si la empresa o el plan no existen.
         */
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

        /**
         * Actualiza una licencia existente.
         * @param id identificador de la licencia.
         * @param updateLicenciaDTO nuevos datos de la licencia.
         * @return licencia actualizada.
         * @throws Exception si la licencia, empresa o plan no existen.
         */
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

        /**
         * Cambia el estado de una licencia.
         * @param id identificador de la licencia.
         * @param inactiveLicenciaDTO datos del nuevo estado.
         * @return licencia actualizada.
         * @throws Exception si la licencia no existe.
         */
    @Override
    @Transactional
    public ResponseLicenciaDTO inactiveLicencia(Long id, InactiveLicenciaDTO inactiveLicenciaDTO) throws Exception {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe la licencia"));
        licenciaMapper.inactiveEntityFromDTO(inactiveLicenciaDTO, licencia);
        licencia = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(licencia);
    }

        /**
         * Obtiene todas las licencias registradas.
         * @return lista de licencias.
         * @throws Exception si ocurre un error durante la consulta.
         */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseLicenciaDTO> getAllLicencias() throws Exception {
        return licenciaRepository.findAll().stream()
                .map(licenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

        /**
         * Consulta una licencia por identificador.
         * @param id identificador de la licencia.
         * @return licencia encontrada.
         * @throws Exception si la licencia no existe.
         */
    @Override
    @Transactional(readOnly = true)
    public ResponseLicenciaDTO getLicenciaById(Long id) throws Exception {
        return licenciaRepository.findById(id)
                .map(licenciaMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe la licencia"));
    }

        /**
         * Obtiene las licencias asociadas a una empresa.
         * @param empresaId identificador de la empresa.
         * @return lista de licencias.
         * @throws Exception si la empresa no existe.
         */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseLicenciaDTO> getLicenciasByEmpresa(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(
                () -> new NoExisteException("No existe la empresa")
        );
        return licenciaRepository.findByEmpresa(empresa);
    }

}
