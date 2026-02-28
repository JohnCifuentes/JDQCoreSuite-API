CREATE TABLE catalogo.tipo_identificacion (
    id BIGINT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL
);

INSERT INTO catalogo.tipo_identificacion (id, codigo, nombre, estado) VALUES
    (1, 'CC', 'Cédula de Ciudadanía', true),
    (2, 'TI', 'Tarjeta de Identidad', true),
    (3, 'CE', 'Cédula de Extranjería', true),
    (4, 'PA', 'Pasaporte', true),
    (5, 'RC', 'Registro Civil', true),
    (6, 'NIT','Número de Identificación Tributaría', true),
    (7, 'PPT','Permiso por Protección Tempora', true),
    (8, 'PEP','Permiso Especial de Permanencia', true);