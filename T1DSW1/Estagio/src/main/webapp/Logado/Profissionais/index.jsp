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
  Profissional profissionalLogado = (Profissional) sessao.getAttribute("usuarioLogado");
%>
<h1>Bem-vindo, <%= profissionalLogado.getNome() %></h1>
<p>Aqui você pode gerenciar suas informações.</p>
<a href="formularios.jsp">Atualizar Informações</a>
<a href="lista.jsp">Ver Profissionais</a>
</body>
</html>
