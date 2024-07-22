<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Atualizar Status da Candidatura</title>
</head>
<body>
    <h1>Atualizar Status da Candidatura</h1>
    <form action="${pageContext.request.contextPath}/vagas/updateStatus" method="post">
        <input type="hidden" name="idCandidatura" value="${candidatura.id}">
        <input type="hidden" name="idVaga" value="${vaga.id}">
        <label>Status:</label>
        <select name="status">
            <option value="1" ${candidatura.status.id == 1 ? 'selected' : ''}>Aberto</option>
            <option value="2" ${candidatura.status.id == 2 ? 'selected' : ''}>Não Selecionado</option>
            <option value="3" ${candidatura.status.id == 3 ? 'selected' : ''}>Entrevista</option>
        </select><br>
        <label>Link da Entrevista (se aplicável):</label>
        <input type="text" name="entrevistaLink" value="${candidatura.entrevistaLink}"><br>
        <label>Data e Hora da Entrevista (se aplicável):</label>
        <input type="datetime-local" name="entrevistaDataHora" value="${candidatura.entrevistaDataHora}"><br>
        <input type="submit" value="Atualizar">
    </form>
    <a href="${pageContext.request.contextPath}/vagas/candidatos?idVaga=${vaga.id}">Voltar</a>
</body>
</html>
