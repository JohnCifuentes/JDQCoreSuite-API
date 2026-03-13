package uq.com.jdq.coresuite.operacion.interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.operacion.modulo.Modulo;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Interfaz.
 */
public interface InterfazRepository extends JpaRepository<Interfaz, Long> {
    /**
     * Consulta las interfaces asociadas a un modulo.
     * @param modulo entidad modulo.
     * @return lista de interfaces relacionadas.
     */
    List<ResponseInterfazDTO> findByModulo(Modulo modulo);

    /**
     * Busca una interfaz por modulo y nombre.
     * @param modulo entidad modulo.
     * @param nombre nombre de la interfaz.
     * @return interfaz encontrada, si existe.
     */
    Optional<Interfaz> findByModuloAndNombre(Modulo modulo, String nombre);

    /**
     * Busca una interfaz por modulo y nombre excluyendo un identificador.
     * @param modulo entidad modulo.
     * @param nombre nombre de la interfaz.
     * @param id identificador a excluir.
     * @return interfaz encontrada, si existe.
     */
    Optional<Interfaz> findByModuloAndNombreAndIdNot(Modulo modulo, String nombre, Long id);

    /**
     * Busca una interfaz por modulo e indice.
     * @param modulo entidad modulo.
     * @param indice indice de la interfaz.
     * @return interfaz encontrada, si existe.
     */
    Optional<Interfaz> findByModuloAndIndice(Modulo modulo, Integer indice);

    /**
     * Busca una interfaz por modulo e indice excluyendo un identificador.
     * @param modulo entidad modulo.
     * @param indice indice de la interfaz.
     * @param id identificador a excluir.
     * @return interfaz encontrada, si existe.
     */
    Optional<Interfaz> findByModuloAndIndiceAndIdNot(Modulo modulo, Integer indice, Long id);

}
