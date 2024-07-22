<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Candidatos</title>
</head>
<body>
<h1>Lista de Candidatos</h1>
<table border="1">
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Currículo</th>
        <th>Data de Candidatura</th>
    </tr>
    <c:forEach var="candidatura" items="${listaCandidaturas}">
        <tr>
            <td>${candidatura.profissional.nome}</td>
            <td>${candidatura.profissional.email}</td>
            <td>${candidatura.curriculo}</td>
            <td>${candidatura.dataCandidatura}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/vagas/listByEmpresa">Voltar</a>
</body>
</html>
