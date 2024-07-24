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
    <title><fmt:message key="page.title.admin" /></title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
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
</body>
</html>
