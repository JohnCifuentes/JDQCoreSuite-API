package uq.com.jdq.coresuite.infra.autenticationevents;

import java.util.List;

public interface AuthenticationEventsService {

    void createAuthenticationEvent(AuthenticationEventsDTO authenticationEventsDTO) throws Exception;

    List<AuthenticationEventsDTO> getAll();

    AuthenticationEventsDTO getAuthenticationEventsById(Long id) throws Exception;

}
