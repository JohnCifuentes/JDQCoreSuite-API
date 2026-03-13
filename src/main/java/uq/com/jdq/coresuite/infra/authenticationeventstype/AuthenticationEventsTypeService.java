package uq.com.jdq.coresuite.infra.authenticationeventstype;

import java.util.List;

/**
 * Define la estructura y comportamiento de interface AuthenticationEventsTypeService.
 */
public interface AuthenticationEventsTypeService {

    List<AuthenticationEventsTypeDTO> getAll() throws Exception;

    AuthenticationEventsType getAuthenticationEventsTypeById(Integer id) throws Exception;

}
