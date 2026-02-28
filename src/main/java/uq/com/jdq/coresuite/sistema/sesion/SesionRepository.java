package uq.com.jdq.coresuite.sistema.sesion;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {

    List<ResponseSesionDTO> findByEmpresa(Empresa empresa);

}
