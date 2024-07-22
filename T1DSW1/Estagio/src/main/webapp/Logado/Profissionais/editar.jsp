<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Profissional</title>
</head>
<body>
<h1>Editar Profissional</h1>
<form action="${pageContext.request.contextPath}/profissionais/update" method="post">
    <input type="hidden" name="id" value="${profissional.id}">
    <label>Nome:</label>
    <input type="text" name="nome" value="${profissional.nome}" required><br>
    <label>CPF:</label>
    <input type="text" name="cpf" value="${profissional.cpf}" required><br>
    <label>Telefone:</label>
    <input type="text" name="telefone" value="${profissional.telefone}" required><br>
    <label>Sexo:</label>
    <input type="text" name="sexo" value="${profissional.sexo}" required><br>
    <label>Data de Nascimento:</label>
    <input type="date" name="dataNascimento" value="${profissional.dataNascimento}" required><br>
    <input type="submit" value="Salvar">
</form>
<a href="${pageContext.request.contextPath}/profissionais/list">Voltar para Lista de Profissionais</a>
</body>
</html>
