<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Vagas</title>
</head>
<body>
<h1>Lista de Vagas</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Empresa</th> <!-- Adicionado -->
        <th>Descrição</th>
        <th>Remuneração</th>
        <th>Data Limite</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="vaga" items="${listaVagas}">
        <tr>
            <td>${vaga.id}</td>
            <td>${vaga.empresa.nome}</td> <!-- Adicionado -->
            <td>${vaga.descricao}</td>
            <td>${vaga.remuneracao}</td>
            <td>${vaga.dataLimiteInscricao}</td>
            <td>
                <a href="edit?id=${vaga.id}">Editar</a>
                <a href="delete?id=${vaga.id}" onclick="return confirm('Tem certeza?')">Deletar</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/Logado/Admin/index.jsp">Voltar</a>
</body>
</html>
