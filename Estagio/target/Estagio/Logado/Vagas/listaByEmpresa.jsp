<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Minhas Vagas</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .filter-container {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1>Minhas Vagas</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Descrição</th>
            <th>Remuneração</th>
            <th>Data Limite</th>
            <th>Cidade</th>
            <th>Ações</th> <!-- Nova coluna -->
        </tr>
        <c:forEach var="vaga" items="${listaVagas}">
            <tr>
                <td>${vaga.id}</td>
                <td>${vaga.descricao}</td>
                <td>${vaga.remuneracao}</td>
                <td>${vaga.dataLimiteInscricao}</td>
                <td>${vaga.cidade}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/vagas/edit?id=${vaga.id}">Editar</a>
                    <a href="${pageContext.request.contextPath}/vagas/delete?id=${vaga.id}" onclick="return confirm('Tem certeza?')">Deletar</a>
                    <a href="${pageContext.request.contextPath}/vagas/candidatos?idVaga=${vaga.id}">Candidatos</a> <!-- Link para ver candidatos -->
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/Logado/Empresas/index.jsp">Voltar</a>
</body>
</html>
