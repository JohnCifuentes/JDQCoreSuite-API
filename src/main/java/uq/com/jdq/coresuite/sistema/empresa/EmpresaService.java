package uq.com.jdq.coresuite.sistema.empresa;

import java.util.List;

/**
 * Contrato de negocio para la gestion de empresas.
 */
public interface EmpresaService {

    /**
     * Registra una nueva empresa.
     * @param createEmpresaDTO datos de la empresa.
     * @return empresa creada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseEmpresaDTO createEmpresa(CreateEmpresaDTO createEmpresaDTO) throws Exception;

    /**
     * Actualiza una empresa existente.
     * @param id identificador de la empresa.
     * @param updateEmpresaDTO nuevos datos de la empresa.
     * @return empresa actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseEmpresaDTO updateEmpresa(Long id, UpdateEmpresaDTO updateEmpresaDTO) throws Exception;

    /**
     * Inactiva una empresa.
     * @param id identificador de la empresa.
     * @param inactiveEmpresaDTO datos del cambio de estado.
     * @return empresa actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseEmpresaDTO inactiveEmpresa(Long id, InactiveEmpresaDTO inactiveEmpresaDTO) throws Exception;

    /**
     * Lista todas las empresas.
     * @return lista de empresas.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseEmpresaDTO> getAllEmpresas() throws Exception;

    /**
     * Consulta una empresa por identificador.
     * @param id identificador de la empresa.
     * @return empresa encontrada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseEmpresaDTO getEmpresaById(Long id) throws Exception;

}
