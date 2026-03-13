package uq.com.jdq.coresuite.operacion.lista_valores_detalle;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de detalles de listas de valores.
 */
public interface ListaValoresDetalleService {

    /**
     * Crea un nuevo detalle de lista de valores.
     * @param createListaValoresDetalleDTO datos de creacion.
     * @return detalle creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseListaValoresDetalleDTO createListaValoresDetalle(CreateListaValoresDetalleDTO createListaValoresDetalleDTO) throws Exception;

    /**
     * Actualiza un detalle de lista de valores existente.
     * @param id identificador del detalle.
     * @param updateListaValoresDetalleDTO datos actualizados.
     * @return detalle actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseListaValoresDetalleDTO updateListaValoresDetalle(Long id, UpdateListaValoresDetalleDTO updateListaValoresDetalleDTO) throws Exception;

    /**
     * Obtiene todos los detalles de listas de valores registrados.
     * @return lista de detalles.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseListaValoresDetalleDTO> getAllListaValoresDetalle() throws Exception;

    /**
     * Obtiene un detalle de lista de valores por identificador.
     * @param id identificador del detalle.
     * @return detalle encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseListaValoresDetalleDTO getListaValoresDetalleById(Long id) throws Exception;

    /**
     * Obtiene los detalles asociados a una lista de valores.
     * @param listaValoresId identificador de la lista.
     * @return lista de detalles relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseListaValoresDetalleDTO> getListaValoresDetalleByListaValores(Long listaValoresId) throws Exception;

}
