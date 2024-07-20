<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.ufscar.dc.dsw.domain.Profissional" %>
<%@ page import="java.util.List" %>
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
    <c:forEach var="Profissional" items="${listaProfissionais}">
        <tr>
            <td>${Profissional.id}</td>
            <td>${Profissional.nome}</td>
            <td>${Profissional.cpf}</td>
            <td>${Profissional.telefone}</td>
            <td>${Profissional.sexo}</td>
            <td>${Profissional.dataNascimento}</td>
            <td>
                <a href="edit?id=${Profissional.id}">Editar</a>
                <a href="delete?id=${Profissional.id}" onclick="return confirm('Tem certeza?')">Deletar</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
