package uq.com.jdq.coresuite.operacion.tipo_validacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacion del servicio de administracion y consulta de tipos de validacion.
 */
@Service
@RequiredArgsConstructor
public class TipoValidacionServiceImpl implements TipoValidacionService {

    private final TipoValidacionRepository tipoValidacionRepository;
    private final TipoValidacionMapper tipoValidacionMapper;

    /**
     * Crea un nuevo tipo de validacion.
     * @param createTipoValidacionDTO datos de creacion.
     * @return tipo de validacion creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @Override
    @Transactional
    public ResponseTipoValidacionDTO createTipoValidacion(CreateTipoValidacionDTO createTipoValidacionDTO) throws Exception {
        TipoValidacion tipoValidacion = tipoValidacionMapper.toEntity(createTipoValidacionDTO);
        tipoValidacion = tipoValidacionRepository.save(tipoValidacion);
        return tipoValidacionMapper.toDTO(tipoValidacion);
    }

    /**
     * Actualiza un tipo de validacion existente.
     * @param id identificador del tipo de validacion.
     * @param updateTipoValidacionDTO datos actualizados.
     * @return tipo de validacion actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @Override
    @Transactional
    public ResponseTipoValidacionDTO updateTipoValidacion(Long id, UpdateTipoValidacionDTO updateTipoValidacionDTO) throws Exception {
        Optional<TipoValidacion> tipoValidacion = tipoValidacionRepository.findById(id);
        if(tipoValidacion.isEmpty()) {
            throw new NoExisteException("No existe el tipo de validaciÃ³n");
        }
        TipoValidacion tipoValidacionAux = tipoValidacion.get();
        tipoValidacionMapper.updateEntityFromDTO(updateTipoValidacionDTO, tipoValidacionAux);
        tipoValidacionAux = tipoValidacionRepository.save(tipoValidacionAux);
        return tipoValidacionMapper.toDTO(tipoValidacionAux);
    }

    /**
     * Obtiene la lista completa de tipos de validacion.
     * @return lista de tipos de validacion.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseTipoValidacionDTO> getAllTipoValidaciones() {
        return tipoValidacionRepository.findAll().stream()
                .map(tipoValidacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un tipo de validacion por identificador.
     * @param id identificador del tipo de validacion.
     * @return tipo de validacion encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseTipoValidacionDTO getTipoValidacionById(Long id) throws Exception {
        Optional<TipoValidacion> tipoValidacion = tipoValidacionRepository.findById(id);
        if(tipoValidacion.isEmpty()) {
            throw new NoExisteException("No existe el tipo de validaciÃ³n");
        }
        return tipoValidacionMapper.toDTO(tipoValidacion.get());
    }

}
