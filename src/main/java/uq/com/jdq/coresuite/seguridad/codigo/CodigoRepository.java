package uq.com.jdq.coresuite.seguridad.codigo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {

    Optional<Codigo> findTopByUsuarioIdOrderByFechaGeneracionDesc(Long usuarioId);

}
