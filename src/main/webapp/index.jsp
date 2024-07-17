<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estágio</title>
</head>
<body>
    <h1>Bem-vindo ao Sistema de Estágios</h1>
    
    <c:choose>
        <c:when test="${not empty usuarioLogado}">
            <p>Olá, ${usuarioLogado.nome}! Você está logado como ${usuarioLogado.papel}.</p>
            <c:choose>
                <c:when test="${usuarioLogado.papel == 'ADMIN'}">
                    <p><a href="${pageContext.request.contextPath}/usuarios/lista">Gerenciar Usuários</a></p>
                    <p><a href="${pageContext.request.contextPath}/empresas/lista">Gerenciar Empresas</a></p>
                    <p><a href="${pageContext.request.contextPath}/profissionais/lista">Gerenciar Profissionais</a></p>
                </c:when>
                <c:when test="${usuarioLogado.papel == 'EMPRESA'}">
                    <p><a href="${pageContext.request.contextPath}/vagas/lista">Gerenciar Vagas</a></p>
                    <p><a href="${pageContext.request.contextPath}/candidaturas/lista">Ver Candidaturas</a></p>
                </c:when>
                <c:when test="${usuarioLogado.papel == 'PROFISSIONAL'}">
                    <p><a href="${pageContext.request.contextPath}/vagas/lista">Ver Vagas</a></p>
                    <p><a href="${pageContext.request.contextPath}/candidaturas/lista">Minhas Candidaturas</a></p>
                </c:when>
            </c:choose>
            <p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>
        </c:when>
        <c:otherwise>
            <p><a href="${pageContext.request.contextPath}/login.jsp">Login</a></p>
        </c:otherwise>
    </c:choose>
</body>
</html>
