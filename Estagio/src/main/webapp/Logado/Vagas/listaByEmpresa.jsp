<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="page.title.myJobs" /></title>
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
        .container table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .container table, th, td {
            border: 1px solid #ddd;
        }
        .container th, td {
            padding: 8px;
            text-align: left;
        }
        .container th {
            background-color: #f2f2f2;
        }
        .container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin: 10px 0;
        }
        .container a:hover {
            text-decoration: underline;
        }
        .container .language-switcher {
            margin-top: 20px;
        }
        .container .language-switcher a {
            margin: 0 5px;
            text-decoration: none;
            color: #333;
        }
        .container .language-switcher a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><fmt:message key="label.myJobs" /></h1>
    <table>
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
</div>
</body>
</html>
