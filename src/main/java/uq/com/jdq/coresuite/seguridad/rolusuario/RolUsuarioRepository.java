package uq.com.jdq.coresuite.seguridad.rolusuario;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {

    List<ResponseRolUsuarioDTO> findByEmpresa(Empresa empresa);

}
