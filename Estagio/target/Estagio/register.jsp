<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="${param.lang != null ? param.lang : 'pt-BR'}">
<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}"/>
<fmt:setBundle basename="message" />
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="page.title.register" /></title>
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
        .register-container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        .register-container h1 {
            margin-bottom: 24px;
            color: #333;
        }
        .register-container input[type="text"],
        .register-container input[type="password"],
        .register-container input[type="email"],
        .register-container input[type="date"] {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .register-container button {
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
        .register-container button:hover {
            background: #f6d365;
        }
        .register-container a {
            color: #fda085;
            text-decoration: none;
            display: block;
            margin-top: 10px;
        }
        .register-container a:hover {
            text-decoration: underline;
        }
        .toggle-buttons {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }
        .toggle-buttons button {
            flex: 1;
            margin: 0 5px;
            background: #ddd;
            border: none;
            color: #333;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .toggle-buttons button.active {
            background: #fda085;
            color: white;
        }
        .radio-container {
            text-align: left;
            margin: 10px 0;
        }
        .radio-container label {
            margin-right: 15px;
            display: inline-block;
        }
        .radio-container input[type="radio"] {
            margin-right: 5px;
        }
        .radio-container .radio-label {
            margin-right: 15px;
        }
        .language-switcher {
            margin-top: 20px;
        }
        .language-switcher a {
            margin: 0 5px;
            text-decoration: none;
            color: #333;
        }
        .language-switcher a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function toggleForm(type) {
            document.getElementById('form-professional').style.display = type === 'professional' ? 'block' : 'none';
            document.getElementById('form-company').style.display = type === 'company' ? 'block' : 'none';
            document.getElementById('btn-professional').classList.toggle('active', type === 'professional');
            document.getElementById('btn-company').classList.toggle('active', type === 'company');
        }

        function setDatePlaceholder() {
            var dateInput = document.getElementById('data_nascimento');
            dateInput.onfocus = function() {
                this.type = 'date';
            };
            dateInput.onblur = function() {
                if (this.value === '') {
                    this.type = 'text';
                    this.placeholder = '<fmt:message key="label.dateOfBirth" />';
                }
            };
            dateInput.type = 'text';
            dateInput.placeholder = '<fmt:message key="label.dateOfBirth" />';
        }

        window.onload = function() {
            setDatePlaceholder();
        };
    </script>
</head>
<body>
<div class="register-container">
    <h1><fmt:message key="label.register" /></h1>
    <div class="toggle-buttons">
        <button id="btn-professional" class="active" onclick="toggleForm('professional')"><fmt:message key="label.professional" /></button>
        <button id="btn-company" onclick="toggleForm('company')"><fmt:message key="label.company" /></button>
    </div>
    <form id="form-professional" action="register" method="post" style="display: block;">
        <input type="hidden" name="tipo" value="profissional">
        <input type="text" name="nome" placeholder="<fmt:message key='label.name' />" required>
        <input type="email" name="email" placeholder="<fmt:message key='label.email' />" required>
        <input type="password" name="senha" placeholder="<fmt:message key='label.password' />" required>
        <input type="text" name="cpf" placeholder="<fmt:message key='label.cpf' />" required>
        <input type="text" name="telefone" placeholder="<fmt:message key='label.phone' />" required>
        <div class="radio-container">
            <label class="radio-label"><fmt:message key="label.gender" />:</label>
            <label>
                <input type="radio" name="sexo" value="M" required> <fmt:message key="label.male" />
            </label>
            <label>
                <input type="radio" name="sexo" value="F" required> <fmt:message key="label.female" />
            </label>
            <label>
                <input type="radio" name="sexo" value="Outro" required> <fmt:message key="label.other" />
            </label>
        </div>
        <input type="date" name="data_nascimento" id="data_nascimento" required>
        <button type="submit"><fmt:message key="label.register" /></button>
    </form>

    <form id="form-company" action="register" method="post" style="display: none;">
        <input type="hidden" name="tipo" value="empresa">
        <input type="text" name="nome" placeholder="<fmt:message key='label.companyName' />" required>
        <input type="email" name="email" placeholder="<fmt:message key='label.email' />" required>
        <input type="password" name="senha" placeholder="<fmt:message key='label.password' />" required>
        <input type="text" name="cnpj" placeholder="<fmt:message key='label.cnpj' />" required>
        <input type="text" name="descricao" placeholder="<fmt:message key='label.description' />" required>
        <input type="text" name="cidade" placeholder="<fmt:message key='label.city' />" required>
        <button type="submit"><fmt:message key="label.register" /></button>
    </form>
    <a href="login.jsp"><fmt:message key="label.alreadyRegistered" /></a>
    <div class="language-switcher">
        <a href="?lang=pt_BR">Português</a>
        <a href="?lang=en">English</a>
    </div>
</div>
</body>
</html>
