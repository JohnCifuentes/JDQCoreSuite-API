package uq.com.jdq.coresuite.operacion.campo_dependencia;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de dependencias de campo.
 */
public interface CampoDependenciaService {

    /**
     * Crea una nueva dependencia de campo.
     * @param createCampoDependenciaDTO datos de creacion.
     * @return dependencia creada.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseCampoDependenciaDTO createCampoDependencia(CreateCampoDependenciaDTO createCampoDependenciaDTO) throws Exception;

    /**
     * Actualiza una dependencia de campo existente.
     * @param id identificador de la dependencia.
     * @param updateCampoDependenciaDTO datos actualizados.
     * @return dependencia actualizada.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseCampoDependenciaDTO updateCampoDependencia(Long id, UpdateCampoDependenciaDTO updateCampoDependenciaDTO) throws Exception;

    /**
     * Obtiene todas las dependencias de campo registradas.
     * @return lista de dependencias.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseCampoDependenciaDTO> getAllCampoDependencias() throws Exception;

    /**
     * Obtiene una dependencia de campo por identificador.
     * @param id identificador de la dependencia.
     * @return dependencia encontrada.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseCampoDependenciaDTO getCampoDependenciaById(Long id) throws Exception;

    /**
     * Obtiene las dependencias asociadas a un campo.
     * @param campoId identificador del campo.
     * @return lista de dependencias del campo.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseCampoDependenciaDTO> getCampoDependenciasByCampo(Long campoId) throws Exception;

}
