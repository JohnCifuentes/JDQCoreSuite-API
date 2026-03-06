package uq.com.jdq.coresuite.infra.autenticationevents;

import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsType;

public record AuthenticationEventsDTO(
    String email,
    AuthenticationEventsType eventType,
    String description
) {
}
