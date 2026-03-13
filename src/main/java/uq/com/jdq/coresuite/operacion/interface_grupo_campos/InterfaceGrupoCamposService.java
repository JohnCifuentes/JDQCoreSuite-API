package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de grupos de campos por interfaz.
 */
public interface InterfaceGrupoCamposService {

    /**
     * Crea un nuevo grupo de campos.
     * @param createInterfaceGrupoCamposDTO datos de creacion.
     * @return grupo de campos creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseInterfaceGrupoCamposDTO createInterfaceGrupoCampos(CreateInterfaceGrupoCamposDTO createInterfaceGrupoCamposDTO) throws Exception;

    /**
     * Actualiza un grupo de campos existente.
     * @param id identificador del grupo.
     * @param updateInterfaceGrupoCamposDTO datos actualizados.
     * @return grupo de campos actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseInterfaceGrupoCamposDTO updateInterfaceGrupoCampos(Long id, UpdateInterfaceGrupoCamposDTO updateInterfaceGrupoCamposDTO) throws Exception;

    /**
     * Obtiene todos los grupos de campos registrados.
     * @return lista de grupos de campos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseInterfaceGrupoCamposDTO> getAllInterfaceGrupoCampos() throws Exception;

    /**
     * Obtiene un grupo de campos por identificador.
     * @param id identificador del grupo.
     * @return grupo de campos encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseInterfaceGrupoCamposDTO getInterfaceGrupoCamposById(Long id) throws Exception;

    /**
     * Obtiene los grupos de campos asociados a una interfaz.
     * @param interfazId identificador de la interfaz.
     * @return lista de grupos de campos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseInterfaceGrupoCamposDTO> getInterfaceGrupoCamposByInterfaz(Long interfazId) throws Exception;

}
