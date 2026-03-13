package uq.com.jdq.coresuite.catalogo.departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.catalogo.pais.Pais;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Departamento.
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    /**
     * Lista los departamentos asociados a un pais.
     * @param pais entidad pais.
     * @return lista de departamentos del pais.
     */
    List<DepartamentoDTO> findByPais(Pais pais);
}
