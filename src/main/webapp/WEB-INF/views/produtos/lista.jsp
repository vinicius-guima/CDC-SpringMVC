<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Casa do C�digo</title>
</head>
<body>

	<h1>Lista de Livros</h1>

	 <div>${sucesso}</div>
	 
	<table>
		<tr>
			<th>T�tulo</th>
			<th>Desci��o</th>
			<th>P�ginas</th>
		</tr>

		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.titulo }</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas }</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>