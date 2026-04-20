CREATE SEQUENCE sistema.payment_seq START 1;

CREATE TABLE sistema.payments (
    id BIGINT PRIMARY KEY DEFAULT nextval('sistema.payment_seq'),
    reference VARCHAR(120) NOT NULL UNIQUE,
    amount_in_cents BIGINT NOT NULL CHECK (amount_in_cents > 0),
    currency VARCHAR(3) NOT NULL DEFAULT 'COP',
    status VARCHAR(20) NOT NULL,
    plan_id BIGINT NOT NULL,
    wompi_transaction_id VARCHAR(120),
    status_message VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payments_plan FOREIGN KEY (plan_id) REFERENCES sistema.plan(id)
);

CREATE INDEX idx_payments_reference ON sistema.payments(reference);
CREATE INDEX idx_payments_status ON sistema.payments(status);
