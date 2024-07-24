<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="page.title.candidatesList" /></title>
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
<h1><fmt:message key="page.title.candidatesList" /></h1>
<table border="1">
    <tr>
        <th><fmt:message key="label.name" /></th>
        <th><fmt:message key="label.email" /></th>
        <th><fmt:message key="label.curriculum" /></th>
        <th><fmt:message key="label.applicationDate" /></th>
        <th><fmt:message key="label.status" /></th>
        <th><fmt:message key="label.actions" /></th>
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
                    <input type="hidden" name="idVaga" value="${idVaga}">
                    <select name="status">
                        <option value="1">ABERTO</option>
                        <option value="2">NÃO SELECIONADO</option>
                        <option value="3">ENTREVISTA</option>
                    </select>
                    <br>
                    <label><fmt:message key="label.interviewLink" /></label>
                    <input type="text" name="entrevistaLink" value="${candidatura.entrevistaLink != null ? candidatura.entrevistaLink : ''}">
                    <br>
                    <label><fmt:message key="label.interviewDateTime" /></label>
                    <input type="datetime-local" name="entrevistaDataHora" value="${candidatura.entrevistaDataHora != null ? candidatura.entrevistaDataHora : ''}">
                    <br>
                    <input type="submit" value="<fmt:message key='label.updateStatus' />">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/vagas/listByEmpresa"><fmt:message key="label.backToJobList" /></a>

</body>
</html>
