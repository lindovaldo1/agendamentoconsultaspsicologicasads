CREATE TABLE IF NOT EXISTS public.psicologo
(
    codigo bigserial NOT NULL,
    nome text NOT NULL,
    cpf text NOT NULL,
    specialty text,
    PRIMARY KEY (codigo)
);

INSERT INTO public.psicologo(nome, cpf, specialty) VALUES
('Grosbilda', '34987654321', 'PSICOLOGIA_SOCIAL'),
('Estrobilobaldo', '34912345678', 'NEUROPSICOLOGIA'),
('Zirgonisvaldo', '34912348765', 'PSICOLOGIA_SAUDE');
