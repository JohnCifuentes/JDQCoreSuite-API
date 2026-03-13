package uq.com.jdq.coresuite.sistema.licencia;

import java.util.List;

/**
 * Contrato de negocio para la gestion de licencias.
 */
public interface LicenciaService {

    /**
     * Registra una nueva licencia.
     * @param createLicenciaDTO datos de la licencia.
     * @return licencia creada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseLicenciaDTO createLicencia(CreateLicenciaDTO createLicenciaDTO) throws Exception;

    /**
     * Actualiza una licencia existente.
     * @param id identificador de la licencia.
     * @param updateLicenciaDTO nuevos datos de la licencia.
     * @return licencia actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseLicenciaDTO updateLicencia(Long id, UpdateLicenciaDTO updateLicenciaDTO) throws Exception;

    /**
     * Inactiva una licencia.
     * @param id identificador de la licencia.
     * @param inactiveLicenciaDTO datos del cambio de estado.
     * @return licencia actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseLicenciaDTO inactiveLicencia(Long id, InactiveLicenciaDTO inactiveLicenciaDTO) throws Exception;

    /**
     * Lista todas las licencias registradas.
     * @return lista de licencias.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseLicenciaDTO> getAllLicencias() throws Exception;

    /**
     * Consulta una licencia por identificador.
     * @param id identificador de la licencia.
     * @return licencia encontrada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseLicenciaDTO getLicenciaById(Long id) throws Exception;

    /**
     * Lista las licencias asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de licencias.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseLicenciaDTO> getLicenciasByEmpresa(Long empresaId) throws Exception;

}
