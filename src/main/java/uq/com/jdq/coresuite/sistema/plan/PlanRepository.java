package uq.com.jdq.coresuite.sistema.plan;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad Plan.
 */
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
