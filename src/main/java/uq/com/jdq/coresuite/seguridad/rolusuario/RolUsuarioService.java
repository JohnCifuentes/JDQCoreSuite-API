package uq.com.jdq.coresuite.seguridad.rolusuario;

import uq.com.jdq.coresuite.seguridad.usuario.Usuario;

import java.util.List;

/**
 * Contrato de negocio para la gestion de asignaciones entre roles y usuarios.
 */
public interface RolUsuarioService {

    /**
     * Registra una nueva asignacion entre un rol y un usuario.
     * @param createRolUsuarioDTO datos de la asignacion.
     * @return asignacion creada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolUsuarioDTO createRolUsuario(CreateRolUsuarioDTO createRolUsuarioDTO) throws Exception;

    /**
     * Actualiza una asignacion existente.
     * @param id identificador de la asignacion.
     * @param updateRolUsuarioDTO nuevos datos de la asignacion.
     * @return asignacion actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolUsuarioDTO updateRolUsuario(Long id, UpdateRolUsuarioDTO updateRolUsuarioDTO) throws Exception;

    /**
     * Inactiva una asignacion existente.
     * @param id identificador de la asignacion.
     * @param inactiveRolUsuarioDTO datos del cambio de estado.
     * @return asignacion actualizada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolUsuarioDTO inactiveRolUsuario(Long id, InactiveRolUsuarioDTO inactiveRolUsuarioDTO) throws Exception;

    /**
     * Lista todas las asignaciones registradas.
     * @return lista de asignaciones.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseRolUsuarioDTO> getAllRolUsuarios() throws Exception;

    /**
     * Consulta una asignacion por identificador.
     * @param id identificador de la asignacion.
     * @return asignacion encontrada.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseRolUsuarioDTO getRolUsuarioById(Long id) throws Exception;

    /**
     * Lista las asignaciones asociadas a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de asignaciones.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseRolUsuarioDTO> getRolUsuariosByEmpresa(Long empresaId) throws Exception;

    /**
     * Obtiene los roles activos asignados a un usuario.
     * @param usuario usuario a consultar.
     * @return lista de roles del usuario.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseRolUsuarioDTO> getRolesUsuarioByUsuario(Usuario usuario) throws Exception;

}
