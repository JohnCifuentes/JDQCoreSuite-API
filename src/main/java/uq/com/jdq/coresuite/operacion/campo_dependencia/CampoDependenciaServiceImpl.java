package uq.com.jdq.coresuite.operacion.campo_dependencia;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.operacion.campo.Campo;
import uq.com.jdq.coresuite.operacion.campo.CampoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampoDependenciaServiceImpl implements CampoDependenciaService {

    private final CampoDependenciaRepository campoDependenciaRepository;
    private final CampoDependenciaMapper campoDependenciaMapper;
    private final CampoRepository campoRepository;

    @Override
    @Transactional
    public ResponseCampoDependenciaDTO createCampoDependencia(CreateCampoDependenciaDTO createCampoDependenciaDTO) throws Exception {
        CampoDependencia campoDependencia = campoDependenciaMapper.toEntity(createCampoDependenciaDTO);
        
        Optional<Campo> campo = campoRepository.findById(createCampoDependenciaDTO.campoId());
        if(campo.isEmpty()) {
            throw new NoExisteException("No existe el campo");
        }
        
        Optional<Campo> campoDependiente = campoRepository.findById(createCampoDependenciaDTO.campoDependienteId());
        if(campoDependiente.isEmpty()) {
            throw new NoExisteException("No existe el campo dependiente");
        }
        
        campoDependencia.setCampo(campo.get());
        campoDependencia.setCampoDependiente(campoDependiente.get());
        campoDependencia = campoDependenciaRepository.save(campoDependencia);
        return campoDependenciaMapper.toDTO(campoDependencia);
    }

    @Override
    @Transactional
    public ResponseCampoDependenciaDTO updateCampoDependencia(Long id, UpdateCampoDependenciaDTO updateCampoDependenciaDTO) throws Exception {
        Optional<Campo> campo = campoRepository.findById(updateCampoDependenciaDTO.campoId());
        if(campo.isEmpty()) {
            throw new NoExisteException("No existe el campo");
        }
        
        Optional<Campo> campoDependiente = campoRepository.findById(updateCampoDependenciaDTO.campoDependienteId());
        if(campoDependiente.isEmpty()) {
            throw new NoExisteException("No existe el campo dependiente");
        }
        
        Optional<CampoDependencia> campoDependencia = campoDependenciaRepository.findById(id);
        if(campoDependencia.isEmpty()) {
            throw new NoExisteException("No existe la dependencia de campo");
        }
        
        CampoDependencia campoDependenciaAux = campoDependencia.get();
        campoDependenciaMapper.updateEntityFromDTO(updateCampoDependenciaDTO, campoDependenciaAux);
        campoDependenciaAux.setCampo(campo.get());
        campoDependenciaAux.setCampoDependiente(campoDependiente.get());
        campoDependenciaAux = campoDependenciaRepository.save(campoDependenciaAux);
        return campoDependenciaMapper.toDTO(campoDependenciaAux);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoDependenciaDTO> getAllCampoDependencias() {
        return campoDependenciaRepository.findAll().stream()
                .map(campoDependenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCampoDependenciaDTO getCampoDependenciaById(Long id) throws Exception {
        Optional<CampoDependencia> campoDependencia = campoDependenciaRepository.findById(id);
        if(campoDependencia.isEmpty()) {
            throw new NoExisteException("No existe la dependencia de campo");
        }
        return campoDependenciaMapper.toDTO(campoDependencia.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoDependenciaDTO> getCampoDependenciasByCampo(Long campoId) throws Exception {
        Campo campo = campoRepository.findById(campoId).orElseThrow(() ->
                new NoExisteException("No existe el campo")
        );
        return campoDependenciaRepository.findByCampo(campo);
    }

}
