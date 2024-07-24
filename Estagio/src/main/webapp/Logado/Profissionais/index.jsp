<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.ufscar.dc.dsw.domain.Profissional" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Página Inicial do Profissional</title>
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
    <h1>Bem-vindo, <%= profissionalLogado.getNome() %></h1>
    <p>Aqui você pode gerenciar suas informações.</p>
    <a href="<%= request.getContextPath() %>/listarVagas">Ver Vagas Disponíveis</a>
    <a href="<%= request.getContextPath() %>/profissionais/candidaturas">Minhas Candidaturas</a> <!-- Adicionado -->
    <a href="${pageContext.request.contextPath}/logout">
        <button type="button">Sair</button>
    </a>
</body>

</html>
