<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${empresa != null}">
				<fmt:message key="empresas.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="empresas.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${empresa != null}">
		<input type="hidden" name="id" value="${empresa.id}" />
	</c:if>
	<tr>
		<td><label for="nome">Nome:</label></td>
		<td><input type="text" id="nome" name="nome" size="45" required value="${empresa.nome}" /></td>
	</tr>
	<tr>
		<td><label for="email">Email:</label></td>
		<td><input type="email" id="email" name="email" size="45" required value="${empresa.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha:</label></td>
		<td><input type="password" id="senha" name="senha" size="45" required value="${empresa.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cnpj">CNPJ:</label></td>
		<td><input type="text" id="cnpj" name="cnpj" size="45" required value="${empresa.cnpj}" /></td>
	</tr>
	<tr>
		<td><label for="descricao">Descrição:</label></td>
		<td><textarea id="descricao" name="descricao" rows="4" cols="50" required>${empresa.descricao}</textarea></td>
	</tr>
	<tr>
		<td><label for="cidade">Cidade:</label></td>
		<td><input type="text" id="cidade" name="cidade" size="45" required value="${empresa.cidade}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
	</tr>
</table>
