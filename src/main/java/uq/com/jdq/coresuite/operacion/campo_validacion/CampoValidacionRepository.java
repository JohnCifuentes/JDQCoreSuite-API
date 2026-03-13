package uq.com.jdq.coresuite.operacion.campo_validacion;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.campo.Campo;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad CampoValidacion.
 */
public interface CampoValidacionRepository extends JpaRepository<CampoValidacion, Long> {

    /**
     * Consulta las validaciones asociadas a un campo.
     * @param campo entidad campo.
     * @return lista de validaciones relacionadas.
     */
    List<ResponseCampoValidacionDTO> findByCampo(Campo campo);

}
