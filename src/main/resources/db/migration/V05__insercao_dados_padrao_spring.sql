INSERT INTO public.usuario (nome, senha, nome_usuario, ativo, data_nascimento) VALUES
('Grosbilda', '{noop}12345', 'grosbilda', true, '2000-06-06'),
('Estrobilobaldo','{noop}12345', 'estrobilobaldo', true, '2001-06-12'),
('Zirgonisvaldo', '{noop}12345', 'zirgonisvaldo', true, '2002-06-11');

INSERT INTO public.papel (codigo, nome) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_PACIENTE'),
(3, 'ROLE_PSICOLOGO');

INSERT INTO public.usuario_papel (codigo_usuario, codigo_papel) VALUES
(1, 1),
(2, 2),
(3, 1),
(3, 3);