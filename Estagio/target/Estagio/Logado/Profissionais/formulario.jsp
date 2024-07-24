<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Profissional</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
            font-family: 'Arial', sans-serif;
            margin: 0;
        }
        .form-container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .form-container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .form-container input[type="text"],
        .form-container input[type="email"],
        .form-container input[type="password"],
        .form-container input[type="date"],
        .form-container select {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .form-container input[type="submit"] {
            background: #fda085;
            border: none;
            color: white;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin: 10px 0;
            font-size: 16px;
        }
        .form-container input[type="submit"]:hover {
            background: #f6d365;
        }
        .form-container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin-top: 10px;
        }
        .form-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>${profissional.id == null ? 'Adicionar Profissional' : 'Editar Profissional'}</h1>
    <form action="${pageContext.request.contextPath}/profissionais/${profissional.id == null ? 'insert' : 'update'}" method="post">
        <input type="hidden" name="id" value="${profissional.id}">
        <label>Nome:</label>
        <input type="text" name="nome" value="${profissional.nome}" required><br>
        <label>Email:</label>
        <input type="email" name="email" value="${profissional.email}" required><br>
        <label>Senha:</label>
        <input type="password" name="senha" value="${profissional.senha}" required><br>
        <label>CPF:</label>
        <input type="text" name="cpf" value="${profissional.cpf}" required><br>
        <label>Telefone:</label>
        <input type="text" name="telefone" value="${profissional.telefone}" required><br>
        <label>Sexo:</label>
        <select name="sexo" required>
            <option value="M" ${profissional.sexo == 'M' ? 'selected' : ''}>Masculino</option>
            <option value="F" ${profissional.sexo == 'F' ? 'selected' : ''}>Feminino</option>
            <option value="Outro" ${profissional.sexo == 'Outro' ? 'selected' : ''}>Outro</option>
        </select><br>
        <label>Data de Nascimento:</label>
        <input type="date" name="data_nascimento" value="${profissional.dataNascimento}" required><br>
        <input type="submit" value="Salvar">
    </form>
    <a href="${pageContext.request.contextPath}/profissionais/list">Voltar</a>
</div>
</body>
</html>
