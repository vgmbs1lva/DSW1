<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Empresa</title>
</head>
<body>
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
</body>
</html>
