package uq.com.jdq.coresuite.operacion.tipo_campo;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de tipos de campo.
 */
public interface TipoCampoService {

    /**
     * Crea un nuevo tipo de campo.
     * @param createTipoCampoDTO datos de creacion.
     * @return tipo de campo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseTipoCampoDTO createTipoCampo(CreateTipoCampoDTO createTipoCampoDTO) throws Exception;

    /**
     * Actualiza un tipo de campo existente.
     * @param id identificador del tipo de campo.
     * @param updateTipoCampoDTO datos actualizados.
     * @return tipo de campo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseTipoCampoDTO updateTipoCampo(Long id, UpdateTipoCampoDTO updateTipoCampoDTO) throws Exception;

    /**
     * Obtiene todos los tipos de campo registrados.
     * @return lista de tipos de campo.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseTipoCampoDTO> getAllTipoCampos() throws Exception;

    /**
     * Obtiene un tipo de campo por identificador.
     * @param id identificador del tipo de campo.
     * @return tipo de campo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseTipoCampoDTO getTipoCampoById(Long id) throws Exception;

}
