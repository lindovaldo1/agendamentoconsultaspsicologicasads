CREATE TABLE IF NOT EXISTS public.consulta
(
    codigo BIGSERIAL NOT NULL,
    codigo_psicologo BIGSERIAL NOT NULL,
    codigo_paciente BIGSERIAL NOT NULL,
    data DATE,
    horario TEXT,
    status TEXT,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.consulta
    ADD FOREIGN KEY (codigo_paciente)
    REFERENCES public.paciente (codigo)
    NOT VALID;

ALTER TABLE public.consulta
    ADD FOREIGN KEY (codigo_psicologo)
    REFERENCES public.psicologo (codigo)
    NOT VALID;