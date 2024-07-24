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
    <h1><fmt:message key="welcome.message" /> <%= profissionalLogado.getNome() %></h1>
    <p><fmt:message key="manage.information" /></p>
    <a href="<%= request.getContextPath() %>/listarVagas"><fmt:message key="view.available.jobs" /></a>
    <a href="<%= request.getContextPath() %>/profissionais/candidaturas"><fmt:message key="my.applications" /></a> <!-- Adicionado -->
    <a href="${pageContext.request.contextPath}/logout">
        <button type="button"><fmt:message key="button.logout" /></button>
    </a>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</body>
</html>
