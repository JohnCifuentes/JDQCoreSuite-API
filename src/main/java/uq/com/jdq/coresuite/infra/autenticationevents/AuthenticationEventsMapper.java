package uq.com.jdq.coresuite.infra.autenticationevents;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationEventsMapper {

    AuthenticationEvents toEntity(AuthenticationEventsDTO authenticationEventsDTO);

    AuthenticationEventsDTO toDTO(AuthenticationEvents authenticationEventsType);

}
