package uq.com.jdq.coresuite.operacion.tipo_validacion;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de tipos de validacion.
 */
public interface TipoValidacionService {

    /**
     * Crea un nuevo tipo de validacion.
     * @param createTipoValidacionDTO datos de creacion.
     * @return tipo de validacion creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseTipoValidacionDTO createTipoValidacion(CreateTipoValidacionDTO createTipoValidacionDTO) throws Exception;

    /**
     * Actualiza un tipo de validacion existente.
     * @param id identificador del tipo de validacion.
     * @param updateTipoValidacionDTO datos actualizados.
     * @return tipo de validacion actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseTipoValidacionDTO updateTipoValidacion(Long id, UpdateTipoValidacionDTO updateTipoValidacionDTO) throws Exception;

    /**
     * Obtiene todos los tipos de validacion registrados.
     * @return lista de tipos de validacion.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseTipoValidacionDTO> getAllTipoValidaciones() throws Exception;

    /**
     * Obtiene un tipo de validacion por identificador.
     * @param id identificador del tipo de validacion.
     * @return tipo de validacion encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseTipoValidacionDTO getTipoValidacionById(Long id) throws Exception;

}
