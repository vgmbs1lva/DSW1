<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.companyFields" /></title>
</head>
<body>
<label><fmt:message key="label.name" />:</label>
<input type="text" name="nome" value="${empresa.nome}" required><br>
<label><fmt:message key="label.email" />:</label>
<input type="email" name="email" value="${empresa.email}" required><br>
<label><fmt:message key="label.password" />:</label>
<input type="password" name="senha" value="${empresa.senha}" required><br>
<label><fmt:message key="label.cnpj" />:</label>
<input type="text" name="cnpj" value="${empresa.cnpj}" required><br>
<label><fmt:message key="label.description" />:</label>
<textarea name="descricao" required>${empresa.descricao}</textarea><br>
<label><fmt:message key="label.city" />:</label>
<input type="text" name="cidade" value="${empresa.cidade}" required><br>
</body>
</html>
