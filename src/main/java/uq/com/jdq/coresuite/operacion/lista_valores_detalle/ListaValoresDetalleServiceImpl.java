package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uq.com.jdq.coresuite.config.exceptions.NoExisteException;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValores;
import uq.com.jdq.coresuite.operacion.lista_valores.ListaValoresRepository;

import java.util.List;

/**
 * Implementacion del servicio de administracion y consulta de detalles de listas de valores.
 */
@Service
@RequiredArgsConstructor
public class ListaValoresDetalleServiceImpl implements ListaValoresDetalleService {

    private final ListaValoresDetalleRepository listaValoresDetalleRepository;
    private final ListaValoresDetalleMapper listaValoresDetalleMapper;
    private final ListaValoresRepository listaValoresRepository;

    /**
     * Crea un nuevo detalle de lista de valores.
     * @param createListaValoresDetalleDTO datos de creacion.
     * @return detalle creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
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

    /**
     * Actualiza un detalle de lista de valores existente.
     * @param id identificador del detalle.
     * @param updateListaValoresDetalleDTO datos actualizados.
     * @return detalle actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
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

    /**
     * Obtiene la lista completa de detalles de listas de valores.
     * @return lista de detalles.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResponseListaValoresDetalleDTO> getAllListaValoresDetalle() throws Exception {
        return listaValoresDetalleRepository.findAll().stream().map(listaValoresDetalleMapper::toDTO).toList();
    }

    /**
     * Obtiene un detalle de lista de valores por identificador.
     * @param id identificador del detalle.
     * @return detalle encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseListaValoresDetalleDTO getListaValoresDetalleById(Long id) throws Exception {
        return listaValoresDetalleRepository.findById(id)
                .map(listaValoresDetalleMapper::toDTO)
                .orElseThrow(() -> new NoExisteException("No existe el detalle de lista de valores"));
    }

    /**
     * Obtiene los detalles asociados a una lista de valores.
     * @param listaValoresId identificador de la lista.
     * @return lista de detalles relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    @Override
    public List<ResponseListaValoresDetalleDTO> getListaValoresDetalleByListaValores(Long listaValoresId) throws Exception {
        ListaValores listaValores = listaValoresRepository.findById(listaValoresId)
                .orElseThrow(() -> new NoExisteException("No existe la lista de valores"));
        return listaValoresDetalleRepository.findByListaValores(listaValores);
    }

}
