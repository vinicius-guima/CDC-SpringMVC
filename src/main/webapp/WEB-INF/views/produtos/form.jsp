
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page isELIgnored="false" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Casa do Código</title>
</head>
<body>
	<form:form action="${s:mvcUrl('PC#salva').build()}" method="POST"
		commandName="produto" enctype="multipart/form-data">
		<div>
			<label>Titulo</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="5" cols="20" path="descricao" />
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Páginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data Lançamento</label>
			<form:input path="dataLancamento" />
			<form:errors path="dataLancamento" />
		</div>

		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco }</label>
				<form:input path="precos[${ status.index}].valor" />
				<form:hidden path="precos[${ status.index}].tipo"
					value="${tipoPreco}" />

			</div>
		</c:forEach>
			<div>
			<label>Sumário</label>
			<input type="file" name="sumario" /> 
			<!-- mesmo nome no metodo salva()  --> 
		</div>


		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>