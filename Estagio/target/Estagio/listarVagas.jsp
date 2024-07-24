<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.domain.Vaga" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.jobListing" /></title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
            font-family: 'Arial', sans-serif;
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
            text-align: center;
        }
        .container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .filter-container {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .filter-container label {
            margin-right: 10px;
            font-size: 16px;
        }
        .filter-container input[type="text"] {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .filter-container button {
            background: #fda085;
            border: none;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .filter-container button:hover {
            background: #f6d365;
        }
        .error-message {
            color: red;
            margin-bottom: 20px;
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
        a {
            color: #fda085;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .language-switcher {
            margin-top: 20px;
        }
        .language-switcher a {
            margin: 0 5px;
            text-decoration: none;
            color: #333;
        }
        .language-switcher a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><fmt:message key="page.title.jobListing" /></h1>
    <div class="filter-container">
        <form action="listarVagas" method="get">
            <label for="cidade"><fmt:message key="label.filterByCity" />:</label>
            <input type="text" id="cidade" name="cidade">
            <button type="submit"><fmt:message key="label.filter" /></button>
        </form>
    </div>
    <c:if test="${not empty param.error}">
        <div class="error-message">
            <c:choose>
                <c:when test="${param.error == '1'}"><fmt:message key="error.emptyResume" /></c:when>
                <c:when test="${param.error == '2'}"><fmt:message key="error.alreadyApplied" /></c:when>
            </c:choose>
        </div>
    </c:if>
    <table>
        <tr>
            <th>ID</th>
            <th><fmt:message key="label.company" /></th>
            <th><fmt:message key="label.description" /></th>
            <th><fmt:message key="label.salary" /></th>
            <th><fmt:message key="label.deadline" /></th>
            <th><fmt:message key="label.city" /></th>
            <th><fmt:message key="label.actions" /></th>
        </tr>
        <c:forEach var="vaga" items="${listaVagas}">
            <tr>
                <td>${vaga.id}</td>
                <td>${vaga.empresa != null ? vaga.empresa.nome : "N/A"}</td>
                <td>${vaga.descricao}</td>
                <td>${vaga.remuneracao}</td>
                <td>${vaga.dataLimiteInscricao}</td>
                <td>${vaga.cidade}</td>
                <td><a href="${pageContext.request.contextPath}/candidatarVaga?id=${vaga.id}"><fmt:message key="label.apply" /></a></td>
            </tr>
        </c:forEach>
    </table>
    <c:choose>
        <c:when test="${not empty sessionScope.profissionalLogado}">
            <a href="${pageContext.request.contextPath}/Logado/Profissionais/index.jsp"><fmt:message key="label.back" /></a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="label.back" /></a>
        </c:otherwise>
    </c:choose>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</div>
</body>
</html>
