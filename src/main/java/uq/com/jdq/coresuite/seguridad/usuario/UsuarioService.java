package uq.com.jdq.coresuite.seguridad.usuario;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de negocio para la gestion de usuarios.
 */
public interface UsuarioService {

    /**
     * Registra un nuevo usuario.
     * @param createUsuarioDTO datos del usuario a crear.
     * @return usuario creado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO createUsuario(CreateUsuarioDTO createUsuarioDTO) throws Exception;

    /**
     * Actualiza un usuario existente.
     * @param id identificador del usuario.
     * @param updateUsuarioDTO nuevos datos del usuario.
     * @return usuario actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO updateUsuario(Long id, UpdateUsuarioDTO updateUsuarioDTO) throws Exception;

    /**
     * Inactiva un usuario existente.
     * @param id identificador del usuario.
     * @param inactiveUsuarioDTO datos del cambio de estado.
     * @return usuario actualizado.
     */
    ResponseUsuarioDTO inactiveUsuario(Long id, InactiveUsuarioDTO inactiveUsuarioDTO) throws Exception;

    /**
     * Lista todos los usuarios registrados.
     * @return lista de usuarios.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseUsuarioDTO> getAllUsuarios() throws Exception;

    /**
     * Consulta un usuario por identificador.
     * @param id identificador del usuario.
     * @return usuario encontrado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO getUsuarioById(Long id) throws Exception;

    /**
     * Lista los usuarios asociados a una empresa.
     * @param empresaId identificador de la empresa.
     * @return lista de usuarios.
     * @throws Exception si ocurre un error de negocio.
     */
    List<ResponseUsuarioDTO> getUsuariosByEmpresa(Long empresaId) throws Exception;

    /**
     * Valida un usuario a partir de sus credenciales.
     * @param usuarioCredencialesDTO correo y contrasena del usuario.
     * @return entidad del usuario autenticado.
     * @throws Exception si ocurre un error de negocio.
     */
    Usuario getUsuarioByCorreoElectronicoAndPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception;

    /**
     * Recupera la contrasena de un usuario.
     * @param usuarioCredencialesDTO correo y nueva contrasena.
     * @return usuario actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO recuperarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception;

    /**
     * Actualiza la contrasena de un usuario.
     * @param usuarioCredencialesDTO correo y nueva contrasena.
     * @return usuario actualizado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO actualizarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception;

    /**
     * Bloquea un usuario por su correo electronico.
     * @param correoElectronico correo del usuario.
     * @return usuario bloqueado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO blockUsuario(String correoElectronico) throws Exception;

    /**
     * Desbloquea un usuario por su identificador.
     * @param usuarioId identificador del usuario.
     * @return usuario desbloqueado.
     * @throws Exception si ocurre un error de negocio.
     */
    ResponseUsuarioDTO unblockUsuario(Long usuarioId) throws Exception;

    /**
     * Busca un usuario por correo electronico.
     * @param correoElectronico correo a consultar.
     * @return usuario encontrado, si existe.
     * @throws Exception si ocurre un error de negocio.
     */
    Optional<Usuario> getUsuarioByCorreoElectronico(String correoElectronico) throws Exception;

    /**
     * Consulta una entidad de usuario por identificador.
     * @param usuarioId identificador del usuario.
     * @return entidad encontrada.
     * @throws Exception si ocurre un error de negocio.
     */
    Usuario getById(Long usuarioId) throws Exception;

}
