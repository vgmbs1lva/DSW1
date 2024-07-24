<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.jobFields" /></title>
</head>
<body>
<form action="<c:choose>
            <c:when test="${not empty Vaga}">update</c:when>
            <c:otherwise>insert</c:otherwise>
        </c:choose>" method="post">
    <c:if test="${not empty Vaga}">
        <input type="hidden" name="id" value="${Vaga.id}">
    </c:if>
    <label><fmt:message key="label.description" />:</label>
    <input type="text" name="descricao" value="${Vaga.descricao != null ? Vaga.descricao : ''}" required><br>
    <label><fmt:message key="label.salary" />:</label>
    <input type="text" name="remuneracao" value="${Vaga.remuneracao != null ? Vaga.remuneracao : ''}" required><br>
    <label><fmt:message key="label.deadline" />:</label>
    <input type="date" name="dataLimiteInscricao" value="${Vaga.dataLimiteInscricao != null ? Vaga.dataLimiteInscricao : ''}" required><br>
    <input type="submit" value="<fmt:message key='label.save' />">
</form>
</body>
</html>
