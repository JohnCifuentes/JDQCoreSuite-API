package uq.com.jdq.coresuite.operacion.interface_grupo_campos;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.interfaz.Interfaz;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad InterfaceGrupoCampos.
 */
public interface InterfaceGrupoCamposRepository extends JpaRepository<InterfaceGrupoCampos, Long> {
    /**
     * Consulta los grupos de campos asociados a una interfaz.
     * @param interfaz entidad interfaz.
     * @return lista de grupos de campos relacionados.
     */
    List<ResponseInterfaceGrupoCamposDTO> findByInterfaz(Interfaz interfaz);

    /**
     * Busca un grupo de campos por interfaz y nombre.
     * @param interfaz entidad interfaz.
     * @param nombre nombre del grupo.
     * @return grupo de campos encontrado, si existe.
     */
    Optional<InterfaceGrupoCampos> findByInterfazAndNombre(Interfaz interfaz, String nombre);

    /**
     * Busca un grupo de campos por interfaz y nombre excluyendo un identificador.
     * @param interfaz entidad interfaz.
     * @param nombre nombre del grupo.
     * @param id identificador a excluir.
     * @return grupo de campos encontrado, si existe.
     */
    Optional<InterfaceGrupoCampos> findByInterfazAndNombreAndIdNot(Interfaz interfaz, String nombre, Long id);

    /**
     * Busca un grupo de campos por interfaz e indice.
     * @param interfaz entidad interfaz.
     * @param indice indice del grupo.
     * @return grupo de campos encontrado, si existe.
     */
    Optional<InterfaceGrupoCampos> findByInterfazAndIndice(Interfaz interfaz, Integer indice);

    /**
     * Busca un grupo de campos por interfaz e indice excluyendo un identificador.
     * @param interfaz entidad interfaz.
     * @param indice indice del grupo.
     * @param id identificador a excluir.
     * @return grupo de campos encontrado, si existe.
     */
    Optional<InterfaceGrupoCampos> findByInterfazAndIndiceAndIdNot(Interfaz interfaz, Integer indice, Long id);

}
