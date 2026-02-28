CREATE SEQUENCE seguridad.rol_seq START 1;

CREATE SEQUENCE seguridad.usuario_seq START 1;

CREATE SEQUENCE seguridad.rolusuario_seq START 1;

CREATE TABLE seguridad.rol (
    id BIGINT PRIMARY KEY DEFAULT nextval('seguridad.rol_seq'),
    empresa_id BIGINT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    estado VARCHAR(1) NOT NULL DEFAULT 'A' ,
    usuario_creacion VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_actualizacion VARCHAR(50),
    fecha_actualizacion TIMESTAMP,
    CONSTRAINT fk_rol_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id)
);

CREATE TABLE seguridad.usuario (
       id BIGINT PRIMARY KEY DEFAULT nextval('seguridad.usuario_seq'),
       empresa_id BIGINT NOT NULL,
       tipo_identificacion_id BIGINT NOT NULL,
       numero_identificacion VARCHAR(20) NOT NULL,
       nombre1 VARCHAR(50) NOT NULL,
       nombre2 VARCHAR(50),
       apellido1 VARCHAR(50) NOT NULL,
       apellido2 VARCHAR(50),
       telefono VARCHAR(50) NOT NULL,
       correo_electronico VARCHAR(100) NOT NULL,
       estado VARCHAR(1) NOT NULL DEFAULT 'A',
       usuario_creacion VARCHAR(50) NOT NULL,
       fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       usuario_actualizacion VARCHAR(50),
       fecha_actualizacion TIMESTAMP,
       CONSTRAINT fk_usuario_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id),
       CONSTRAINT fk_usuario_tipo_identificacion FOREIGN KEY (tipo_identificacion_id) REFERENCES catalogo.tipo_identificacion(id)
);

CREATE TABLE seguridad.rolusuario (
      id BIGINT PRIMARY KEY DEFAULT nextval('seguridad.rolusuario_seq'),
      empresa_id BIGINT NOT NULL,
      rol_id BIGINT NOT NULL,
      usuario_id BIGINT NOT NULL,
      estado VARCHAR(1) NOT NULL DEFAULT 'A',
      usuario_creacion VARCHAR(50) NOT NULL,
      fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      usuario_actualizacion VARCHAR(50),
      fecha_actualizacion TIMESTAMP,
      CONSTRAINT fk_rolusuario_empresa FOREIGN KEY (empresa_id) REFERENCES sistema.empresa(id),
      CONSTRAINT fk_rolusuario_rol FOREIGN KEY (rol_id) REFERENCES seguridad.rol(id),
      CONSTRAINT fk_rolusuario_usuario FOREIGN KEY (usuario_id) REFERENCES seguridad.usuario(id)
);