package uq.com.jdq.coresuite.operacion.tipo_validacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoValidacionServiceImpl implements TipoValidacionService {

    private final TipoValidacionRepository tipoValidacionRepository;
    private final TipoValidacionMapper tipoValidacionMapper;

    @Override
    @Transactional
    public ResponseTipoValidacionDTO createTipoValidacion(CreateTipoValidacionDTO createTipoValidacionDTO) throws Exception {
        TipoValidacion tipoValidacion = tipoValidacionMapper.toEntity(createTipoValidacionDTO);
        tipoValidacion = tipoValidacionRepository.save(tipoValidacion);
        return tipoValidacionMapper.toDTO(tipoValidacion);
    }

    @Override
    @Transactional
    public ResponseTipoValidacionDTO updateTipoValidacion(Long id, UpdateTipoValidacionDTO updateTipoValidacionDTO) throws Exception {
        Optional<TipoValidacion> tipoValidacion = tipoValidacionRepository.findById(id);
        if(tipoValidacion.isEmpty()) {
            throw new NoExisteException("No existe el tipo de validación");
        }
        TipoValidacion tipoValidacionAux = tipoValidacion.get();
        tipoValidacionMapper.updateEntityFromDTO(updateTipoValidacionDTO, tipoValidacionAux);
        tipoValidacionAux = tipoValidacionRepository.save(tipoValidacionAux);
        return tipoValidacionMapper.toDTO(tipoValidacionAux);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseTipoValidacionDTO> getAllTipoValidaciones() {
        return tipoValidacionRepository.findAll().stream()
                .map(tipoValidacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseTipoValidacionDTO getTipoValidacionById(Long id) throws Exception {
        Optional<TipoValidacion> tipoValidacion = tipoValidacionRepository.findById(id);
        if(tipoValidacion.isEmpty()) {
            throw new NoExisteException("No existe el tipo de validación");
        }
        return tipoValidacionMapper.toDTO(tipoValidacion.get());
    }

}
