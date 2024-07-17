<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Empresas</title>
</head>
<body>
    <h1>Lista de Empresas</h1>
    <a href="empresas/cadastro">Cadastrar Nova Empresa</a>
    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>CNPJ</th>
            <th>Descrição</th>
            <th>Cidade</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="empresa" items="${listaEmpresas}">
            <tr>
                <td>${empresa.nome}</td>
                <td>${empresa.email}</td>
                <td>${empresa.cnpj}</td>
                <td>${empresa.descricao}</td>
                <td>${empresa.cidade}</td>
                <td>
                    <a href="empresas/edicao?id=${empresa.id}">Editar</a>
                    <a href="empresas/remocao?id=${empresa.id}">Remover</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
