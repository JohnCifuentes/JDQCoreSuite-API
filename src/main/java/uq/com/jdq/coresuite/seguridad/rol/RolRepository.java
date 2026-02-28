package uq.com.jdq.coresuite.seguridad.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {

    List<ResponseRolDTO> findByEmpresa(Empresa empresa);

}
