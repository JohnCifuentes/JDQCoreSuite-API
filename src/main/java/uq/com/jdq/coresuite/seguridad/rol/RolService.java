package uq.com.jdq.coresuite.seguridad.rol;

import java.util.List;

public interface RolService {

    ResponseRolDTO createRol(CreateRolDTO createRolDTO) throws Exception;

    ResponseRolDTO updateRol(Long id, UpdateRolDTO updateRolDTO) throws Exception;

    ResponseRolDTO inactiveRol(Long id, InactiveRolDTO inactiveRolDTO) throws Exception;

    List<ResponseRolDTO> getAllRoles() throws Exception;

    ResponseRolDTO getRolById(Long id) throws Exception;

    List<ResponseRolDTO> getRolsByEmpresa(Long empresaId) throws Exception;

}
