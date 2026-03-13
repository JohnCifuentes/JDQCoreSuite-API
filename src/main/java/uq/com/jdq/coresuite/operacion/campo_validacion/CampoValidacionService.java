package uq.com.jdq.coresuite.operacion.campo_validacion;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de validaciones de campo.
 */
public interface CampoValidacionService {

    /**
     * Crea una nueva validacion de campo.
     * @param createCampoValidacionDTO datos de creacion.
     * @return validacion creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseCampoValidacionDTO createCampoValidacion(CreateCampoValidacionDTO createCampoValidacionDTO) throws Exception;

    /**
     * Actualiza una validacion de campo existente.
     * @param id identificador de la validacion.
     * @param updateCampoValidacionDTO datos actualizados.
     * @return validacion actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseCampoValidacionDTO updateCampoValidacion(Long id, UpdateCampoValidacionDTO updateCampoValidacionDTO) throws Exception;

    /**
     * Obtiene todas las validaciones de campo registradas.
     * @return lista de validaciones.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseCampoValidacionDTO> getAllCampoValidaciones() throws Exception;

    /**
     * Obtiene una validacion de campo por identificador.
     * @param id identificador de la validacion.
     * @return validacion encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseCampoValidacionDTO getCampoValidacionById(Long id) throws Exception;

    /**
     * Obtiene las validaciones asociadas a un campo.
     * @param campoId identificador del campo.
     * @return lista de validaciones relacionadas.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseCampoValidacionDTO> getCampoValidacionesByCampo(Long campoId) throws Exception;

}
