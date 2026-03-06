package uq.com.jdq.coresuite.infra.authenticationeventstype;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationEventsTypeMapper {

    AuthenticationEventsTypeDTO toDTO(AuthenticationEventsType authenticationEventsType);

}
