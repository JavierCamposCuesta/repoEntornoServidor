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

<!-- Este bloque es el contador de visitas -->
	<%!
	private int contador=1;
	%>
	
	<!-- Lo utilizaremos para el mensaje de bienvenida según la hora -->
	<%
	java.util.Calendar ahora = java.util.Calendar.getInstance();
	int hora = ahora.get(java.util.Calendar.HOUR_OF_DAY);
	%>

	<jsp:useBean id="nombreUsuario" class="beans.Usuario"  scope="session"></jsp:useBean>
	<jsp:setProperty name="nombreUsuario" property="nombre" param="nombre"></jsp:setProperty>
	<jsp:setProperty name="nombreUsuario" property="edad" param="edad"></jsp:setProperty>
	<jsp:setProperty name="nombreUsuario" property="passw" param="passw"></jsp:setProperty>
	

<div class="container">
	<div class="contenido">
		<div class="contador">
		<!-- Añadimos el contador -->
			<p>Visitas: <%=contador++ %></p>
		</div>
		<div class="divPrincipalBienvenida">
			<div class="divImgIzquierda">
				<img src="imagenes/usuario.jpg">
			</div>
			<div class="divImgDerecha">
	<!-- 			Modificamos el mensaje de bienvenida segun la hora que sea-->
				<p><% if (hora>20 || hora<6) { %>
						Buenas noches
					<% }
   					else if (hora >=6 && hora <=12) {%>
  						 Buenos días
					<%		}
					else {%>
  						 Buenas tardes
					<%		} %> 
					<!-- Llamados al nombre que introducimos en el formulario para mostrarlo en el mensaje -->
					<jsp:getProperty name="nombreUsuario" property="nombre"/> </p>
					
					
				<a href="addAnuncio.jsp">Publicar anuncio</a>
				<a href="miAnuncio.jsp">Ver anuncio</a>
			</div>
		</div>
		<div class="divImgAbajo">
			<p>Nacido en el año 
			<!-- Nos muestra el año en el que nació, para ello hace uso del getEdad de la clase Usuario.java que se encarga del cálculo -->
			<jsp:getProperty name="nombreUsuario" property="edad"/> </p>
		</div>
	</div>
	
</div>

<!-- Incluimos el footer, el cual lo tenemos en otra página y lo importamos, así reciclamos código -->
     <%@ include file="footer.jsp" %>
	
</body>
</html>