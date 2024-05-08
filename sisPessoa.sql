CREATE DATABASE sisPessoa;


-- Cria a tabela 'pessoa'
CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150),
    idade INT,
    sexo CHAR(2)
);

-- Cria a tabela 'endereco'
CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    estado CHAR(2),
    cidade VARCHAR(100),
    logradouro VARCHAR(100),
    numero INT,
    cep CHAR(8),
    id_pessoa INT,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);
