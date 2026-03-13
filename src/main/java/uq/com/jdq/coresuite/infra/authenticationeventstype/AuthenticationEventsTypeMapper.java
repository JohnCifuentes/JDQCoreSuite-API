package uq.com.jdq.coresuite.infra.authenticationeventstype;

import org.mapstruct.Mapper;

/**
 * Define la estructura y comportamiento de interface AuthenticationEventsTypeMapper.
 */
@Mapper(componentModel = "spring")
public interface AuthenticationEventsTypeMapper {

    AuthenticationEventsTypeDTO toDTO(AuthenticationEventsType authenticationEventsType);

}
