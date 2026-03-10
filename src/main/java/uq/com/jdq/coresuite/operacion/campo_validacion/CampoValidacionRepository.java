package uq.com.jdq.coresuite.operacion.campo_validacion;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.campo.Campo;

import java.util.List;

public interface CampoValidacionRepository extends JpaRepository<CampoValidacion, Long> {
    
    List<ResponseCampoValidacionDTO> findByCampo(Campo campo);
    
}
