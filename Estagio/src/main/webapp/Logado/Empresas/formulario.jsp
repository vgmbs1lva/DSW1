<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    HttpSession sessao = request.getSession();
    if (sessao.getAttribute("usuarioLogado") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.companyForm" /></title>
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
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .container label {
            display: block;
            margin: 10px 0 5px;
            text-align: left;
            color: #333;
        }
        .container input[type="text"],
        .container input[type="email"],
        .container input[type="password"],
        .container textarea {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .container input[type="submit"] {
            background: #fda085;
            border: none;
            color: white;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin: 10px 0;
            font-size: 16px;
        }
        .container input[type="submit"]:hover {
            background: #f6d365;
        }
        .container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin-top: 20px;
        }
        .container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1><fmt:message key="${empresa.id == null ? 'label.addCompany' : 'label.editCompany'}" /></h1>
    <form action="${pageContext.request.contextPath}/empresas/${empresa.id == null ? 'insert' : 'update'}" method="post">
        <input type="hidden" name="id" value="${empresa.id}">
        <label><fmt:message key="label.name" />:</label>
        <input type="text" name="nome" value="${empresa.nome}" required><br>
        <label><fmt:message key="label.email" />:</label>
        <input type="email" name="email" value="${empresa.email}" required><br>
        <label><fmt:message key="label.password" />:</label>
        <input type="password" name="senha" value="${empresa.senha}" required><br>
        <label><fmt:message key="label.cnpj" />:</label>
        <input type="text" name="cnpj" value="${empresa.cnpj}" required><br>
        <label><fmt:message key="label.description" />:</label>
        <textarea name="descricao" required>${empresa.descricao}</textarea><br>
        <label><fmt:message key="label.city" />:</label>
        <input type="text" name="cidade" value="${empresa.cidade}" required><br>
        <input type="submit" value="<fmt:message key='label.save' />">
    </form>
    <a href="${pageContext.request.contextPath}/empresas/list"><fmt:message key="label.backToCompanyList" /></a>
</div>
</body>
</html>
