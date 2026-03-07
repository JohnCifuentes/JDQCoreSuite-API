package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValoresRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListaValoresDetalleServiceImpl implements ListaValoresDetalleService {

    private final ListaValoresDetalleRepository listaValoresDetalleRepository;
    private final ListaValoresDetalleMapper listaValoresDetalleMapper;
    private final ListaValoresRepository listaValoresRepository;

    @Override
    @Transactional
    public ResponseListaValoresDetalleDTO createListaValoresDetalle(CreateListaValoresDetalleDTO createListaValoresDetalleDTO) throws Exception {
        ListaValores listaValores = listaValoresRepository.findById(createListaValoresDetalleDTO.listaValoresId())
                .orElseThrow(() -> new NoExisteException("No existe la lista de valores"));
        ListaValoresDetalle detalle = listaValoresDetalleMapper.toEntity(createListaValoresDetalleDTO);
        detalle.setListaValores(listaValores);
        detalle = listaValoresDetalleRepository.save(detalle);
        return listaValoresDetalleMapper.toDTO(detalle);
    }

    @Override
    @Transactional
    public ResponseListaValoresDetalleDTO updateListaValoresDetalle(Long id, UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO) throws Exception {
        ListaValores listaValores = listaValoresRepository.findById(updateListaValoresDetalleDTO.listaValoresId())
                .orElseThrow(() -> new NoExisteException("No existe la lista de valores"));
        ListaValoresDetalle detalle = listaValoresDetalleRepository.findById(id)
                .orElseThrow(() -> new NoExisteException("No existe el detalle de lista de valores"));
        listaValoresDetalleMapper.updateEntityFromDTO(updateListaValoresDetalleDTO, detalle);
        detalle.setListaValores(listaValores);
        detalle = listaValoresDetalleRepository.save(detalle);
        return listaValoresDetalleMapper.toDTO(detalle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseListaValoresDetalleDTO> getAllListaValoresDetalle() throws Exception {
        return listaValoresDetalleRepository.findAll().stream().map(listaValoresDetalleMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseListaValoresDetalleDTO getListaValoresDetalleById(Long id) throws Exception {
        return listaValoresDetalleRepository.findById(id)
                .map(listaValoresDetalleMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el detalle de lista de valores"));
    }

    @Override
    public List<ResponseListaValoresDetalleDTO> getListaValoresDetalleByListaValores(Long listaValoresId) throws Exception {
        ListaValores listaValores = listaValoresRepository.findById(listaValoresId)
                .orElseThrow(() -> new NoExisteException("No existe la lista de valores"));
        return listaValoresDetalleRepository.findByListaValores(listaValores);
    }

}
