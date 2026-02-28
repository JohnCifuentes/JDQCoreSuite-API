CREATE SEQUENCE seguridad.codigo_seq START 1;

CREATE TABLE seguridad.codigo (
    id BIGINT PRIMARY KEY DEFAULT nextval('seguridad.codigo_seq'),
    usuario_id BIGINT NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    fecha_generacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT fk_codigos_usuario FOREIGN KEY (usuario_id) REFERENCES seguridad.usuario(id)
);