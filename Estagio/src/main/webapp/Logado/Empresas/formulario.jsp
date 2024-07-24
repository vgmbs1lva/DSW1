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
    <title><fmt:message key="page.title.companyForm" /></title>
</head>
<body>
    <h1><fmt:message key="${empresa.id == null ? 'label.addCompany' : 'label.editCompany'}" /></h1>
    <form action="${pageContext.request.contextPath}/empresas/${empresa.id == null ? 'insert' : 'update'}" method="post">
        <input type="hidden" name="id" value="${empresa.id}">
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
        <input type="submit" value="<fmt:message key='label.save' />">
    </form>
    <a href="${pageContext.request.contextPath}/empresas/list"><fmt:message key="label.backToCompanyList" /></a>
    
</body>
</html>
