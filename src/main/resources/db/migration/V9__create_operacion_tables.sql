-- SECUENCIA
CREATE SEQUENCE operacion.modulo_seq START WITH 1 INCREMENT BY 1;

-- TABLA
CREATE TABLE operacion.modulo (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.modulo_seq'),
    empresa_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    indice INTEGER,
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_modulo_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id)
);

-- SECUENCIA
CREATE SEQUENCE operacion.tipo_campo_seq START WITH 1 INCREMENT BY 1;

-- TABLA
CREATE TABLE operacion.tipo_campo (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.tipo_campo_seq'),
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP
);

-- SECUENCIA
CREATE SEQUENCE operacion.lista_valores_seq START WITH 1 INCREMENT BY 1;

-- TABLA
CREATE TABLE operacion.lista_valores (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.lista_valores_seq'),
    empresa_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_lista_valores_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id)
);

-- SECUENCIA
CREATE SEQUENCE operacion.lista_valores_detalle_seq START WITH 1 INCREMENT BY 1;

-- TABLA
CREATE TABLE operacion.lista_valores_detalle (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.lista_valores_detalle_seq'),
    lista_valores_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_lista_valores_detalle_lista FOREIGN KEY (lista_valores_id) REFERENCES operacion.lista_valores(id)
);

