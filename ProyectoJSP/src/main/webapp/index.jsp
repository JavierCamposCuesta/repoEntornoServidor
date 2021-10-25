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

 
<!-- Nos traemos el nombre de usuario almacenado en el beans -->
<jsp:useBean id="nombreUsuario" class="beans.Usuario"  scope="session"></jsp:useBean>




<div class="container">

	<!-- Si la sesión es nueva nos mostrará un mensaje distinto a si no lo es -->

	<% if(session.isNew()){ %>
		<h1 class="tituloIndex">Bienvenido a SpeedCar, registrate para continuar</h1>
	<% } else{ %>
	<!-- Cuando la sesión no es nueva hacemos uso del nombre que hay introducido para utilizarlo en el mensaje -->
		<h1 class="tituloIndex">Que tal
		<jsp:getProperty name="nombreUsuario" property="nombre"/>, introduce un nuevo usuario</h1><% } %> 
		
	<form id="form" method="post">
       <div class="divForm">
           <label>Indique su nombre</label>
           <input type="text" id="nombre" name="nombre">
       </div>
       <div class="divForm">
           <label>Indique su edad</label>
           <input type="number" id="edad" name="edad">
       </div>
       <div class="divForm">
           <label>Indique su contraseña</label>
           <input type="password" id="passw" name="passw">
       </div>
       <div class="botonInicio" >
           <input type="submit" id="boton" name="boton" value="Registrarse-Iniciar Sesión">
       </div>
     </form>


<!-- El nombre será necesario para registrarse -->
<% if(request.getMethod().equals("POST")){ 
	
	/* Si el nombre es nulo haremos que la sessión sea nula, por lo tanto contará como que no se ha entrado nunca y el mensaje que saltará será el correcto,
	de esta forma nos evitamos que si el usuario es nulo, el mensaje sea "null que tal" */
	if(request.getParameter("nombre").length()==0){
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		
		/* Si es nulo mostraremos el mensaje en color rojo */
%>
	<p style="text-align: center; color: red">El nombre es incorrecto</p>
	
	<!-- En el caso de que no sea nulo crearemos una cookie para guardar el valor del nombre y nos vamos a la pagina de bienvenida.jsp -->
<% 	}else{ 
	
	/* Creamos y guardamos el nombre en una cookie */
		String nombre = request.getParameter("nombre");
		Cookie cookie = new Cookie("cookie1",nombre);
		response.addCookie(cookie);
		
%> 
	<!-- De esta forma hacemos el cambio de pagina -->
	<jsp:forward page="bienvenida.jsp"/>
	
<%
	}
 } 
%>

</div>

<!-- Incluimos el footer, el cual lo tenemos en otra página y lo importamos, así reciclamos código -->
     <%@ include file="footer.jsp" %>
	
</body>
</html>