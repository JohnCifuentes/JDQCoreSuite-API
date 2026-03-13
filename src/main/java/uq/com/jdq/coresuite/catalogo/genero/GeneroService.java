package uq.com.jdq.coresuite.catalogo.genero;

import java.util.List;

/**
 * Contrato de negocio para operaciones de consulta de generos.
 */
public interface GeneroService {

    /**
     * Obtiene todos los generos disponibles.
     * @return lista de generos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<GeneroDTO> getAllGeneros() throws Exception;

    /**
     * Obtiene un genero por su identificador.
     * @param id identificador del genero.
     * @return genero encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    GeneroDTO getGeneroById(Long id) throws Exception;

}
