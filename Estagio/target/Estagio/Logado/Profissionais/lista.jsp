<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.professionalList" /></title>
</head>
<body>
<h1><fmt:message key="page.title.professionalList" /></h1>
<table border="1">
    <tr>
        <th><fmt:message key="label.id" /></th>
        <th><fmt:message key="label.name" /></th>
        <th><fmt:message key="label.cpf" /></th>
        <th><fmt:message key="label.phone" /></th>
        <th><fmt:message key="label.gender" /></th>
        <th><fmt:message key="label.dateOfBirth" /></th>
        <th><fmt:message key="label.actions" /></th>
    </tr>
    <c:forEach var="profissional" items="${listaProfissionais}">
        <tr>
            <td>${profissional.id}</td>
            <td>${profissional.nome}</td>
            <td>${profissional.cpf}</td>
            <td>${profissional.telefone}</td>
            <td>${profissional.sexo}</td>
            <td>${profissional.dataNascimento}</td>
            <td>
                <a href="edit?id=${profissional.id}"><fmt:message key="label.edit" /></a>
                <a href="delete?id=${profissional.id}" onclick="return confirm('<fmt:message key="label.confirmDelete" />')"><fmt:message key="label.delete" /></a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="new"><fmt:message key="label.addNewProfessional" /></a>
<a href="${pageContext.request.contextPath}/Logado/Admin/index.jsp"><fmt:message key="label.back" /></a>
<div class="language-switcher">
    <a href="?lang=pt_BR"><fmt:message key="label.portuguese" /></a>
    <a href="?lang=en"><fmt:message key="label.english" /></a>
</div>
</body>
</html>
