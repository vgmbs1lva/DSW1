<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.ufscar.dc.dsw.domain.Empresa" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Página Inicial da Empresa</title>
</head>
<body>
<%
    HttpSession sessao = request.getSession();
    Empresa empresaLogada = (Empresa) sessao.getAttribute("empresaLogada");
%>
<h1>Bem-vindo, <%= empresaLogada.getNome() %></h1>
<p>Aqui você pode gerenciar suas informações e vagas.</p>
<a href="../Vagas/formularios.jsp">Criar Nova Vaga</a>
<a href="../Vagas/lista.jsp">Ver Vagas Existentes</a>
</body>
</html>
