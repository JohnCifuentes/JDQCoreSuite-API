package uq.com.jdq.coresuite.operacion.modulo;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Modulo.
 */
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    /**
     * Consulta los modulos asociados a una empresa.
     * @param empresa entidad empresa.
     * @return lista de modulos relacionados.
     */
    List<ResponseModuloDTO> findByEmpresa(Empresa empresa);

    /**
     * Busca un modulo por empresa y nombre.
     * @param empresa entidad empresa.
     * @param nombre nombre del modulo.
     * @return modulo encontrado, si existe.
     */
    Optional<Modulo> findByEmpresaAndNombre(Empresa empresa, String nombre);

    /**
     * Busca un modulo por empresa y nombre excluyendo un identificador.
     * @param empresa entidad empresa.
     * @param nombre nombre del modulo.
     * @param id identificador a excluir.
     * @return modulo encontrado, si existe.
     */
    Optional<Modulo> findByEmpresaAndNombreAndIdNot(Empresa empresa, String nombre, Long id);

    /**
     * Busca un modulo por empresa e indice.
     * @param empresa entidad empresa.
     * @param indice indice del modulo.
     * @return modulo encontrado, si existe.
     */
    Optional<Modulo> findByEmpresaAndIndice(Empresa empresa, Integer indice);

    /**
     * Busca un modulo por empresa e indice excluyendo un identificador.
     * @param empresa entidad empresa.
     * @param indice indice del modulo.
     * @param id identificador a excluir.
     * @return modulo encontrado, si existe.
     */
    Optional<Modulo> findByEmpresaAndIndiceAndIdNot(Empresa empresa, Integer indice, Long id);

}
