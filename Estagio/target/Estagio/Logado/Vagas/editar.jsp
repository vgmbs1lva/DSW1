<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Vaga</title>
</head>
<body>
<h1>Editar Vaga</h1>
<form action="${pageContext.request.contextPath}/vagas/update" method="post">
    <input type="hidden" name="id" value="${vaga.id}">
    <label>Descrição:</label>
    <input type="text" name="descricao" value="${vaga.descricao}" required><br>
    <label>Remuneração:</label>
    <input type="text" name="remuneracao" value="${vaga.remuneracao}" required><br>
    <label>Data Limite:</label>
    <input type="date" name="dataLimiteInscricao" value="${vaga.dataLimiteInscricao}" required><br>
    <input type="submit" value="Salvar">
</form>
<a href="${pageContext.request.contextPath}/vagas/list">Voltar para Lista de Vagas</a>
</body>
</html>
