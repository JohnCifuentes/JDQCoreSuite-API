package uq.com.jdq.coresuite.infra.autenticationevents;

import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsType;

/**
 * Define la estructura y comportamiento de record AuthenticationEventsDTO.
 */
public record AuthenticationEventsDTO(
    String email,
    AuthenticationEventsType eventType,
    String description
) {
}
