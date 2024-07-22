<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufscar.dc.dsw.domain.Vaga" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Candidatar-se à Vaga</title>
</head>
<body>
    <h1>Candidatar-se à Vaga</h1>
    <form action="${pageContext.request.contextPath}/candidatarVaga" method="post">
        <input type="hidden" name="id" value="${vaga.id}">
        <label>Descrição da Vaga:</label>
        <p>${vaga.descricao}</p>
        <label>Remuneração:</label>
        <p>${vaga.remuneracao}</p>
        <label>Data Limite:</label>
        <p>${vaga.dataLimiteInscricao}</p>
        <label>Conte-nos mais sobre você:</label>
        <textarea name="curriculo" rows="10" cols="50" required></textarea><br>
        <input type="submit" value="Enviar Candidatura">
    </form>
    <a href="${pageContext.request.contextPath}/listarVagas">Voltar</a>
</body>
</html>
