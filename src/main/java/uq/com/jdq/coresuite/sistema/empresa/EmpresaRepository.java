package uq.com.jdq.coresuite.sistema.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.catalogo.tipoindetificacion.TipoIdentificacion;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Empresa.
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    /**
     * Busca una empresa por tipo y numero de identificacion.
     * @param tipoIdentificacion tipo de identificacion.
     * @param numeroIdentificacion numero de identificacion.
     * @return empresa encontrada, si existe.
     */
    Optional<Empresa> findByTipoIdentificacionAndNumeroIdentificacion(TipoIdentificacion tipoIdentificacion, String numeroIdentificacion);

    /**
     * Busca una empresa por correo electronico.
     * @param correoElectronico correo de la empresa.
     * @return empresa encontrada, si existe.
     */
    Optional<Empresa> findByCorreoElectronico(String correoElectronico);

    /**
     * Busca empresa por tipo y numero excluyendo un identificador.
     * @param tipoIdentificacion tipo de identificacion.
     * @param s numero de identificacion.
     * @param id identificador excluido.
     * @return resultado opcional de la consulta.
     */
    Optional<Object> findByTipoIdentificacionAndNumeroIdentificacionAndIdNot(TipoIdentificacion tipoIdentificacion, String s, Long id);

    /**
     * Busca empresa por correo excluyendo un identificador.
     * @param correoElectronico correo de la empresa.
     * @param id identificador excluido.
     * @return resultado opcional de la consulta.
     */
    Optional<Object> findByCorreoElectronicoAndIdNot(String correoElectronico, Long id);

}
