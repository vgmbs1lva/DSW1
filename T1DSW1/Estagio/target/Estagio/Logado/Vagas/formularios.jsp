<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Vaga</title>
</head>
<body>
<h1><c:choose>
    <c:when test="${not empty Vaga}">Editar Vaga</c:when>
    <c:otherwise>Criar Nova Vaga</c:otherwise>
</c:choose></h1>
<form action="<c:choose>
            <c:when test="${not empty Vaga}">update</c:when>
            <c:otherwise>insert</c:otherwise>
        </c:choose>" method="post">
    <c:if test="${not empty Vaga}">
        <input type="hidden" name="id" value="${Vaga.id}">
    </c:if>
    <label>Descrição:</label>
    <input type="text" name="descricao" value="${Vaga.descricao != null ? Vaga.descricao : ''}" required><br>
    <label>Remuneração:</label>
    <input type="text" name="remuneracao" value="${Vaga.remuneracao != null ? Vaga.remuneracao : ''}" required><br>
    <label>Data Limite:</label>
    <input type="date" name="dataLimiteInscricao" value="${Vaga.dataLimiteInscricao != null ? Vaga.dataLimiteInscricao : ''}" required><br>
    <input type="submit" value="Salvar">
</form>
<a href="list">Voltar para Lista de Vagas</a>
</body>
</html>
