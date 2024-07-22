<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label>Nome:</label>
<input type="text" name="nome" value="${empresa.nome}" required><br>
<label>Email:</label>
<input type="email" name="email" value="${empresa.email}" required><br>
<label>Senha:</label>
<input type="password" name="senha" value="${empresa.senha}" required><br>
<label>CNPJ:</label>
<input type="text" name="cnpj" value="${empresa.cnpj}" required><br>
<label>Descrição:</label>
<textarea name="descricao" required>${empresa.descricao}</textarea><br>
<label>Cidade:</label>
<input type="text" name="cidade" value="${empresa.cidade}" required><br>
