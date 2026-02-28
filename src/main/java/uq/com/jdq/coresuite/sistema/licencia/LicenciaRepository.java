package uq.com.jdq.coresuite.sistema.licencia;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

    List<ResponseLicenciaDTO> findByEmpresa(Empresa empresa);

}
