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

/**
 * Implementacion del servicio de administracion y consulta de dependencias de campo.
 */
@Service
@RequiredArgsConstructor
public class CampoDependenciaServiceImpl implements CampoDependenciaService {

    private final CampoDependenciaRepository campoDependenciaRepository;
    private final CampoDependenciaMapper campoDependenciaMapper;
    private final CampoRepository campoRepository;

    /**
     * Crea una nueva dependencia entre campos.
     * @param createCampoDependenciaDTO datos de creacion de la dependencia.
     * @return dependencia creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
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

    /**
     * Actualiza una dependencia de campo existente.
     * @param id identificador de la dependencia.
     * @param updateCampoDependenciaDTO datos actualizados de la dependencia.
     * @return dependencia actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
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

    /**
     * Obtiene la lista completa de dependencias de campo.
     * @return lista de dependencias.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoDependenciaDTO> getAllCampoDependencias() {
        return campoDependenciaRepository.findAll().stream()
                .map(campoDependenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una dependencia de campo por identificador.
     * @param id identificador de la dependencia.
     * @return dependencia encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseCampoDependenciaDTO getCampoDependenciaById(Long id) throws Exception {
        Optional<CampoDependencia> campoDependencia = campoDependenciaRepository.findById(id);
        if(campoDependencia.isEmpty()) {
            throw new NoExisteException("No existe la dependencia de campo");
        }
        return campoDependenciaMapper.toDTO(campoDependencia.get());
    }

    /**
     * Obtiene las dependencias asociadas a un campo.
     * @param campoId identificador del campo.
     * @return lista de dependencias del campo.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoDependenciaDTO> getCampoDependenciasByCampo(Long campoId) throws Exception {
        Campo campo = campoRepository.findById(campoId).orElseThrow(() ->
                new NoExisteException("No existe el campo")
        );
        return campoDependenciaRepository.findByCampo(campo);
    }

}
