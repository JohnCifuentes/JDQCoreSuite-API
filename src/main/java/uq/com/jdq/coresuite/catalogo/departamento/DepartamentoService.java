package uq.com.jdq.coresuite.catalogo.departamento;

import java.util.List;

/**
 * Contrato de negocio para operaciones de consulta de departamentos.
 */
public interface DepartamentoService {

    /**
     * Obtiene todos los departamentos disponibles.
     * @return lista de departamentos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<DepartamentoDTO> getAllDepartamentos() throws Exception;

    /**
     * Obtiene los departamentos de un pais.
     * @param paisId identificador del pais.
     * @return lista de departamentos del pais.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<DepartamentoDTO> getAllDepartamentosByPais(Long paisId) throws Exception;

    /**
     * Obtiene un departamento por su identificador.
     * @param id identificador del departamento.
     * @return departamento encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    DepartamentoDTO getDepartamentoById(Long id) throws Exception;

}
