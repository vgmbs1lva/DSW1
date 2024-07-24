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
    <title><fmt:message key="page.title.listCompanies" /></title>
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
        .actions a {
            color: #fda085;
            text-decoration: none;
            margin: 0 5px;
        }
        .actions a:hover {
            text-decoration: underline;
        }
        .button-container a {
            display: inline-block;
            margin: 20px 5px 0;
            padding: 10px 20px;
            background: #fda085;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
        }
        .button-container a:hover {
            background: #f6d365;
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
    <h1><fmt:message key="label.listCompanies" /></h1>
    <table border="1">
        <tr>
            <th><fmt:message key="label.id" /></th>
            <th><fmt:message key="label.name" /></th>
            <th><fmt:message key="label.email" /></th>
            <th><fmt:message key="label.cnpj" /></th>
            <th><fmt:message key="label.description" /></th>
            <th><fmt:message key="label.city" /></th>
            <th><fmt:message key="label.actions" /></th>
        </tr>
        <c:forEach var="empresa" items="${listaEmpresas}">
            <tr>
                <td>${empresa.id}</td>
                <td>${empresa.nome}</td>
                <td>${empresa.email}</td>
                <td>${empresa.cnpj}</td>
                <td>${empresa.descricao}</td>
                <td>${empresa.cidade}</td>
                <td class="actions">
                    <a href="edit?id=${empresa.id}"><fmt:message key="label.edit" /></a>
                    <a href="delete?id=${empresa.id}" onclick="return confirm('<fmt:message key="label.confirmDelete" />')"><fmt:message key="label.delete" /></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="button-container">
        <a href="new"><fmt:message key="label.addNewCompany" /></a>
        <a href="${pageContext.request.contextPath}/Logado/Admin/index.jsp"><fmt:message key="label.back" /></a>
    </div>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</div>
</body>
</html>
