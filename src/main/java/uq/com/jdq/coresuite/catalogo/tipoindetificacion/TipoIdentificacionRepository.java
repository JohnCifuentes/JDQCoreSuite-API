package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad TipoIdentificacion.
 */
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {

    /**
     * Obtiene los tipos de identificacion activos.
     * @return lista de tipos de identificacion con estado activo.
     */
    List<TipoIdentificacion> findByEstadoTrue();

}
