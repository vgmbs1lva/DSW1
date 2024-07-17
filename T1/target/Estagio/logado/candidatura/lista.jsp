<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Vagas</title>
</head>
<body>
    <h1>Lista de Vagas</h1>
    <a href="vagas/cadastro">Cadastrar Nova Vaga</a>
    <table border="1">
        <tr>
            <th>CNPJ</th>
            <th>Descrição</th>
            <th>Remuneração</th>
            <th>Data Limite de Inscrição</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="vaga" items="${listaVagas}">
            <tr>
                <td>${vaga.cnpj}</td>
                <td>${vaga.descricao}</td>
                <td>${vaga.remuneracao}</td>
                <td>${vaga.dataLimiteInscricao}</td>
                <td>
                    <a href="vagas/edicao?id=${vaga.id}">Editar</a>
                    <a href="vagas/remocao?id=${vaga.id}">Remover</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
