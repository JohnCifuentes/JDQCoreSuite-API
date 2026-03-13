package uq.com.jdq.coresuite.seguridad.codigo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad Codigo.
 */
public interface CodigoRepository extends JpaRepository<Codigo, Long> {

    /**
     * Obtiene el codigo mas reciente generado para un usuario.
     * @param usuarioId identificador del usuario.
     * @return codigo encontrado, si existe.
     */
    Optional<Codigo> findTopByUsuarioIdOrderByFechaGeneracionDesc(Long usuarioId);

}
