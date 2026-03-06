package uq.com.jdq.coresuite.infra.authenticationeventstype;

import java.util.List;

public interface AuthenticationEventsTypeService {

    List<AuthenticationEventsTypeDTO> getAll() throws Exception;

    AuthenticationEventsTypeDTO getAuthenticationEventsTypeById(Integer id) throws Exception;

}
