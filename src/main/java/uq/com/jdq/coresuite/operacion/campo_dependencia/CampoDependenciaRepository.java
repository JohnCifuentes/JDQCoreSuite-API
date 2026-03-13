package uq.com.jdq.coresuite.operacion.campo_dependencia;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.campo.Campo;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad CampoDependencia.
 */
public interface CampoDependenciaRepository extends JpaRepository<CampoDependencia, Long> {
    
    /**
     * Lista las dependencias asociadas a un campo.
     * @param campo entidad campo.
     * @return lista de dependencias del campo.
     */
    List<ResponseCampoDependenciaDTO> findByCampo(Campo campo);
    
}
