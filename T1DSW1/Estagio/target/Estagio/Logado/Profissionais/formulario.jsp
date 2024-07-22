<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Profissional</title>
</head>
<body>
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
</body>
</html>
