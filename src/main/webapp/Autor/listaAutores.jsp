<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.poo2.beans.Autor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js">
	
</script>

<script>

	function eliminar(id) {
		if (confirm("¿Desea eliminar el registro?") == true) {
			location.href = "AutorController?op=eliminar&id=" + id;
		} else {

		}
	}
</script>

</head>
<body>

	<%@ include file='/MenuNavegacion.jsp'%>

	<br>
	<div class="container">
	
		<a type="button" href="<%=url%>AutorController?op=nuevo" class="btn btn-primary">Nuevo autor</a>
		<table class="table">
			<thead>
				<tr>
					<th>Cod Autor</th>
					<th>Nombre Autor</th>
					<th>Nacionalidad</th>
					<th>Operaciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores");

				if (listaAutores != null) {
					for (Autor autor : listaAutores) {
				%>
				<tr>
					<td><%=autor.getIdAutor()%></td>
					<td><%=autor.getNombre()%></td>
					<td><%=autor.getNacionalidad()%></td>
					<td>
						<a href="<%=url%>AutorController?op=obtener&id=<%=autor.getIdAutor()%>" class="btn btn-warning">Modificar</a>
						<a href="javascript:eliminar('<%=autor.getIdAutor()%>')" class="btn btn-danger">Eliminar</a>
					</td>
				</tr>
				<%
					}
				} else {
				%>
				<tr>
					<td colspan="4" class="text-center">No hay datos</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>

</html>