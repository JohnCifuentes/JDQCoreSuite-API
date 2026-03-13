package uq.com.jdq.coresuite.operacion.lista_valores;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de listas de valores.
 */
public interface ListaValoresService {

    /**
     * Crea una nueva lista de valores.
     * @param createListaValoresDTO datos de creacion.
     * @return lista de valores creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseListaValoresDTO createListaValores(CreateListaValoresDTO createListaValoresDTO) throws Exception;

    /**
     * Actualiza una lista de valores existente.
     * @param id identificador de la lista.
     * @param updateListaValoresDTO datos actualizados.
     * @return lista de valores actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseListaValoresDTO updateListaValores(Long id, UpdateListaValoresDTO updateListaValoresDTO) throws Exception;

    /**
     * Obtiene todas las listas de valores registradas.
     * @return lista de listas de valores.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseListaValoresDTO> getAllListaValores() throws Exception;

    /**
     * Obtiene una lista de valores por identificador.
     * @param id identificador de la lista.
     * @return lista de valores encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseListaValoresDTO getListaValoresById(Long id) throws Exception;

    /**
     * Obtiene las listas de valores asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de listas de valores relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseListaValoresDTO> getListaValoresByEmpresa(Long empresaId) throws Exception;

}
