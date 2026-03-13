package uq.com.jdq.coresuite.catalogo.municipio;

import java.util.List;

/**
 * Contrato de negocio para operaciones de consulta de municipios.
 */
public interface MunicipioService {

    /**
     * Obtiene todos los municipios disponibles.
     * @return lista de municipios.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<MunicipioDTO> getAllMunicipios() throws Exception;

    /**
     * Obtiene los municipios de un departamento.
     * @param departamentoId identificador del departamento.
     * @return lista de municipios del departamento.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<MunicipioDTO> getAllMunicipiosByDepartamento(Long departamentoId) throws Exception;

    /**
     * Obtiene un municipio por su identificador.
     * @param id identificador del municipio.
     * @return municipio encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    MunicipioDTO getMunicipioById(Long id) throws Exception;

}
