<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${vaga != null}">
                <fmt:message key="vagas.update" />
            </c:when>
            <c:otherwise>
                <fmt:message key="vagas.create" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${vaga != null}">
        <input type="hidden" name="id" value="${vaga.id}" />
    </c:if>
    <tr>
        <td><label for="cnpj">CNPJ:</label></td>
        <td><input type="text" id="cnpj" name="cnpj" size="45" required value="${vaga.cnpj}" /></td>
    </tr>
    <tr>
        <td><label for="descricao">Descrição:</label></td>
        <td><textarea id="descricao" name="descricao" rows="4" cols="50" required>${vaga.descricao}</textarea></td>
    </tr>
    <tr>
        <td><label for="remuneracao">Remuneração:</label></td>
        <td><input type="number" id="remuneracao" name="remuneracao" step="0.01" required value="${vaga.remuneracao}" /></td>
    </tr>
    <tr>
        <td><label for="data_limite_inscricao">Data Limite de Inscrição:</label></td>
        <td><input type="date" id="data_limite_inscricao" name="data_limite_inscricao" required value="${vaga.dataLimiteInscricao}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
    </tr>
</table>
