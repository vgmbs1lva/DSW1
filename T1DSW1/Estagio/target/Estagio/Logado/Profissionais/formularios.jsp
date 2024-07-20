<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Profissional</title>
</head>
<body>
<h1><c:choose>
    <c:when test="${not empty Profissional}">Editar Profissional</c:when>
    <c:otherwise>Criar Novo Profissional</c:otherwise>
</c:choose></h1>
<form action="<c:choose>
            <c:when test="${not empty Profissional}">update</c:when>
            <c:otherwise>insert</c:otherwise>
        </c:choose>" method="post">
    <c:if test="${not empty Profissional}">
        <input type="hidden" name="id" value="${Profissional.id}">
    </c:if>
    <label>Nome:</label>
    <input type="text" name="nome" value="${Profissional.nome != null ? Profissional.nome : ''}" required><br>
    <label>CPF:</label>
    <input type="text" name="cpf" value="${Profissional.cpf != null ? Profissional.cpf : ''}" required><br>
    <label>Telefone:</label>
    <input type="text" name="telefone" value="${Profissional.telefone != null ? Profissional.telefone : ''}"><br>
    <label>Sexo:</label>
    <input type="text" name="sexo" value="${Profissional.sexo != null ? Profissional.sexo : ''}"><br>
    <label>Data de Nascimento:</label>
    <input type="date" name="dataNascimento" value="${Profissional.dataNascimento != null ? Profissional.dataNascimento : ''}" required><br>
    <input type="submit" value="Salvar">
</form>
<a href="list">Voltar para Lista de Profissionais</a>
</body>
</html>
