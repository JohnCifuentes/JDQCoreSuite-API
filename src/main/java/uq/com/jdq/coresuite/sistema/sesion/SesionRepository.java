package uq.com.jdq.coresuite.sistema.sesion;

import org.springframework.data.jpa.repository.JpaRepository;
import uq.com.jdq.coresuite.seguridad.usuario.Usuario;
import uq.com.jdq.coresuite.sistema.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Sesion.
 */
public interface SesionRepository extends JpaRepository<Sesion, Long> {

    /**
     * Consulta las sesiones asociadas a una empresa.
     * @param empresa empresa a consultar.
     * @return lista de sesiones de la empresa.
     */
    List<ResponseSesionDTO> findByEmpresa(Empresa empresa);

    /**
     * Busca la sesion activa mas reciente de un usuario.
     * @param usuario usuario a consultar.
     * @param estado estado de la sesion.
     * @return sesion encontrada, si existe.
     */
    Optional<Sesion> findTopByUsuarioAndEstadoOrderByFechaInicioDesc(Usuario usuario, String estado);

}
