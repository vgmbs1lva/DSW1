<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    HttpSession sessao = request.getSession();
    if (sessao.getAttribute("usuarioLogado") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Página de Administração</title>
</head>
<body>
<h1>Bem-vindo, Administrador</h1>
<ul>
    <li><a href="${pageContext.request.contextPath}/empresas/list">Gerenciar Empresas</a></li>
    <li><a href="${pageContext.request.contextPath}/profissionais/list">Gerenciar Profissionais</a></li>
    <!--<li><a href="${pageContext.request.contextPath}/vagas/list">Gerenciar Vagas</a></li>-->
    <a href="${pageContext.request.contextPath}/login.jsp">
        <button type="button">Voltar para Login</button>
    </a>
</ul>

</body>
</html>
