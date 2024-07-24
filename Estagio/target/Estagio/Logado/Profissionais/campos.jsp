<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.professionalFields" /></title>
</head>
<body>
<form action="<c:choose>
            <c:when test="${not empty profissional}">update</c:when>
            <c:otherwise>insert</c:otherwise>
        </c:choose>" method="post">
    <c:if test="${not empty profissional}">
        <input type="hidden" name="id" value="${profissional.id}">
    </c:if>
    <label><fmt:message key="label.name" />:</label>
    <input type="text" name="nome" value="${profissional.nome != null ? profissional.nome : ''}" required><br>
    <label><fmt:message key="label.cpf" />:</label>
    <input type="text" name="cpf" value="${profissional.cpf != null ? profissional.cpf : ''}" required><br>
    <label><fmt:message key="label.phone" />:</label>
    <input type="text" name="telefone" value="${profissional.telefone != null ? profissional.telefone : ''}"><br>
    <label><fmt:message key="label.gender" />:</label>
    <input type="text" name="sexo" value="${profissional.sexo != null ? profissional.sexo : ''}"><br>
    <label><fmt:message key="label.dateOfBirth" />:</label>
    <input type="date" name="dataNascimento" value="${profissional.dataNascimento != null ? profissional.dataNascimento : ''}" required><br>
    <input type="submit" value="<fmt:message key='label.save' />">
</form>
</body>
</html>
