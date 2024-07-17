<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Vaga</title>
</head>
<body>
    <h1>Cadastro de Vaga</h1>
    <form action="${vaga != null ? 'atualizacao' : 'insercao'}" method="post">
        <jsp:include page="campos.jsp" />
    </form>
</body>
</html>
