<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.vacancyManagement" /></title>
</head>
<body>
    <h1><fmt:message key="label.vacancyManagement" /></h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/vagas/list"><fmt:message key="label.listAllVacancies" /></a></li>
        <li><a href="${pageContext.request.contextPath}/vagas/new"><fmt:message key="label.addNewVacancy" /></a></li>
    </ul>
    <div class="language-switcher">
        <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
        <a href="?lang=en"><fmt:message key="label.english" /></a>
    </div>
</body>
</html>
