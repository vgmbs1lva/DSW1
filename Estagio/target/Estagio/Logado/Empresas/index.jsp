<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.domain.Empresa" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessao = request.getSession();
    Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");
%>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.companyHome" /></title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
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
    <h1><fmt:message key="label.welcomeCompany" />, <%= empresaLogada.getNome() %></h1>
    <p><fmt:message key="label.manageInfoAndJobs" /></p>
    <a href="../Vagas/formulario.jsp"><fmt:message key="label.createNewJob" /></a>
    <a href="${pageContext.request.contextPath}/vagas/listByEmpresa"><fmt:message key="label.myJobs" /></a>
    <a href="${pageContext.request.contextPath}/logout">
        <button type="button"><fmt:message key="label.logout" /></button>
    </a>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</body>
</html>
