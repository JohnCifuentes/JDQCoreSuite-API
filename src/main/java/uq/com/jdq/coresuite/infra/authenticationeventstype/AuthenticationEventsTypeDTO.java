package uq.com.jdq.coresuite.infra.authenticationeventstype;

import java.time.LocalDateTime;

/**
 * Define la estructura y comportamiento de record AuthenticationEventsTypeDTO.
 */
public record AuthenticationEventsTypeDTO(
    Long id,
    String name,
    String description,
    Boolean status,
    String createdBy,
    LocalDateTime createdAt,
    String updatedBy,
    LocalDateTime updatedAt
) {
}
