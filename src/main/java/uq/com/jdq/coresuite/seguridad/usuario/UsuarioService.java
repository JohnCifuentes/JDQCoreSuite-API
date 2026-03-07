package uq.com.jdq.coresuite.seguridad.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    ResponseUsuarioDTO createUsuario(CreateUsuarioDTO createUsuarioDTO) throws Exception;

    ResponseUsuarioDTO updateUsuario(Long id, UpdateUsuarioDTO updateUsuarioDTO) throws Exception;

    ResponseUsuarioDTO inactiveUsuario(Long id, InactiveUsuarioDTO inactiveUsuarioDTO) throws Exception;

    List<ResponseUsuarioDTO> getAllUsuarios() throws Exception;

    ResponseUsuarioDTO getUsuarioById(Long id) throws Exception;

    List<ResponseUsuarioDTO> getUsuariosByEmpresa(Long empresaId) throws Exception;

    Usuario getUsuarioByCorreoElectronicoAndPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception;

    ResponseUsuarioDTO recuperarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception;

    ResponseUsuarioDTO actualizarPassword(UsuarioCredencialesDTO usuarioCredencialesDTO) throws Exception;

    ResponseUsuarioDTO blockUsuario(String correoElectronico) throws Exception;

    ResponseUsuarioDTO unblockUsuario(Long id) throws Exception;

    Optional<Usuario> getUsuarioByCorreoElectronico(String correoElectronico) throws Exception;

}
