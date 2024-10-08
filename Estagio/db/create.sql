--Dropar o banco de dados se já existir
DROP DATABASE IF EXISTS SistemaEstagioEmprego;

-- Criação do banco de dados
CREATE DATABASE SistemaEstagioEmprego CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE SistemaEstagioEmprego;

-- Criação da tabela Profissionais
CREATE TABLE IF NOT EXISTS Profissionais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    sexo ENUM('M', 'F', 'Outro'),
    data_nascimento DATE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Criação da tabela Empresas
CREATE TABLE IF NOT EXISTS Empresas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    cidade VARCHAR(100)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Criação da tabela Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('profissional', 'empresa', 'admin') NOT NULL,
    id_profissional INT,
    id_empresa INT,
    FOREIGN KEY (id_profissional) REFERENCES Profissionais(id) ON DELETE SET NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresas(id) ON DELETE SET NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tabela para armazenar informações das vagas
CREATE TABLE IF NOT EXISTS Vaga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa INT NOT NULL,
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(10, 2),
    data_limite_inscricao DATE,
    cidade VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresas(id) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tabela para armazenar os diferentes status de candidatura
CREATE TABLE IF NOT EXISTS StatusCandidatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Inserindo status padrão na tabela StatusCandidatura
INSERT INTO StatusCandidatura (descricao) VALUES ('ABERTO'), ('NÃO SELECIONADO'), ('ENTREVISTA');

CREATE TABLE IF NOT EXISTS Candidatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_profissional INT NOT NULL,
    id_vaga INT NOT NULL,
    curriculo VARCHAR(255) NOT NULL,
    id_status INT NOT NULL DEFAULT 1,
    data_candidatura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    entrevistaLink VARCHAR(255),
    entrevistaDataHora DATETIME,
    FOREIGN KEY (id_profissional) REFERENCES Profissionais(id) ON DELETE CASCADE,
    FOREIGN KEY (id_vaga) REFERENCES Vaga(id) ON DELETE CASCADE,
    FOREIGN KEY (id_status) REFERENCES StatusCandidatura(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Inserir usuário administrador
INSERT INTO Usuario (email, senha, tipo) VALUES ('admin@example.com', 'admin123', 'admin');

-- Inserir exemplos na tabela Profissionais
INSERT INTO Profissionais (nome, email, senha, cpf, telefone, sexo, data_nascimento)
VALUES 
('João da Silva', 'joao@example.com', 'senha123', '123.456.789-00', '(11) 98765-4321', 'M', '1990-01-01'),
('Maria de Souza', 'maria@example.com', 'senha123', '987.654.321-00', '(11) 91234-5678', 'F', '1992-02-02');

-- Inserir exemplos na tabela Empresas
INSERT INTO Empresas (nome, email, senha, cnpj, descricao, cidade)
VALUES 
('Empresa X', 'empresax@example.com', 'senha123', '12.345.678/0001-00', 'Empresa de tecnologia', 'Recife'),
('Empresa Y', 'empresay@example.com', 'senha123', '98.765.432/0001-00', 'Empresa de marketing', 'Rio de Janeiro');

-- Inserir exemplos na tabela Usuario para profissionais e empresas
INSERT INTO Usuario (email, senha, tipo, id_profissional) 
VALUES 
('joao@example.com', 'senha123', 'profissional', 1),
('maria@example.com', 'senha123', 'profissional', 2);

INSERT INTO Usuario (email, senha, tipo, id_empresa) 
VALUES 
('empresax@example.com', 'senha123', 'empresa', 1),
('empresay@example.com', 'senha123', 'empresa', 2);

-- Inserir exemplos na tabela Vaga
INSERT INTO Vaga (id_empresa, descricao, remuneracao, data_limite_inscricao, cidade)
VALUES 
(1, 'Desenvolvedor Java', 3000.00, '2024-08-01', 'Recife'),
(2, 'Analista de Marketing', 2500.00, '2024-08-15', 'Rio de Janeiro');

INSERT INTO Candidatura (id_profissional, id_vaga, curriculo, id_status, data_candidatura)
VALUES (1, 1, 'Sou muito bom no que eu faço', 1, CURRENT_TIMESTAMP);
