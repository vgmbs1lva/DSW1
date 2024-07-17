<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${profissional != null}">
				<fmt:message key="profissionais.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="profissionais.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${profissional != null}">
		<input type="hidden" name="id" value="${profissional.id}" />
	</c:if>
	<tr>
		<td><label for="nome"> <fmt:message key="profissional.name" />
		</label></td>
		<td><input type="text" id="nome" name="nome" size="45" required
			value="${profissional.nome}" /></td>
	</tr>
	<tr>
		<td><label for="email"> <fmt:message key="profissional.email" />
		</label></td>
		<td><input type="email" id="email" name="email" size="45" required
			value="${profissional.email}" /></td>
	</tr>
	<tr>
		<td><label for="senha"> <fmt:message key="profissional.password" />
		</label></td>
		<td><input type="password" id="senha" name="senha" size="45" required
			value="${profissional.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cpf"> <fmt:message key="profissional.cpf" />
		</label></td>
		<td><input type="text" id="cpf" name="cpf" size="20" required
			value="${profissional.cpf}" /></td>
	</tr>
	<tr>
		<td><label for="telefone"> <fmt:message key="profissional.phone" />
		</label></td>
		<td><input type="text" id="telefone" name="telefone" size="20" required
			value="${profissional.telefone}" /></td>
	</tr>
	<tr>
		<td><label for="sexo"> <fmt:message key="profissional.gender" />
		</label></td>
		<td>
            <select name="sexo">
                <option value="M" ${profissional.sexo == 'M' ? 'selected' : ''}>Masculino</option>
                <option value="F" ${profissional.sexo == 'F' ? 'selected' : ''}>Feminino</option>
            </select>
        </td>
	</tr>
	<tr>
		<td><label for="data_nascimento"> <fmt:message key="profissional.birthdate" />
		</label></td>
		<td><input type="date" id="data_nascimento" name="data_nascimento" required
			value="${profissional.dataNascimento}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
</table>
