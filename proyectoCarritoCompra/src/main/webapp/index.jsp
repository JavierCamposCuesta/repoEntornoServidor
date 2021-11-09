<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link type="image/x-icon" href="imagenes/logoFacebook.svg" rel="icon" /> 
	<title>Iniciar sesión en Facebook</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div id="general">
		<h1>La Tienda de Pepe</h1>
		<div id="titulo">
			<div id="titulo"><img src="imagenes/logoSinFondo.png">
			<p>En <b>La Tienda de Pepe</b> vas encontrarar todo lo necesario para tu zona Gaming.</p></div>

		</div>
		<div>
			<div id="cuadro">
				<form action="/proyectoCarritoCompra/Inicio" method="post" name="Acceso">
					<input type="text" name="nombre" placeholder="Nombre de usuario">
					<input type="password" name="pass" placeholder="Contraseña">
					<input type="submit" name="Entrar" value="Entrar" class="entrar">
				</form>
			<a href="#" class="olvidoPass"><p>¿Has olvidado la contraseña?</p></a>
			<%if(session.getAttribute("datosIncorrectos")=="true"){
				%> <p class="datosErroneos">Datos incorrectos, vuelve a intentarlo</p>
				<% 
			} 
			%>
			
			 
			<div id="botonFalso"><p>Crear nueva cuenta</p></div>
	
	
			</div>
		
	</div>

		
	</div>
	<div id="debajoDelCuadro"><p><b>Crea una cuenta</b> y podrás subscribirte a nuestras exclusivas ofertas</p></div>
	

</body>
</html>