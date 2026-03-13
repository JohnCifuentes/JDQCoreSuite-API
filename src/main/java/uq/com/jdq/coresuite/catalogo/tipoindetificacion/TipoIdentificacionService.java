package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import java.util.List;

/**
 * Contrato de negocio para operaciones de consulta de tipos de identificacion.
 */
public interface TipoIdentificacionService {

    /**
     * Obtiene todos los tipos de identificacion disponibles.
     * @return lista de tipos de identificacion.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<TipoIdentificacionDTO> getAllTiposIdentificacion() throws Exception;

    /**
     * Obtiene un tipo de identificacion por su identificador.
     * @param id identificador del tipo de identificacion.
     * @return tipo de identificacion encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    TipoIdentificacionDTO getTipoIdentificacionById(Long id) throws Exception;

}
