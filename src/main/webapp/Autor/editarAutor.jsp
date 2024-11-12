<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.unu.poo2.beans.Autor"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modificar Autor</title>
</head>
<body>

	<%@ include file='/MenuNavegacion.jsp'%>

	<%
		Autor autor;
		if (request.getAttribute("autor") == null) {
			autor = new Autor();
		} else {
			autor = (Autor) request.getAttribute("autor");
		}
	%>

	<div class="container">
		<h3>Modificar Autor</h3>
		<form role="form" action="<%=url%>AutorController" method="POST">
			<input type="hidden" name="op" value="modificar" />
			<input type="hidden" name="id" value="<%=autor.getIdAutor()%>" />

			<div class="form-group">
				<label for="nombre">Nombre del Autor</label>
				<input type="text" class="form-control" name="nombre" id="nombre" value="<%=autor.getNombre()%>" placeholder="Ingrese el nombre del autor" />
			</div>

			<div class="form-group">
				<label for="nacionalidad">Nacionalidad</label>
				<input type="text" class="form-control" name="nacionalidad" id="nacionalidad" value="<%=autor.getNacionalidad()%>" placeholder="Ingrese la nacionalidad" />
			</div>

			<br>
			<button type="submit" class="btn btn-success">Guardar</button>
			<a href="<%=url%>AutorController?op=listar" class="btn btn-secondary">Retornar</a>
		</form>
	</div>
	
</body>
</html>
