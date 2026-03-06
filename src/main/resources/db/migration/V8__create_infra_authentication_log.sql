CREATE TABLE infra.authentication_events_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    created_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at TIMESTAMP
);

CREATE TABLE infra.authentication_events (
    id BIGSERIAL PRIMARY KEY,
    event_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    email VARCHAR(255) NOT NULL,
    event_type_id INTEGER NOT NULL,
    description TEXT,
    CONSTRAINT fk_authentication_event_type FOREIGN KEY (event_type_id)
        REFERENCES infra.authentication_events_type(id)
);

INSERT INTO infra.authentication_events_type (name, description, created_by)
VALUES
    ('LOGIN_SUCCESS', 'Inicio de sesión correcto', 'system'),
    ('LOGIN_FAILED_USER_NOT_FOUND', 'Inicio de sesión incorrecto: el correo electrónico o usuario no existe', 'system'),
    ('LOGIN_FAILED_INVALID_CREDENTIALS', 'Inicio de sesión incorrecto: credenciales inválidas','system'),
    ('LOGIN_FAILED_LICENSE_INACTIVE','Inicio de sesión incorrecto: la licencia de la empresa se encuentra inactiva','system'),
    ('LOGIN_FAILED_LICENSE_LIMIT_REACHED','Inicio de sesión incorrecto: el número de sesiones activas supera el permitido por la licencia','system'),
    ('LOGIN_FAILED_ACCOUNT_LOCKED','Inicio de sesión incorrecto: usuario bloqueado por exceso de credenciales incorrectas','system'),
    ('PASSWORD_RESET_REQUIRED','Inicio de sesión requiere restablecimiento de contraseña','system'),
    ('FIRST_LOGIN_REQUIRED','Inicio de sesión requiere configuración inicial por primer acceso','system');