package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import java.util.List;

public interface ListaValoresDetalleService {

    ResponseListaValoresDetalleDTO createListaValoresDetalle(CreateListaValoresDetalleDTO createListaValoresDetalleDTO) throws Exception;

    ResponseListaValoresDetalleDTO updateListaValoresDetalle(Long id, UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO) throws Exception;

    List<ResponseListaValoresDetalleDTO> getAllListaValoresDetalle() throws Exception;

    ResponseListaValoresDetalleDTO getListaValoresDetalleById(Long id) throws Exception;

    List<ResponseListaValoresDetalleDTO> getListaValoresDetalleByListaValores(Long listaValoresId) throws Exception;

}
