package uq.com.jdq.coresuite.infra.autenticationevents;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uq.com.jdq.coresuite.infra.authenticationeventstype.AuthenticationEventsType;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "authentication_events", schema = "infra")
public class AuthenticationEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_timestamp", nullable = false)
    private LocalDateTime eventTimestamp = LocalDateTime.now();

    @Column(nullable = false, length = 255)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    private AuthenticationEventsType eventType;

    @Column
    private String description;

}
