<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.myJobs" /></title>
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
    <h1><fmt:message key="label.myJobs" /></h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th><fmt:message key="label.description" /></th>
            <th><fmt:message key="label.salary" /></th>
            <th><fmt:message key="label.deadline" /></th>
            <th><fmt:message key="label.city" /></th>
            <th><fmt:message key="label.actions" /></th>
        </tr>
        <c:forEach var="vaga" items="${listaVagas}">
            <tr>
                <td>${vaga.id}</td>
                <td>${vaga.descricao}</td>
                <td>${vaga.remuneracao}</td>
                <td>${vaga.dataLimiteInscricao}</td>
                <td>${vaga.cidade}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/vagas/edit?id=${vaga.id}"><fmt:message key="label.edit" /></a>
                    <a href="${pageContext.request.contextPath}/vagas/delete?id=${vaga.id}" onclick="return confirm('<fmt:message key="message.confirm" />')"><fmt:message key="label.delete" /></a>
                    <a href="${pageContext.request.contextPath}/vagas/candidatos?idVaga=${vaga.id}"><fmt:message key="label.viewCandidates" /></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/Logado/Empresas/index.jsp"><fmt:message key="label.back" /></a>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</body>
</html>
