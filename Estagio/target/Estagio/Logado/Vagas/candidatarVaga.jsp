<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Candidatar-se à Vaga</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
            font-family: 'Arial', sans-serif;
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }
        .container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .container label {
            display: block;
            font-weight: bold;
            margin-top: 20px;
        }
        .container p {
            margin-top: 5px;
            color: #555;
        }
        .container textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            resize: vertical;
        }
        .container input[type="submit"] {
            background: #fda085;
            border: none;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }
        .container input[type="submit"]:hover {
            background: #f6d365;
        }
        .container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin-top: 20px;
            font-size: 16px;
        }
        .container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Candidatar-se à Vaga</h1>
        <form action="${pageContext.request.contextPath}/candidatarVaga" method="post">
            <input type="hidden" name="id" value="${vaga.id}">
            <label>Descrição da Vaga:</label>
            <p>${vaga.descricao}</p>
            <label>Remuneração:</label>
            <p>${vaga.remuneracao}</p>
            <label>Data Limite:</label>
            <p>${vaga.dataLimiteInscricao}</p>
            <label>Conte-nos mais sobre você:</label>
            <textarea name="curriculo" rows="10" cols="50" required></textarea><br>
            <input type="submit" value="Enviar Candidatura">
        </form>
        <a href="${pageContext.request.contextPath}/listarVagas">Voltar</a>
    </div>
</body>
</html>
