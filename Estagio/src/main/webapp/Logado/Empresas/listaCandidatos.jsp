<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Candidatos</title>
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
        form {
            display: inline;
        }
    </style>
    <script>
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            var mensagemErro = urlParams.get('error');
            if (mensagemErro) {
                alert(mensagemErro);
            }
        };
    </script>
</head>
<body>
<h1>Lista de Candidatos</h1>
<table border="1">
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Currículo</th>
        <th>Data de Candidatura</th>
        <th>Status</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="candidatura" items="${listaCandidaturas}">
        <tr>
            <td>${candidatura.profissional.nome}</td>
            <td>${candidatura.profissional.email}</td>
            <td>${candidatura.curriculo}</td>
            <td>${candidatura.dataCandidatura}</td>
            <td>${candidatura.status.descricao}</td>
            <td>
                
                <form action="${pageContext.request.contextPath}/vagas/updateStatus" method="post">
                    <input type="hidden" name="idCandidatura" value="${candidatura.id}">
                    <input type="hidden" name="idVaga" value="${idVaga}"> <!-- Certifique-se de que este campo está presente -->
                    <select name="status">
                        <option value="1">ABERTO</option>
                        <option value="2">NÃO SELECIONADO</option>
                        <option value="3">ENTREVISTA</option>
                    </select>
                    <br>
                    <label>Link da Entrevista:</label>
                    <input type="text" name="entrevistaLink" value="${candidatura.entrevistaLink != null ? candidatura.entrevistaLink : ''}">
                    <br>
                    <label>Data e Hora da Entrevista:</label>
                    <input type="datetime-local" name="entrevistaDataHora" value="${candidatura.entrevistaDataHora != null ? candidatura.entrevistaDataHora : ''}">
                    <br>
                    <input type="submit" value="Atualizar Status">
                </form>                
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/vagas/listByEmpresa">Voltar</a>
</body>
</html>
