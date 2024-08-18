-- Remover o banco de dados Livraria se ele já existir
DROP DATABASE IF EXISTS Livraria;

-- Criação do banco de dados Livraria se não existir
CREATE DATABASE IF NOT EXISTS Livraria;

-- Usar o banco de dados Livraria
USE Livraria;

-- Criação da tabela Editora se não existir
CREATE TABLE IF NOT EXISTS Editora (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    CNPJ VARCHAR(60) NOT NULL UNIQUE,
    nome VARCHAR(60) NOT NULL UNIQUE
);

-- Criação da tabela Livro se não existir
CREATE TABLE IF NOT EXISTS Livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(60) NOT NULL,
    autor VARCHAR(60) NOT NULL,
    ano INT NOT NULL,
    preco DECIMAL(7, 2) NOT NULL,
    editora_id BIGINT,
    CONSTRAINT FK_Editora FOREIGN KEY (editora_id) REFERENCES Editora(id)
);

-- Inserção de dados na tabela Editora se não existirem registros
INSERT INTO Editora (CNPJ, nome) VALUES
('55.789.390/0008-99', 'Companhia das Letras'),
('32.106.536/0001-82', 'Objetiva'),
('71.150.470/0001-40', 'Record')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);

-- Inserção de dados na tabela Livro se não existirem registros
INSERT INTO Livro (titulo, autor, ano, preco, editora_id) VALUES
('Diálogos Impossíveis', 'Luis Fernando Verissimo', 2012, 22.90, 1),
('Ensaio sobre a Cegueira', 'José Saramago', 1995, 54.90, 2),
('Cem anos de Solidão', 'Gabriel Garcia Márquez', 1977, 59.90, 3)
ON DUPLICATE KEY UPDATE autor = VALUES(autor);
