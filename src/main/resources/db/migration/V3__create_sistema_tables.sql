CREATE SEQUENCE sistema.plan_seq START 1;

CREATE SEQUENCE sistema.empresa_seq START 1;

CREATE SEQUENCE sistema.licencia_seq START 1;

CREATE SEQUENCE sistema.sesion_seq START 1;

CREATE TABLE sistema.plan (
    id BIGINT PRIMARY KEY DEFAULT nextval('sistema.plan_seq'),
    cantidad_usuarios INT NOT NULL CHECK (cantidad_usuarios IN (1, 2, 3, 5, 8, 13, 21, 34)),
    nombre VARCHAR(50) NOT NULL UNIQUE,
    valor NUMERIC(12,2) NOT NULL,
    descripcion VARCHAR(255),
    estado VARCHAR(1) DEFAULT 'A',
    usuario_creacion VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(50),
    fecha_actualizacion TIMESTAMP
);

CREATE TABLE sistema.empresa (
    id BIGINT PRIMARY KEY DEFAULT nextval('sistema.empresa_seq'),
    tipo_identificacion_id BIGINT NOT NULL,
    pais_id BIGINT NOT NULL,
    departamento_id BIGINT NOT NULL,
    municipio_id BIGINT NOT NULL,
    numero_identificacion VARCHAR(20) NOT NULL UNIQUE,
    razon_social VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    correo_electronico VARCHAR(100),
    telefono VARCHAR(20),
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(50),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_tipo_identificacion FOREIGN KEY (tipo_identificacion_id) REFERENCES catalogo.tipo_identificacion(id),
    CONSTRAINT fk_pais FOREIGN KEY (pais_id) REFERENCES catalogo.pais(id),
    CONSTRAINT fk_departamento FOREIGN KEY (departamento_id) REFERENCES catalogo.departamento(id),
    CONSTRAINT fk_municipio FOREIGN KEY (municipio_id) REFERENCES catalogo.municipio(id)
);

CREATE TABLE sistema.licencia (
    id BIGINT PRIMARY KEY DEFAULT nextval('sistema.licencia_seq'),
    empresa_id BIGINT NOT NULL,
    plan_id BIGINT NOT NULL,
    fecha_compra DATE NOT NULL,
    fecha_expiracion DATE NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    estado VARCHAR(1) DEFAULT 'A',
    usuario_creacion VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(50),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_licencia_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id),
    CONSTRAINT fk_licencia_plan FOREIGN KEY (plan_id) REFERENCES sistema.plan(id)
);

CREATE TABLE sistema.sesion (
    id BIGINT PRIMARY KEY DEFAULT nextval('sistema.sesion_seq'),
    empresa_id BIGINT NOT NULL,
    usuario_id BIGINT,
    fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_ultimo_acceso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre TIMESTAMP,
    estado VARCHAR(1) NOT NULL DEFAULT 'A',
    usuario_creacion VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(50),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_sesion_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id)
);