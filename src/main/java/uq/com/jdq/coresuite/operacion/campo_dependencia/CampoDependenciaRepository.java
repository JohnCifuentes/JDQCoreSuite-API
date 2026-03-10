package uq.com.jdq.coresuite.operacion.campo_dependencia;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.campo.Campo;

import java.util.List;

public interface CampoDependenciaRepository extends JpaRepository<CampoDependencia, Long> {
    
    List<ResponseCampoDependenciaDTO> findByCampo(Campo campo);
    
}
