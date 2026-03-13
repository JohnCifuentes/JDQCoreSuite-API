package uq.com.jdq.coresuite.sistema.licencia;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Licencia.
 */
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

    /**
     * Consulta las licencias asociadas a una empresa.
     * @param empresa empresa a consultar.
     * @return lista de licencias de la empresa.
     */
    List<ResponseLicenciaDTO> findByEmpresa(Empresa empresa);

}
