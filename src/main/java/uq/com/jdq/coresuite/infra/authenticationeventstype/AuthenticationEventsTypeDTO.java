package uq.com.jdq.coresuite.infra.authenticationeventstype;

import java.time.LocalDateTime;

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
