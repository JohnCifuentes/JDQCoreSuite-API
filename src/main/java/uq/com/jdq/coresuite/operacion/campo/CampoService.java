package uq.com.jdq.coresuite.operacion.campo;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de campos.
 */
public interface CampoService {

    /**
     * Crea un nuevo campo.
     * @param createCampoDTO datos de creacion del campo.
     * @return campo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseCampoDTO createCampo(CreateCampoDTO createCampoDTO) throws Exception;

    /**
     * Actualiza un campo existente.
     * @param id identificador del campo.
     * @param updateCampoDTO datos actualizados del campo.
     * @return campo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseCampoDTO updateCampo(Long id, UpdateCampoDTO updateCampoDTO) throws Exception;

    /**
     * Obtiene todos los campos registrados.
     * @return lista de campos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseCampoDTO> getAllCampos() throws Exception;

    /**
     * Obtiene un campo por identificador.
     * @param id identificador del campo.
     * @return campo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseCampoDTO getCampoById(Long id) throws Exception;

    /**
     * Obtiene los campos asociados a una interfaz.
     * @param interfazId identificador de la interfaz.
     * @return lista de campos de la interfaz.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseCampoDTO> getCamposByInterfaz(Long interfazId) throws Exception;

}
