-- Dropar o banco de dados se já existir
DROP DATABASE IF EXISTS SistemaEstagioEmprego;

-- Criação do banco de dados
CREATE DATABASE SistemaEstagioEmprego;
USE SistemaEstagioEmprego;

-- Tabela para armazenar informações dos profissionais
CREATE TABLE Profissionais (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               nome VARCHAR(255) NOT NULL,
                               cpf VARCHAR(14) NOT NULL UNIQUE,
                               telefone VARCHAR(20),
                               sexo ENUM('M', 'F', 'Outro'),
                               data_nascimento DATE
);

-- Tabela para armazenar informações das empresas
CREATE TABLE Empresas (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          cnpj VARCHAR(18) NOT NULL UNIQUE,
                          descricao TEXT,
                          cidade VARCHAR(100)
);



-- Tabela para armazenar informações de login dos usuários
CREATE TABLE Usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL,
                         tipo ENUM('profissional', 'empresa', 'admin') NOT NULL,
                         id_profissional INT,
                         id_empresa INT,
                         FOREIGN KEY (id_profissional) REFERENCES Profissionais(id) ON DELETE SET NULL,
                         FOREIGN KEY (id_empresa) REFERENCES Empresas(id) ON DELETE SET NULL
);





-- Tabela para armazenar informações das vagas
CREATE TABLE Vaga (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      id_empresa INT NOT NULL,
                      descricao TEXT NOT NULL,
                      remuneracao DECIMAL(10, 2),
                      data_limite_inscricao DATE,
                      FOREIGN KEY (id_empresa) REFERENCES Empresas(id) ON DELETE CASCADE
);

-- Tabela para armazenar os diferentes status de candidatura
CREATE TABLE StatusCandidatura (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   descricao VARCHAR(50) NOT NULL
);

-- Inserindo status padrão na tabela StatusCandidatura
INSERT INTO StatusCandidatura (descricao) VALUES ('ABERTO'), ('NÃO SELECIONADO');

-- Tabela para armazenar as candidaturas dos profissionais às vagas
CREATE TABLE Candidatura (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             id_profissional INT NOT NULL,
                             id_vaga INT NOT NULL,
                             id_status INT NOT NULL DEFAULT 1, -- Status padrão: ABERTO
                             data_candidatura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (id_profissional) REFERENCES Profissionais(id) ON DELETE CASCADE,
                             FOREIGN KEY (id_vaga) REFERENCES Vaga(id) ON DELETE CASCADE,
                             FOREIGN KEY (id_status) REFERENCES StatusCandidatura(id)
);

-- Inserir usuário administrador
INSERT INTO Usuario (email, senha, tipo) VALUES ('admin@example.com', 'admin123', 'admin');
