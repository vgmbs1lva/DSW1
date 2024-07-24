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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="page.title.admin" /></title>
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
        .admin-container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .admin-container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .admin-container ul {
            list-style-type: none;
            padding: 0;
        }
        .admin-container li {
            margin: 10px 0;
        }
        .admin-container a {
            color: #fda085;
            text-decoration: none;
        }
        .admin-container a:hover {
            text-decoration: underline;
        }
        .admin-container button {
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
        .admin-container button:hover {
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
<div class="admin-container">
    <h1><fmt:message key="label.welcomeAdmin" /></h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/empresas/list"><fmt:message key="label.manageCompanies" /></a></li>
        <li><a href="${pageContext.request.contextPath}/profissionais/list"><fmt:message key="label.manageProfessionals" /></a></li>
        <!--<li><a href="${pageContext.request.contextPath}/vagas/list"><fmt:message key="label.manageJobs" /></a></li>-->
    </ul>
    <a href="${pageContext.request.contextPath}/logout">
        <button type="button"><fmt:message key="label.logout" /></button>
    </a>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</div>
</body>
</html>
