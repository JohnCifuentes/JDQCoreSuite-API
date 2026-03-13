package uq.com.jdq.coresuite.infra.autenticationevents;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Define la estructura y comportamiento de interface AuthenticationEventsRepository.
 */
public interface AuthenticationEventsRepository extends JpaRepository<AuthenticationEvents, Long> {


}
