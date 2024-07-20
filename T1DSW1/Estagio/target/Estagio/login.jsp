<%@ page import="br.ufscar.dc.dsw.util.Erro" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Login</title>
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
        .login-container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .login-container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .login-container input[type="email"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .login-container button {
            background: #fda085;
            border: none;
            color: white;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin: 10px 0;
            font-size: 16px;
        }
        .login-container button:hover {
            background: #f6d365;
        }
        .login-container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin-top: 10px;
        }
        .login-container a:hover {
            text-decoration: underline;
        }
        .login-container .error-messages {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>Login</h1>
    <form action="login" method="post">
        <input type="email" name="login" placeholder="E-mail" required>
        <input type="password" name="senha" placeholder="Senha" required>
        <button type="submit">Entrar</button>
    </form>
    <a href="register.jsp">Não possui conta? Registre-se</a>
    <div class="error-messages">
        <%
            Erro erros = (Erro) request.getAttribute("mensagens");
            if (erros != null) {
                for (String mensagem : erros.getErros()) {
                    out.println("<p>" + mensagem + "</p>");
                }
            }
        %>
    </div>
</div>
</body>
</html>
