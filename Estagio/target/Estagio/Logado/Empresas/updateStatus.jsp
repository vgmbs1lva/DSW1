<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.updateStatus" /></title>
</head>
<body>
    <h1><fmt:message key="page.title.updateStatus" /></h1>
    <form action="${pageContext.request.contextPath}/vagas/updateStatus" method="post">
        <input type="hidden" name="idCandidatura" value="${candidatura.id}">
        <input type="hidden" name="idVaga" value="${vaga.id}">
        <label><fmt:message key="label.status" />:</label>
        <select name="status">
            <option value="1" ${candidatura.status.id == 1 ? 'selected' : ''}><fmt:message key="status.open" /></option>
            <option value="2" ${candidatura.status.id == 2 ? 'selected' : ''}><fmt:message key="status.notSelected" /></option>
            <option value="3" ${candidatura.status.id == 3 ? 'selected' : ''}><fmt:message key="status.interview" /></option>
        </select><br>
        <label><fmt:message key="label.interviewLink" />:</label>
        <input type="text" name="entrevistaLink" value="${candidatura.entrevistaLink}"><br>
        <label><fmt:message key="label.interviewDateTime" />:</label>
        <input type="datetime-local" name="entrevistaDataHora" value="${candidatura.entrevistaDataHora}"><br>
        <input type="submit" value="<fmt:message key='label.update' />">
    </form>
    <a href="${pageContext.request.contextPath}/vagas/candidatos?idVaga=${vaga.id}"><fmt:message key="label.back" /></a>
</body>
</html>
