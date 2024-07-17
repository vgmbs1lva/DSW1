<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Profissional</title>
</head>
<body>
    <h1>Cadastro de Profissional</h1>
    <form action="${profissional != null ? 'atualizacao' : 'insercao'}" method="post">
        <jsp:include page="campos.jsp" />
    </form>
</body>
</html>
