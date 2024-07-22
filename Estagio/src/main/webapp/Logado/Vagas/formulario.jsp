<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Vaga</title>
</head>
<body>
    <h1>${vaga.id == null ? 'Adicionar Vaga' : 'Editar Vaga'}</h1>
    <form action="${pageContext.request.contextPath}/vagas/${vaga.id == null ? 'insert' : 'update'}" method="post">
        <input type="hidden" name="id" value="${vaga.id}">
        <label>Descrição:</label>
        <input type="text" name="descricao" value="${vaga.descricao}" required><br>
        <label>Remuneração:</label>
        <input type="text" name="remuneracao" value="${vaga.remuneracao}" required><br>
        <label>Data Limite de Inscrição:</label>
        <input type="date" name="dataLimiteInscricao" value="${vaga.dataLimiteInscricao}" required><br>
        <input type="hidden" name="cidade" value="${vaga.cidade}">
        <input type="submit" value="Salvar">
    </form>
    <a href="${pageContext.request.contextPath}/vagas/Logado/Empresas/">Voltar</a>
</body>
</html>
