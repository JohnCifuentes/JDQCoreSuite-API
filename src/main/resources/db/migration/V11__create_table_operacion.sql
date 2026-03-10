CREATE SEQUENCE operacion.tipo_validacion_seq;

CREATE SEQUENCE operacion.interfaz_seq;

CREATE SEQUENCE operacion.interface_grupo_campos_seq;

CREATE SEQUENCE operacion.campo_seq;

CREATE SEQUENCE operacion.campo_validacion_seq;

CREATE SEQUENCE operacion.campo_dependencia_seq;

CREATE TABLE operacion.tipo_validacion (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.tipo_validacion_seq'),
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP
);

CREATE TABLE operacion.interfaz (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.interfaz_seq'),
    modulo_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    indice INTEGER,
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_interfaz_modulo FOREIGN KEY (modulo_id) REFERENCES operacion.modulo(id)
);

CREATE TABLE operacion.interface_grupo_campos (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.interface_grupo_campos_seq'),
    interfaz_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    indice INTEGER,
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_grupo_interfaz FOREIGN KEY (interfaz_id) REFERENCES operacion.interfaz(id)
);

CREATE TABLE operacion.campo (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.campo_seq'),
    interfaz_id BIGINT NOT NULL,
    interface_grupo_campos_id BIGINT,
    tipo_campo_id BIGINT NOT NULL,
    lista_valores_id BIGINT,
    nombre VARCHAR(100) NOT NULL,
    etiqueta VARCHAR(150),
    descripcion VARCHAR(255),
    indice INTEGER,
    columnas INTEGER DEFAULT 12,
    valor_defecto VARCHAR(255),
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_campo_interfaz FOREIGN KEY (interfaz_id) REFERENCES operacion.interfaz(id),
    CONSTRAINT fk_campo_grupo FOREIGN KEY (interface_grupo_campos_id) REFERENCES operacion.interface_grupo_campos(id),
    CONSTRAINT fk_campo_tipo FOREIGN KEY (tipo_campo_id) REFERENCES operacion.tipo_campo(id),
    CONSTRAINT fk_campo_lista FOREIGN KEY (lista_valores_id) REFERENCES operacion.lista_valores(id)
);

CREATE TABLE operacion.campo_validacion (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.campo_validacion_seq'),
    campo_id BIGINT NOT NULL,
    tipo_validacion_id BIGINT NOT NULL,
    valor VARCHAR(255),
    campo_referencia_id BIGINT,
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_cv_campo FOREIGN KEY (campo_id) REFERENCES operacion.campo(id),
    CONSTRAINT fk_cv_tipo FOREIGN KEY (tipo_validacion_id) REFERENCES operacion.tipo_validacion(id),
    CONSTRAINT fk_cv_campo_ref FOREIGN KEY (campo_referencia_id) REFERENCES operacion.campo(id)
);

CREATE TABLE operacion.campo_dependencia (
    id BIGINT PRIMARY KEY DEFAULT nextval('operacion.campo_dependencia_seq'),
    campo_id BIGINT NOT NULL,
    campo_dependiente_id BIGINT NOT NULL,
    operador VARCHAR(50) NOT NULL,
    valor VARCHAR(255),
    estado VARCHAR(20) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(100),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_dependencia_campo FOREIGN KEY (campo_id) REFERENCES operacion.campo(id),
    CONSTRAINT fk_dependencia_campo_dep FOREIGN KEY (campo_dependiente_id) REFERENCES operacion.campo(id)
);