-- Drop the existing database if it exists
DROP DATABASE IF EXISTS Estagios;

-- Create a new database
CREATE DATABASE Estagios;

-- Use the new database
USE Estagios;

-- Create the Profissional table
CREATE TABLE Profissional (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(256) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(256) NOT NULL,
    telefone VARCHAR(20),
    sexo CHAR(1),
    data_nascimento DATE,
    PRIMARY KEY (id)
);

-- Create the Empresa table
CREATE TABLE Empresa (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(256) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    nome VARCHAR(256) NOT NULL,
    descricao TEXT,
    cidade VARCHAR(256),
    PRIMARY KEY (id)
);

-- Create the Usuario table
CREATE TABLE Usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(256) NOT NULL,
    login VARCHAR(256) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL,
    papel VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

-- Insert initial users (administrador, usuario empresa, usuario profissional)
INSERT INTO Usuario (nome, login, senha, papel) VALUES 
('Administrador', 'admin', 'admin', 'ADMIN'),
('Usuario Empresa', 'user_emp', 'user_emp', 'USER_EMPRESA'),
('Usuario Profissional', 'user_prof', 'user_prof', 'USER_PROFISSIONAL');

-- Create the Vaga table
CREATE TABLE Vaga (
    id BIGINT NOT NULL AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(10, 2),
    data_limite_inscricao DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (empresa_id) REFERENCES Empresa(id)
);

-- Create the Candidatura table
CREATE TABLE Candidatura (
    id BIGINT NOT NULL AUTO_INCREMENT,
    vaga_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    data_candidatura DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'ABERTO',
    curriculo_path VARCHAR(512),
    entrevista_horario DATETIME,
    entrevista_link VARCHAR(512),
    PRIMARY KEY (id),
    FOREIGN KEY (vaga_id) REFERENCES Vaga(id),
    FOREIGN KEY (profissional_id) REFERENCES Profissional(id)
);

-- Insert sample data into Empresa
INSERT INTO Empresa (email, senha, cnpj, nome, descricao, cidade) VALUES 
('empresaA@example.com', 'senhaA', '55.789.390/0008-99', 'Empresa A', 'Descrição da Empresa A', 'Cidade A'),
('empresaB@example.com', 'senhaB', '71.150.470/0001-40', 'Empresa B', 'Descrição da Empresa B', 'Cidade B'),
('empresaC@example.com', 'senhaC', '32.106.536/0001-82', 'Empresa C', 'Descrição da Empresa C', 'Cidade C');

-- Insert sample data into Profissional
INSERT INTO Profissional (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES 
('profissional1@example.com', 'senha1', '123.456.789-00', 'Profissional 1', '1111-1111', 'M', '1990-01-01'),
('profissional2@example.com', 'senha2', '987.654.321-00', 'Profissional 2', '2222-2222', 'F', '1992-02-02'),
('profissional3@example.com', 'senha3', '111.222.333-44', 'Profissional 3', '3333-3333', 'M', '1994-03-03');

-- Insert sample data into Vaga
INSERT INTO Vaga (empresa_id, descricao, remuneracao, data_limite_inscricao) VALUES 
(1, 'Estágio em Desenvolvimento de Software', 1500.00, '2024-08-01'),
(2, 'Estágio em Marketing Digital', 1200.00, '2024-09-01'),
(3, 'Estágio em Design Gráfico', 1000.00, '2024-10-01');

-- Insert sample data into Candidatura
INSERT INTO Candidatura (vaga_id, profissional_id, data_candidatura, curriculo_path) VALUES 
(1, 1, '2024-07-01', '/path/to/curriculo1.pdf'),
(2, 2, '2024-07-02', '/path/to/curriculo2.pdf'),
(3, 3, '2024-07-03', '/path/to/curriculo3.pdf');
