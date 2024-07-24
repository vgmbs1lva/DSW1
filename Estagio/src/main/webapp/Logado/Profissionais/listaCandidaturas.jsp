<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.myApplications" /></title>
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
    </style>
</head>
<body>
    <h1><fmt:message key="page.title.myApplications" /></h1>
    <table border="1">
        <tr>
            <th><fmt:message key="label.id" /></th>
            <th><fmt:message key="label.job" /></th>
            <th><fmt:message key="label.curriculum" /></th>
            <th><fmt:message key="label.status" /></th>
        </tr>
        <c:forEach var="candidatura" items="${listaCandidaturas}">
            <tr>
                <td>${candidatura.id}</td>
                <td>${candidatura.vaga.descricao}</td>
                <td>${candidatura.curriculo}</td>
                <td>${candidatura.status.descricao}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/Logado/Profissionais/index.jsp"><fmt:message key="label.back" /></a>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</body>
</html>
