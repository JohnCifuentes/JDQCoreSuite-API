package uq.com.jdq.coresuite.catalogo.departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.catalogo.pais.Pais;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    List<DepartamentoDTO> findByPais(Pais pais);
}
