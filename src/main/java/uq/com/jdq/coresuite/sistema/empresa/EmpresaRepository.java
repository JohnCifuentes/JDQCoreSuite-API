package uq.com.jdq.coresuite.sistema.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByTipoIdentificacionAndNumeroIdentificacion(TipoIdentificacion tipoIdentificacion, String numeroIdentificacion);

    Optional<Empresa> findByCorreoElectronico(String correoElectronico);

    Optional<Object> findByTipoIdentificacionAndNumeroIdentificacionAndIdNot(TipoIdentificacion tipoIdentificacion, String s, Long id);

    Optional<Object> findByCorreoElectronicoAndIdNot(String correoElectronico, Long id);
}
