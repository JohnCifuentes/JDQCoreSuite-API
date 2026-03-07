package uq.com.jdq.coresuite.operacion.lista_valores;

import java.util.List;

public interface ListaValoresService {

    ResponseListaValoresDTO createListaValores(CreateListaValoresDTO createListaValoresDTO) throws Exception;

    ResponseListaValoresDTO updateListaValores(Long id, UpdateListaValoresDTO updateListaValoresDTO) throws Exception;

    List<ResponseListaValoresDTO> getAllListaValores() throws Exception;

    ResponseListaValoresDTO getListaValoresById(Long id) throws Exception;

    List<ResponseListaValoresDTO> getListaValoresByEmpresa(Long empresaId) throws Exception;

}
