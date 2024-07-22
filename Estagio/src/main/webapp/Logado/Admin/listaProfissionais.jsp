<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Profissionais</title>
</head>
<body>
<h1>Lista de Profissionais</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>CPF</th>
        <th>Telefone</th>
        <th>Sexo</th>
        <th>Data de Nascimento</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="profissional" items="${listaProfissionais}">
        <tr>
            <td>${profissional.id}</td>
            <td>${profissional.nome}</td>
            <td>${profissional.cpf}</td>
            <td>${profissional.telefone}</td>
            <td>${profissional.sexo}</td>
            <td>${profissional.dataNascimento}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/profissionais/edit?id=${profissional.id}">Editar</a>
                <a href="${pageContext.request.contextPath}/admin/profissionais/delete?id=${profissional.id}" onclick="return confirm('Tem certeza?')">Deletar</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/Logado/Admin/index.jsp">Voltar</a>
</body>
</html>
