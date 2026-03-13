package uq.com.jdq.coresuite.catalogo.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.catalogo.departamento.Departamento;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Municipio.
 */
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    /**
     * Lista los municipios asociados a un departamento.
     * @param departamento entidad departamento.
     * @return lista de municipios del departamento.
     */
    List<MunicipioDTO> findByDepartamento(Departamento departamento);

}
