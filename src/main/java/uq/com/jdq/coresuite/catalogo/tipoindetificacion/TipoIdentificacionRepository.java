package uq.com.jdq.coresuite.catalogo.tipoindetificacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {

    List<TipoIdentificacion> findByEstadoTrue();

}
