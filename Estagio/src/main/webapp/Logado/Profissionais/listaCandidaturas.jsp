<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Minhas Candidaturas</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Minhas Candidaturas</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Vaga</th>
            <th>Currículo</th>
            <th>Status</th>
            <th>Data de Candidatura</th>
        </tr>
        <c:forEach var="candidatura" items="${listaCandidaturas}">
            <tr>
                <td>${candidatura.id}</td>
                <td>${candidatura.vaga.descricao}</td>
                <td>${candidatura.curriculo}</td>
                <td>${candidatura.status.descricao}</td>
                <td>${candidatura.dataCandidatura}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/Logado/Profissionais/index.jsp">Voltar</a>
</body>
</html>
