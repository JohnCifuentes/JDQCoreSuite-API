package uq.com.jdq.coresuite.seguridad.rolusuario;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.seguridad.rol.Rol;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad RolUsuario.
 */
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {

    /**
     * Consulta las asignaciones rol-usuario de una empresa.
     * @param empresa empresa a consultar.
     * @return lista de asignaciones de la empresa.
     */
    List<ResponseRolUsuarioDTO> findByEmpresa(Empresa empresa);

    /**
     * Obtiene los roles activos asociados a un usuario.
     * @param usuario usuario a consultar.
     * @param estado estado de las asignaciones.
     * @return lista de roles asignados al usuario.
     */
    List<ResponseRolUsuarioDTO> getByUsuarioAndEstado(Usuario usuario, String estado);

    /**
     * Busca una asignacion exacta entre un usuario y un rol.
     * @param usuario usuario asociado.
     * @param rol rol asociado.
     * @return asignacion encontrada, si existe.
     */
    Optional<RolUsuario> findByUsuarioAndRol(Usuario usuario, Rol rol);

}
