package uq.com.jdq.coresuite.seguridad.rol;

import java.util.List;

/**
 * Contrato de negocio para la gestion de roles.
 */
public interface RolService {

    /**
     * Registra un nuevo rol.
     * @param createRolDTO datos del rol a crear.
     * @return rol creado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolDTO createRol(CreateRolDTO createRolDTO) throws Exception;

    /**
     * Actualiza un rol existente.
     * @param id identificador del rol.
     * @param updateRolDTO nuevos datos del rol.
     * @return rol actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolDTO updateRol(Long id, UpdateRolDTO updateRolDTO) throws Exception;

    /**
     * Inactiva un rol existente.
     * @param id identificador del rol.
     * @param inactiveRolDTO datos del cambio de estado.
     * @return rol actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolDTO inactiveRol(Long id, InactiveRolDTO inactiveRolDTO) throws Exception;

    /**
     * Lista todos los roles registrados.
     * @return lista de roles.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseRolDTO> getAllRoles() throws Exception;

    /**
     * Consulta un rol por identificador.
     * @param id identificador del rol.
     * @return rol encontrado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolDTO getRolById(Long id) throws Exception;

    /**
     * Lista los roles asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de roles.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseRolDTO> getRolsByEmpresa(Long empresaId) throws Exception;

}
