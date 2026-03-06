package uq.com.jdq.coresuite.infra.authenticationeventstype;

import java.util.List;

public interface AuthenticationEventsTypeService {

    List<AuthenticationEventsTypeDTO> getAll();

    AuthenticationEventsTypeDTO getAuthenticationEventsTypeById(Long id) throws Exception;

}
