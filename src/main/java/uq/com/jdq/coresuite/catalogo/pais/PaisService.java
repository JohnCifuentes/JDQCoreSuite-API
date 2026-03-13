package uq.com.jdq.coresuite.catalogo.pais;

import java.util.List;

/**
 * Contrato de negocio para operaciones de consulta de paises.
 */
public interface PaisService {

    /**
     * Obtiene todos los paises disponibles.
     * @return lista de paises.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<PaisDTO> getAllPaises() throws Exception;

    /**
     * Obtiene un pais por su identificador.
     * @param id identificador del pais.
     * @return pais encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    PaisDTO getPaisById(Long id) throws Exception;

}
