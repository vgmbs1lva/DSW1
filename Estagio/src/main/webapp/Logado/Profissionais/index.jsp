<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.ufscar.dc.dsw.domain.Profissional" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.professionalHome" /></title>
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
        .container p {
            margin-bottom: 20px;
            color: #333;
        }
        .container a {
            display: block;
            color: #fda085;
            text-decoration: none;
            margin: 10px 0;
            font-size: 16px;
        }
        .container a:hover {
            text-decoration: underline;
        }
        .container button {
            background: #fda085;
            border: none;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin: 10px 0;
            font-size: 16px;
        }
        .container button:hover {
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
<%
    HttpSession sessao = request.getSession();
    Profissional profissionalLogado = (Profissional) sessao.getAttribute("profissionalLogado");
    if (profissionalLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<div class="container">
    <h1><fmt:message key="welcome.message" /> <%= profissionalLogado.getNome() %></h1>
    <p><fmt:message key="manage.information" /></p>
    <a href="<%= request.getContextPath() %>/listarVagas"><fmt:message key="view.available.jobs" /></a>
    <a href="<%= request.getContextPath() %>/profissionais/candidaturas"><fmt:message key="my.applications" /></a>
    <a href="${pageContext.request.contextPath}/logout">
        <button type="button"><fmt:message key="button.logout" /></button>
    </a>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</div>
</body>
</html>
