<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Editar Empresa</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
            font-family: 'Arial', sans-serif;
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .container label {
            display: block;
            margin: 10px 0 5px;
            text-align: left;
            color: #333;
        }
        .container input[type="text"],
        .container input[type="email"],
        .container input[type="password"],
        .container textarea {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .container input[type="submit"] {
            background: #fda085;
            border: none;
            color: white;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin: 10px 0;
            font-size: 16px;
        }
        .container input[type="submit"]:hover {
            background: #f6d365;
        }
        .container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin-top: 20px;
        }
        .container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Editar Empresa</h1>
    <form action="${pageContext.request.contextPath}/empresas/update" method="post">
        <input type="hidden" name="id" value="${empresa.id}">
        <label>Nome:</label>
        <input type="text" name="nome" value="${empresa.nome}" required><br>
        <label>Email:</label>
        <input type="email" name="email" value="${empresa.email}" required><br>
        <label>Senha:</label>
        <input type="password" name="senha" value="${empresa.senha}" required><br>
        <label>CNPJ:</label>
        <input type="text" name="cnpj" value="${empresa.cnpj}" required><br>
        <label>Descrição:</label>
        <textarea name="descricao" required>${empresa.descricao}</textarea><br>
        <label>Cidade:</label>
        <input type="text" name="cidade" value="${empresa.cidade}" required><br>
        <input type="submit" value="Salvar">
    </form>
    <a href="${pageContext.request.contextPath}/empresas/list">Voltar para Lista de Empresas</a>
</div>
</body>
</html>
