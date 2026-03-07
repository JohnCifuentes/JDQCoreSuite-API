package uq.com.jdq.coresuite.operacion.tipo_campo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCampoServiceImpl implements TipoCampoService {

    private final TipoCampoRepository tipoCampoRepository;
    private final TipoCampoMapper tipoCampoMapper;

    @Override
    @Transactional
    public ResponseTipoCampoDTO createTipoCampo(CreateTipoCampoDTO createTipoCampoDTO) throws Exception {
        TipoCampo tipoCampo = tipoCampoMapper.toEntity(createTipoCampoDTO);
        tipoCampo = tipoCampoRepository.save(tipoCampo);
        return tipoCampoMapper.toDTO(tipoCampo);
    }

    @Override
    @Transactional
    public ResponseTipoCampoDTO updateTipoCampo(Long id, UpdateTipoCampoDTO updateTipoCampoDTO) throws Exception {
        TipoCampo tipoCampo = tipoCampoRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el tipo de campo"));
        tipoCampoMapper.updateEntityFromDTO(updateTipoCampoDTO, tipoCampo);
        tipoCampo = tipoCampoRepository.save(tipoCampo);
        return tipoCampoMapper.toDTO(tipoCampo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseTipoCampoDTO> getAllTipoCampos() throws Exception {
        return tipoCampoRepository.findAll().stream().map(tipoCampoMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseTipoCampoDTO getTipoCampoById(Long id) throws Exception {
        return tipoCampoRepository.findById(id)
                .map(tipoCampoMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el tipo de campo"));
    }

}
