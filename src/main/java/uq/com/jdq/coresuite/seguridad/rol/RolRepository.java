package uq.com.jdq.coresuite.seguridad.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Rol.
 */
public interface RolRepository extends JpaRepository<Rol, Long> {

    /**
     * Consulta los roles asociados a una empresa.
     * @param empresa empresa propietaria de los roles.
     * @return lista de roles de la empresa.
     */
    List<ResponseRolDTO> findByEmpresa(Empresa empresa);
    
    /**
     * Busca un rol por empresa y nombre.
     * @param empresa empresa propietaria del rol.
     * @param nombre nombre del rol.
     * @return rol encontrado, si existe.
     */
    Optional<Rol> findByEmpresaAndNombre(Empresa empresa, String nombre);
    
    /**
     * Busca un rol por empresa y nombre excluyendo un identificador especifico.
     * @param empresa empresa propietaria del rol.
     * @param nombre nombre del rol.
     * @param id identificador del rol que debe excluirse.
     * @return rol encontrado, si existe.
     */
    Optional<Rol> findByEmpresaAndNombreAndIdNot(Empresa empresa, String nombre, Long id);

}
