<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="Styles/style.css" media="screen"></link>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Iniciar Sesión-Registro</title>
<!-- Incluimos el header, el cual lo tenemos en otra página y lo importamos, así reciclamos código -->
<%@ include file="header.jsp" %>
</head>
<body>

<!-- Indicamos la página a la que debe ir en caso de que salte alguna exepcion -->
<%@ page errorPage="error.jsp" %>

<!-- Nos traemos los datos almacenados en el beans de Coche -->
<jsp:useBean id="miAnuncio" class="beans.Coche"  scope="session"></jsp:useBean>
	<jsp:setProperty name="miAnuncio" property="*"></jsp:setProperty>


<div class="container">
	<div class="contenido">
		<div class="divTitulo">
		<!-- Utilizamos los datos que tenemos almacenados en el beans para mostrar los datos -->
			<p class="divTitulo1"><jsp:getProperty name="miAnuncio" property="marca"/> - <jsp:getProperty name="miAnuncio" property="modelo"/></p>
			<p class="divTitulo2"><jsp:getProperty name="miAnuncio" property="precio"/>€ Iva incluido</p>
		</div>
		<div class="divDescripcion">
			<p><jsp:getProperty name="miAnuncio" property="descripcion"/></p>
		</div>
		
		<div class="divYearKm">
			<p class="year">Año: <jsp:getProperty name="miAnuncio" property="year"/></p>
			<p class="km"><jsp:getProperty name="miAnuncio" property="km"/> km</p>
			<p class="combustible"><jsp:getProperty name="miAnuncio" property="combustible"/></p>
		</div>
		<div class="divImgAnuncio">
			<img src="imagenes/coche.jpg">
		</div>
	</div>
	
</div>
<!-- Incluimos el footer, el cual lo tenemos en otra página y lo importamos, así reciclamos código -->
     <%@ include file="footer.jsp" %>
	
</body>
</html>