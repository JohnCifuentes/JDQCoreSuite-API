package uq.com.jdq.coresuite.infra.autenticationevents;

import java.util.List;

/**
 * Define la estructura y comportamiento de interface AuthenticationEventsService.
 */
public interface AuthenticationEventsService {

    AuthenticationEventsDTO createAuthenticationEvent(AuthenticationEventsDTO authenticationEventsDTO) throws Exception;

    List<AuthenticationEventsDTO> getAll();

    AuthenticationEventsDTO getAuthenticationEventsById(Long id) throws Exception;

}
