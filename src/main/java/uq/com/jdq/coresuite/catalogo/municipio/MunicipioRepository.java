package uq.com.jdq.coresuite.catalogo.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {


    List<MunicipioDTO> findByDepartamento(Departamento departamento);

}
