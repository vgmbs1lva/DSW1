<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Empresas</title>
</head>
<body>
<h1>Lista de Empresas</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>CNPJ</th>
        <th>Descrição</th>
        <th>Cidade</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="empresa" items="${listaEmpresas}">
        <tr>
            <td>${empresa.id}</td>
            <td>${empresa.nome}</td>
            <td>${empresa.email}</td>
            <td>${empresa.cnpj}</td>
            <td>${empresa.descricao}</td>
            <td>${empresa.cidade}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/empresas/edit?id=${empresa.id}">Editar</a>
                <a href="${pageContext.request.contextPath}/admin/empresas/delete?id=${empresa.id}" onclick="return confirm('Tem certeza?')">Deletar</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/Logado/Admin/index.jsp">Voltar</a>
</body>
</html>
