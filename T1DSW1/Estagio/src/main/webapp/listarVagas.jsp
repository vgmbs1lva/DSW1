<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufscar.dc.dsw.domain.Vaga" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Listagem de Vagas</title>
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
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Listagem de Vagas</h1>
    <div class="filter-container">
        <form action="listarVagas" method="get">
            <label for="cidade">Filtrar por cidade:</label>
            <input type="text" id="cidade" name="cidade">
            <button type="submit">Filtrar</button>
        </form>
    </div>
    <c:if test="${not empty param.error}">
        <div class="error-message">
            <c:choose>
                <c:when test="${param.error == '1'}">O currículo não pode estar vazio.</c:when>
                <c:when test="${param.error == '2'}">Você já se candidatou a esta vaga.</c:when>
            </c:choose>
        </div>
    </c:if>
    <table>
        <tr>
            <th>ID</th>
            <th>Empresa</th>
            <th>Descrição</th>
            <th>Remuneração</th>
            <th>Data Limite</th>
            <th>Cidade</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="vaga" items="${listaVagas}">
            <tr>
                <td>${vaga.id}</td>
                <td>${vaga.empresa != null ? vaga.empresa.nome : "N/A"}</td>
                <td>${vaga.descricao}</td>
                <td>${vaga.remuneracao}</td>
                <td>${vaga.dataLimiteInscricao}</td>
                <td>${vaga.cidade}</td>
                <td><a href="${pageContext.request.contextPath}/candidatarVaga?id=${vaga.id}">Candidatar-se</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:choose>
        <c:when test="${not empty sessionScope.profissionalLogado}">
            <a href="${pageContext.request.contextPath}/Logado/Profissionais/index.jsp">Voltar</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/login.jsp">Voltar</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
