-- create table tarefa(
--     Id int NOT NULL UNIQUE,
--     titulo varchar (50),
--     descricao varchar (200),
--     responsavel varchar (50),
--     prioridade int,
--     deadline date,
--     CONSTRAINT pk_cod_cliente PRIMARY KEY (Id)
--     );
INSERT INTO tarefa (ID,titulo,descricao,responsavel,prioridade,deadline)
VALUES 
(1, 'Tarefa01', 'Uma descrição da tarefa01', 'Paulo', '1', 'segunda'),
(2, 'Tarefa02', 'Uma descrição da tarefa02', 'Paulo', '2', 'segunda'),
(3, 'Tarefa03', 'Uma descrição da tarefa03', 'Paulo', '3', 'segunda');
