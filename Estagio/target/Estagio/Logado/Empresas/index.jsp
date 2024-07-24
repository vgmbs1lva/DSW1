<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.ufscar.dc.dsw.domain.Empresa" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessao = request.getSession();
    Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");
%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Página Inicial da Empresa</title>
    </head>
    <body>
        <h1>Bem-vindo, <%= empresaLogada.getNome() %></h1>
        <p>Aqui você pode gerenciar suas informações e vagas.</p>
        <a href="../Vagas/formulario.jsp">Criar Nova Vaga</a>
        <a href="${pageContext.request.contextPath}/vagas/listByEmpresa">Minhas Vagas</a>
        <a href="${pageContext.request.contextPath}/logout">
            <button type="button">Sair</button>
        </a>
    </body>
</html>
