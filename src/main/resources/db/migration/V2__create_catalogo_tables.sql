CREATE TABLE catalogo.pais (
    id BIGINT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    iso3 VARCHAR(3) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE catalogo.departamento (
    id BIGINT PRIMARY KEY,
    pais_id BIGINT NOT NULL,
    codigo VARCHAR(10) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    abreviatura VARCHAR(10) NOT NULL,
    CONSTRAINT fk_departamento_pais FOREIGN KEY (pais_id)
        REFERENCES catalogo.pais(id),
    CONSTRAINT uq_departamento_codigo_por_pais UNIQUE (pais_id, codigo)
);

CREATE TABLE catalogo.municipio (
    id BIGSERIAL PRIMARY KEY,
    departamento_id BIGINT NOT NULL,
    codigo VARCHAR(10) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    CONSTRAINT fk_municipio_departamento FOREIGN KEY (departamento_id)
        REFERENCES catalogo.departamento(id),
    CONSTRAINT uq_municipio_codigo_por_departamento UNIQUE (departamento_id, codigo)
);

CREATE TABLE catalogo.genero (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    abreviatura VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO catalogo.pais (id, nombre, iso3, codigo) VALUES
    (1, 'Colombia', 'COL', '170');

-- Insert en tabla departamento
INSERT INTO catalogo.departamento (id, pais_id, codigo, nombre, abreviatura) VALUES
    (1, 1, '05', 'Antioquia', 'ANT'),
    (2, 1, '08', 'Atlántico', 'ATL'),
    (3, 1, '11', 'Bogotá, D.C.', 'DC'),
    (4, 1, '13', 'Bolívar', 'BOL'),
    (5, 1, '15', 'Boyacá', 'BOY'),
    (6, 1, '17', 'Caldas', 'CAL'),
    (7, 1, '18', 'Caquetá', 'CAQ'),
    (8, 1, '19', 'Cauca', 'CAU'),
    (9, 1, '20', 'Cesar', 'CES'),
    (10, 1, '23', 'Córdoba', 'COR'),
    (11, 1, '25', 'Cundinamarca', 'CUN'),
    (12, 1, '27', 'Chocó', 'CHO'),
    (13, 1, '41', 'Huila', 'HUI'),
    (14, 1, '44', 'La Guajira', 'LAG'),
    (15, 1, '47', 'Magdalena', 'MAG'),
    (16, 1, '50', 'Meta', 'MET'),
    (17, 1, '52', 'Nariño', 'NAR'),
    (18, 1, '54', 'Norte de Santander', 'NSA'),
    (19, 1, '63', 'Quindío', 'QUI'),
    (20, 1, '66', 'Risaralda', 'RIS'),
    (21, 1, '68', 'Santander', 'SAN'),
    (22, 1, '70', 'Sucre', 'SUC'),
    (23, 1, '73', 'Tolima', 'TOL'),
    (24, 1, '76', 'Valle del Cauca', 'VAC'),
    (25, 1, '81', 'Arauca', 'ARA'),
    (26, 1, '85', 'Casanare', 'CAS'),
    (27, 1, '86', 'Putumayo', 'PUT'),
    (28, 1, '88', 'Archipiélago de San Andrés, Providencia y Santa Catalina', 'SAP'),
    (29, 1, '91', 'Amazonas', 'AMA'),
    (30, 1, '94', 'Guainía', 'GUA'),
    (31, 1, '95', 'Guaviare', 'GUV'),
    (32, 1, '97', 'Vaupés', 'VAU'),
    (33, 1, '99', 'Vichada', 'VID');

-- Insert en tabla municipio (solo Quindío, departamento_id = 19)
INSERT INTO catalogo.municipio (id, departamento_id, codigo, nombre) VALUES
    (1, 19, '63001', 'Armenia'),
    (2, 19, '63111', 'Buenavista'),
    (3, 19, '63130', 'Calarcá'),
    (4, 19, '63190', 'Circasia'),
    (5, 19, '63212', 'Córdoba'),
    (6, 19, '63272', 'Filandia'),
    (7, 19, '63302', 'Génova'),
    (8, 19, '63401', 'La Tebaida'),
    (9, 19, '63470', 'Montenegro'),
    (10, 19, '63548', 'Pijao'),
    (11, 19, '63594', 'Quimbaya'),
    (12, 19, '63690', 'Salento');

-- Insert en tabla genero
INSERT INTO catalogo.genero (id, nombre, abreviatura) VALUES
    (1, 'Masculino', 'M'),
    (2, 'Femenino', 'F'),
    (3, 'Otro', 'O');