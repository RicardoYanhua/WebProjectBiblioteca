<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nuevo Autor</title>

	<link rel="stylesheet" href="assets/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	<script src="assets/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script>
		function validarFormulario() {
			const nombre = document.getElementById('nombre').value.trim();
			const nacionalidad = document.getElementById('nacionalidad').value.trim();

			if (nombre === '') {
				alert('Ingrese el nombre del autor');
				document.getElementById('nombre').focus();
				return false;
			}
			return true;
		}
	</script>
</head>
<body>

	<%@ include file='/MenuNavegacion.jsp'%>

	<% 
	if (request.getAttribute("respuesta") != null) {
		boolean res = (boolean) request.getAttribute("respuesta");
		if (res) {
			List<String> listaError = (List<String>) request.getAttribute("listaError");
			for (String error : listaError) {
	%>
	<div class="alert alert-danger"><%= error %></div>
	<% 
			}
		}
	}
	%>

	<div class="container">
		<h3>Nuevo Autor</h3>
		<form role="form" action="<%=url%>AutorController" method="POST" onsubmit="return validarFormulario()">
			<input type="hidden" name="op" value="insertar" />
			
			<div class="form-group">
				<label for="nombre">Nombre del Autor</label>
				<input class="form-control" placeholder="Ingrese el nombre del autor" type="text" name="nombre" id="nombre" />
			</div>

			<div class="form-group">
				<label for="nacionalidad">Nacionalidad</label>
				<input type="text" class="form-control" name="nacionalidad" id="nacionalidad" placeholder="Ingrese la nacionalidad" />
			</div>

			<br>
			<button type="submit" class="btn btn-primary">Guardar</button>
			<a href="<%=url%>AutorController?op=listar" class="btn btn-secondary">Retornar</a>
		</form>
	</div>

</body>
</html>
