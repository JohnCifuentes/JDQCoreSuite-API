package uq.com.jdq.coresuite.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad persistente para almacenar pagos iniciados a traves de Wompi Checkout.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments", schema = "sistema")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_generator")
    @SequenceGenerator(name = "payment_seq_generator", sequenceName = "sistema.payment_seq", allocationSize = 1)
    private Long id;

    @Column(name = "reference", nullable = false, unique = true, length = 120)
    private String reference;

    @Column(name = "amount_in_cents", nullable = false)
    private Long amountInCents;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "COP";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Column(name = "wompi_transaction_id", length = 120)
    private String wompiTransactionId;

    @Column(name = "status_message", length = 255)
    private String statusMessage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.status == null) {
            this.status = PaymentStatus.PENDING;
        }
        if (this.currency == null || this.currency.isBlank()) {
            this.currency = "COP";
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
