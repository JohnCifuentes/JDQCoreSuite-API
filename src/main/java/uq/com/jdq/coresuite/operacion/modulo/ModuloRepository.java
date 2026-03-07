package uq.com.jdq.coresuite.operacion.modulo;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    List<ResponseModuloDTO> findByEmpresa(Empresa empresa);

}
