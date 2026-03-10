package uq.com.jdq.coresuite.operacion.campo_validacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.operacion.campo.Campo;
import uq.com.jdq.coresuite.operacion.campo.CampoRepository;
import uq.com.jdq.coresuite.operacion.tipo_validacion.TipoValidacion;
import uq.com.jdq.coresuite.operacion.tipo_validacion.TipoValidacionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampoValidacionServiceImpl implements CampoValidacionService {

    private final CampoValidacionRepository campoValidacionRepository;
    private final CampoValidacionMapper campoValidacionMapper;
    private final CampoRepository campoRepository;
    private final TipoValidacionRepository tipoValidacionRepository;

    @Override
    @Transactional
    public ResponseCampoValidacionDTO createCampoValidacion(CreateCampoValidacionDTO createCampoValidacionDTO) throws Exception {
        CampoValidacion campoValidacion = campoValidacionMapper.toEntity(createCampoValidacionDTO);
        
        Optional<Campo> campo = campoRepository.findById(createCampoValidacionDTO.campoId());
        if(campo.isEmpty()) {
            throw new NoExisteException("No existe el campo");
        }
        
        Optional<TipoValidacion> tipoValidacion = tipoValidacionRepository.findById(createCampoValidacionDTO.tipoValidacionId());
        if(tipoValidacion.isEmpty()) {
            throw new NoExisteException("No existe el tipo de validación");
        }
        
        if(createCampoValidacionDTO.campoReferenciaId() != null) {
            Optional<Campo> campoReferencia = campoRepository.findById(createCampoValidacionDTO.campoReferenciaId());
            if(campoReferencia.isEmpty()) {
                throw new NoExisteException("No existe el campo de referencia");
            }
            campoValidacion.setCampoReferencia(campoReferencia.get());
        }
        
        campoValidacion.setCampo(campo.get());
        campoValidacion.setTipoValidacion(tipoValidacion.get());
        campoValidacion = campoValidacionRepository.save(campoValidacion);
        return campoValidacionMapper.toDTO(campoValidacion);
    }

    @Override
    @Transactional
    public ResponseCampoValidacionDTO updateCampoValidacion(Long id, UpdateCampoValidacionDTO updateCampoValidacionDTO) throws Exception {
        Optional<Campo> campo = campoRepository.findById(updateCampoValidacionDTO.campoId());
        if(campo.isEmpty()) {
            throw new NoExisteException("No existe el campo");
        }
        
        Optional<TipoValidacion> tipoValidacion = tipoValidacionRepository.findById(updateCampoValidacionDTO.tipoValidacionId());
        if(tipoValidacion.isEmpty()) {
            throw new NoExisteException("No existe el tipo de validación");
        }
        
        if(updateCampoValidacionDTO.campoReferenciaId() != null) {
            Optional<Campo> campoReferencia = campoRepository.findById(updateCampoValidacionDTO.campoReferenciaId());
            if(campoReferencia.isEmpty()) {
                throw new NoExisteException("No existe el campo de referencia");
            }
        }
        
        Optional<CampoValidacion> campoValidacion = campoValidacionRepository.findById(id);
        if(campoValidacion.isEmpty()) {
            throw new NoExisteException("No existe la validación de campo");
        }
        
        CampoValidacion campoValidacionAux = campoValidacion.get();
        campoValidacionMapper.updateEntityFromDTO(updateCampoValidacionDTO, campoValidacionAux);
        campoValidacionAux.setCampo(campo.get());
        campoValidacionAux.setTipoValidacion(tipoValidacion.get());
        
        if(updateCampoValidacionDTO.campoReferenciaId() != null) {
            Campo campoReferencia = campoRepository.findById(updateCampoValidacionDTO.campoReferenciaId()).get();
            campoValidacionAux.setCampoReferencia(campoReferencia);
        } else {
            campoValidacionAux.setCampoReferencia(null);
        }
        
        campoValidacionAux = campoValidacionRepository.save(campoValidacionAux);
        return campoValidacionMapper.toDTO(campoValidacionAux);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoValidacionDTO> getAllCampoValidaciones() {
        return campoValidacionRepository.findAll().stream()
                .map(campoValidacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCampoValidacionDTO getCampoValidacionById(Long id) throws Exception {
        Optional<CampoValidacion> campoValidacion = campoValidacionRepository.findById(id);
        if(campoValidacion.isEmpty()) {
            throw new NoExisteException("No existe la validación de campo");
        }
        return campoValidacionMapper.toDTO(campoValidacion.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCampoValidacionDTO> getCampoValidacionesByCampo(Long campoId) throws Exception {
        Campo campo = campoRepository.findById(campoId).orElseThrow(() ->
                new NoExisteException("No existe el campo")
        );
        return campoValidacionRepository.findByCampo(campo);
    }

}
