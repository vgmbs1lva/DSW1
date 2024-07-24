<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.domain.Vaga" %>
<%@ page import="br.ufscar.dc.dsw.domain.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.jobList" /></title>
</head>
<body>
<%
    HttpSession sessao = request.getSession();
    Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");
    List<Vaga> vagas = (List<Vaga>) request.getAttribute("listaVagas");
%>
<h1><fmt:message key="page.title.jobsFromCompany" /> <%= empresaLogada.getNome() %></h1>
<table border="1">
    <tr>
        <th><fmt:message key="label.id" /></th>
        <th><fmt:message key="label.description" /></th>
        <th><fmt:message key="label.salary" /></th>
        <th><fmt:message key="label.deadline" /></th>
        <th><fmt:message key="label.city" /></th>
        <th><fmt:message key="label.actions" /></th>
    </tr>
    <c:forEach var="vaga" items="${vagas}">
        <tr>
            <td>${vaga.id}</td>
            <td>${vaga.descricao}</td>
            <td>${vaga.remuneracao}</td>
            <td>${vaga.dataLimiteInscricao}</td>
            <td>${vaga.cidade}</td>
            <td>
                <a href="edit?id=${vaga.id}"><fmt:message key="label.edit" /></a>
                <a href="delete?id=${vaga.id}" onclick="return confirm('<fmt:message key="label.confirmDelete" />')"><fmt:message key="label.delete" /></a>
                <a href="${pageContext.request.contextPath}/empresas/candidatos?idVaga=${vaga.id}"><fmt:message key="label.viewCandidates" /></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
