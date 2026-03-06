package uq.com.jdq.coresuite.infra.authenticationeventstype;

import java.util.List;

public interface AuthenticationEventsTypeService {

    List<AuthenticationEventsTypeDTO> getAll() throws Exception;

    AuthenticationEventsType getAuthenticationEventsTypeById(Integer id) throws Exception;

}
