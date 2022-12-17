CREATE TABLE IF NOT EXISTS public.paciente
(
    codigo BIGSERIAL NOT NULL,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL,
    status TEXT DEFAULT 'ATIVO',
    PRIMARY KEY (codigo)
);
