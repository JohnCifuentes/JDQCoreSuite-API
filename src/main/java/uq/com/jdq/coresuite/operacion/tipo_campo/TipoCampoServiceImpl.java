package uq.com.jdq.coresuite.operacion.tipo_campo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;

/**
 * Implementacion del servicio de administracion y consulta de tipos de campo.
 */
@Service
@RequiredArgsConstructor
public class TipoCampoServiceImpl implements TipoCampoService {

    private final TipoCampoRepository tipoCampoRepository;
    private final TipoCampoMapper tipoCampoMapper;

    /**
     * Crea un nuevo tipo de campo.
     * @param createTipoCampoDTO datos de creacion.
     * @return tipo de campo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    @Override
    @Transactional
    public ResponseTipoCampoDTO createTipoCampo(CreateTipoCampoDTO createTipoCampoDTO) throws Exception {
        TipoCampo tipoCampo = tipoCampoMapper.toEntity(createTipoCampoDTO);
        tipoCampo = tipoCampoRepository.save(tipoCampo);
        return tipoCampoMapper.toDTO(tipoCampo);
    }

    /**
     * Actualiza un tipo de campo existente.
     * @param id identificador del tipo de campo.
     * @param updateTipoCampoDTO datos actualizados.
     * @return tipo de campo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    @Override
    @Transactional
    public ResponseTipoCampoDTO updateTipoCampo(Long id, UpdateTipoCampoDTO updateTipoCampoDTO) throws Exception {
        TipoCampo tipoCampo = tipoCampoRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el tipo de campo"));
        tipoCampoMapper.updateEntityFromDTO(updateTipoCampoDTO, tipoCampo);
        tipoCampo = tipoCampoRepository.save(tipoCampo);
        return tipoCampoMapper.toDTO(tipoCampo);
    }

    /**
     * Obtiene la lista completa de tipos de campo.
     * @return lista de tipos de campo.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseTipoCampoDTO> getAllTipoCampos() throws Exception {
        return tipoCampoRepository.findAll().stream().map(tipoCampoMapper::toDTO).toList();
    }

    /**
     * Obtiene un tipo de campo por identificador.
     * @param id identificador del tipo de campo.
     * @return tipo de campo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseTipoCampoDTO getTipoCampoById(Long id) throws Exception {
        return tipoCampoRepository.findById(id)
                .map(tipoCampoMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el tipo de campo"));
    }

}
