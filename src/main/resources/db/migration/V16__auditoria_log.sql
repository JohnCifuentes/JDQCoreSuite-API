create table infra.log (
    id bigserial primary key,
    esquema text not null,
    tabla text not null,
    operacion text not null, -- INSERT, UPDATE, DELETE
    fecha timestamp default now(),
    usuario text,
    datos_antes jsonb,
    datos_despues jsonb
);