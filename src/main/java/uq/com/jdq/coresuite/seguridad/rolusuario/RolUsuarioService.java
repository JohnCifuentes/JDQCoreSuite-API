package uq.com.jdq.coresuite.seguridad.rolusuario;

import java.util.List;

public interface RolUsuarioService {

    ResponseRolUsuarioDTO createRolUsuario(CreateRolUsuarioDTO createRolUsuarioDTO) throws Exception;

    ResponseRolUsuarioDTO updateRolUsuario(Long id, UpdateRolUsuarioDTO updateRolUsuarioDTO) throws Exception;

    ResponseRolUsuarioDTO inactiveRolUsuario(Long id, InactiveRolUsuarioDTO inactiveRolUsuarioDTO) throws Exception;

    List<ResponseRolUsuarioDTO> getAllRolUsuarios() throws Exception;

    ResponseRolUsuarioDTO getRolUsuarioById(Long id) throws Exception;

    List<ResponseRolUsuarioDTO> getRolUsuariosByEmpresa(Long empresaId) throws Exception;

}
