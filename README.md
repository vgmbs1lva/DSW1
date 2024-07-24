# DSW1 - Desenvolvimento de Softwares para Web 1

Repositório para a disciplina de **Desenvolvimento de Softwares para Web 1**.

### SGBD Utilizado
- **MySQL**

### Script de Criação
- Localização: `db/create.sql`

---

## Código de Criação do Banco de Dados

```sql
-- Dropar o banco de dados se já existir
DROP DATABASE IF EXISTS SistemaEstagioEmprego;

-- Criação do banco de dados
CREATE DATABASE SistemaEstagioEmprego;
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
);

-- Criação da tabela Empresas
CREATE TABLE IF NOT EXISTS Empresas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    cidade VARCHAR(100)
);

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
);

-- Tabela para armazenar informações das vagas
CREATE TABLE Vaga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa INT NOT NULL,
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(10, 2),
    data_limite_inscricao DATE,
    cidade VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresas(id) ON DELETE CASCADE
);

-- Tabela para armazenar os diferentes status de candidatura
CREATE TABLE StatusCandidatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

-- Inserindo status padrão na tabela StatusCandidatura
INSERT INTO StatusCandidatura (descricao) VALUES ('ABERTO'), ('NÃO SELECIONADO'), ('ENTREVISTA');

CREATE TABLE Candidatura (
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
);

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
('Empresa X', 'empresax@example.com', 'senha123', '12.345.678/0001-00', 'Empresa de tecnologia', 'São Paulo'),
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
(1, 'Desenvolvedor Java', 3000.00, '2024-08-01', 'São Paulo'),
(2, 'Analista de Marketing', 2500.00, '2024-08-15', 'Rio de Janeiro');

INSERT INTO Candidatura (id_profissional, id_vaga, curriculo, id_status, data_candidatura)
VALUES (1, 1, 'Sou muito bom no que eu faço', 1, CURRENT_TIMESTAMP);
```

---

## Responsabilidades de Implementação

| Requisito | Status | Eduardo Spinelli | Victor Germano |
|-----------|--------|------------------|----------------|
| **R1**    | ☑️ Implementado | 50% | 50% |
| **R2**    | ☑️ Implementado | 50% | 50% |
| **R3**    | ☑️ Implementado | 50% | 50% |
| **R4**    | ☑️ Implementado | 50% | 50% |
| **R5**    | ☑️ Implementado | 50% | 50% |
| **R6**    | ☑️ Implementado | 50% | 50% |
| **R7**    | ☑️ Implementado | 50% | 50% |
| **R8**    | ☑️ Implementado | 50% | 50% |
| **R9**    | ☑️ Implementado (80%) | 50% | 50% |


---

### Contribuidores
- **Eduardo Spinelli**
- **Victor Germano**

---

> Projeto desenvolvido como parte da disciplina de **Desenvolvimento de Softwares para Web 1**.
