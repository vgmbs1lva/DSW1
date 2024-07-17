<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Profissionais</title>
</head>
<body>
    <h1>Lista de Profissionais</h1>
    <a href="profissionais/cadastro">Cadastrar Novo Profissional</a>
    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>CPF</th>
            <th>Telefone</th>
            <th>Sexo</th>
            <th>Data de Nascimento</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="profissional" items="${listaProfissionais}">
            <tr>
                <td>${profissional.nome}</td>
                <td>${profissional.email}</td>
                <td>${profissional.cpf}</td>
                <td>${profissional.telefone}</td>
                <td>${profissional.sexo}</td>
                <td>${profissional.dataNascimento}</td>
                <td>
                    <a href="profissionais/edicao?id=${profissional.id}">Editar</a>
                    <a href="profissionais/remocao?id=${profissional.id}">Remover</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
