INSERT INTO Usuario (nome, cpf, email, senha) VALUES ('John Doe', '31224480090', 'john@doe.com', '$2a$10$vtOst9bmafORf11rD3Hb8./7avk0.0O0UOWzJHBtawoGID.E1PIwm');
INSERT INTO Usuario (nome, cpf, email, senha) VALUES ('Admin', '31224480090', 'admin@admin.com', '$2a$10$vtOst9bmafORf11rD3Hb8./7avk0.0O0UOWzJHBtawoGID.E1PIwm');

INSERT INTO Perfil (id, nome) VALUES (1L, 'ROLE_USER');
INSERT INTO Perfil (id, nome) VALUES (2L, 'ROLE_ADMIN');

INSERT INTO Usuario_Perfis (USUARIO_ID, PERFIS_ID) VALUES (1,1);
INSERT INTO Usuario_Perfis (USUARIO_ID, PERFIS_ID) VALUES (2,2);

INSERT INTO Categoria (nome) VALUES ('Salário');
INSERT INTO Categoria (nome) VALUES ('Bonificação');
INSERT INTO Categoria (nome) VALUES ('Férias');
INSERT INTO Categoria (nome) VALUES ('Investimentos');
INSERT INTO Categoria (nome) VALUES ('Alimentação');
INSERT INTO Categoria (nome) VALUES ('Educação');
INSERT INTO Categoria (nome) VALUES ('Vestuário');
INSERT INTO Categoria (nome) VALUES ('Viagens');
INSERT INTO Categoria (nome) VALUES ('Carro');
INSERT INTO Categoria (nome) VALUES ('Saúde');
INSERT INTO Categoria (nome) VALUES ('Academia');
INSERT INTO Categoria (nome) VALUES ('TV/Internet');

INSERT INTO Lancamento (data, descricao, usuario_id, categoria_id, valor) VALUES ('2020-09-01 10:00:00', 'Salário', 1, 1, 8000);
INSERT INTO Lancamento (data, descricao, usuario_id, categoria_id, valor) VALUES ('2020-09-03 16:50:00', 'Supermercado', 1, 2, 2000);
INSERT INTO Lancamento (data, descricao, usuario_id, categoria_id, valor) VALUES ('2020-09-05 09:30:00', 'Faculdade', 1, 3, 1500);
INSERT INTO Lancamento (data, descricao, usuario_id, categoria_id, valor) VALUES ('2020-09-10 20:00:00', 'Restaurante', 1, 2, 200);
INSERT INTO Lancamento (data, descricao, usuario_id, categoria_id, valor) VALUES ('2020-10-01 10:00:00', 'Salário do Admin', 2, 1, 8000);
