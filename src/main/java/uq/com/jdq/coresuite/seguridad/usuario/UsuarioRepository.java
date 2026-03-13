package uq.com.jdq.coresuite.seguridad.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Consulta los usuarios asociados a una empresa.
     * @param empresa empresa a consultar.
     * @return lista de usuarios de la empresa.
     */
    List<ResponseUsuarioDTO> findByEmpresa(Empresa empresa);

    /**
     * Busca un usuario por correo electronico.
     * @param correoElectronico correo del usuario.
     * @return usuario encontrado, si existe.
     * @throws Exception si ocurre un error en la consulta.
     */
    Optional<Usuario> findByCorreoElectronico(String correoElectronico) throws Exception;

}
