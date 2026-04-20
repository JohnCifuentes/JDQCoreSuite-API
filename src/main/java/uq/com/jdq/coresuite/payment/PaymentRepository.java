package uq.com.jdq.coresuite.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la gestion de pagos persistidos.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Busca un pago por su referencia unica.
     * @param reference referencia del pago.
     * @return pago encontrado si existe.
     */
    Optional<Payment> findByReference(String reference);

    /**
     * Valida si una referencia ya se encuentra registrada.
     * @param reference referencia a validar.
     * @return true si existe.
     */
    boolean existsByReference(String reference);
}
