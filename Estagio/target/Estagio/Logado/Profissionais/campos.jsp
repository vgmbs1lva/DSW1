<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Campos de Profissional</title>
</head>
<body>
<form action="<c:choose>
            <c:when test="${not empty profissional}">update</c:when>
            <c:otherwise>insert</c:otherwise>
        </c:choose>" method="post">
    <c:if test="${not empty profissional}">
        <input type="hidden" name="id" value="${profissional.id}">
    </c:if>
    <label>Nome:</label>
    <input type="text" name="nome" value="${profissional.nome != null ? profissional.nome : ''}" required><br>
    <label>CPF:</label>
    <input type="text" name="cpf" value="${profissional.cpf != null ? profissional.cpf : ''}" required><br>
    <label>Telefone:</label>
    <input type="text" name="telefone" value="${profissional.telefone != null ? profissional.telefone : ''}"><br>
    <label>Sexo:</label>
    <input type="text" name="sexo" value="${profissional.sexo != null ? profissional.sexo : ''}"><br>
    <label>Data de Nascimento:</label>
    <input type="date" name="dataNascimento" value="${profissional.dataNascimento != null ? profissional.dataNascimento : ''}" required><br>
    <input type="submit" value="Salvar">
</form>
</body>
</html>
