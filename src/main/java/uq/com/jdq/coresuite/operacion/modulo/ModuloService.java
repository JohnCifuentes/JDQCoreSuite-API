package uq.com.jdq.coresuite.operacion.modulo;

import java.util.List;

/**
 * Contrato de negocio para la administracion y consulta de modulos.
 */
public interface ModuloService {

    /**
     * Crea un nuevo modulo.
     * @param createModuloDTO datos de creacion.
     * @return modulo creado.
     * @throws Exception si ocurre un error durante la creacion.
     */
    ResponseModuloDTO createModulo(CreateModuloDTO createModuloDTO) throws Exception;

    /**
     * Actualiza un modulo existente.
     * @param id identificador del modulo.
     * @param updateModuloDTO datos actualizados.
     * @return modulo actualizado.
     * @throws Exception si ocurre un error durante la actualizacion.
     */
    ResponseModuloDTO updateModulo(Long id, UpdateModuloDTO updateModuloDTO) throws Exception;

    /**
     * Obtiene todos los modulos registrados.
     * @return lista de modulos.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseModuloDTO> getAllModulos() throws Exception;

    /**
     * Obtiene un modulo por identificador.
     * @param id identificador del modulo.
     * @return modulo encontrado.
     * @throws Exception si ocurre un error durante la consulta.
     */
    ResponseModuloDTO getModuloById(Long id) throws Exception;

    /**
     * Obtiene los modulos asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de modulos relacionados.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<ResponseModuloDTO> getModulosByEmpresa(Long empresaId) throws Exception;

}
