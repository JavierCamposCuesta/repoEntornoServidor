<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="Styles/style.css" media="screen"></link>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Publicar Anuncio</title>
<!-- Incluimos el header, el cual lo tenemos en otra página y lo importamos, así reciclamos código -->
<%@ include file="header.jsp" %>
</head>
<body>


<div class="container">
	<h1 class="tituloIndex">Publica el anuncio de tu coche</h1>
	<form id="form" action="miAnuncio.jsp" method="POST">
       <div class="divForm">
           <label>Indique la marca</label>
           <input type="text" id="marca" name="marca">
       </div>
        <div class="divForm">
           <label>Indique el modelo</label>
           <input type="text" id="modelo" name="modelo">
       </div>
        <div class="divForm">
           <label>Indique los kilometros</label>
           <input type="number" id="km" name="km">
       </div>
        <div class="divForm">
           <label>Indique el año de fabricacion</label>
           <input type="number" id="year" name="year">
       </div>
        <div class="divForm">
           <label>Indique el combustible</label>
           <select name="combustible">
           		<option value="DIESEL">DIESEL</option>
           		<option value="GASOLINA">GASOLINA</option>
           		<option value="HIBRIDO">HIBRIDO</option>
           </select>
       </div>
       <div class="divForm">
           <label>Indique el precio</label>
           <input type="number" id="precio" name="precio">
       </div>
       <div class="divForm">
           <textarea rows="4" cols="30" name="descripcion" placeholder="Escriba una breve descripcion"></textarea>
           
       </div>
       <div class="botonInicio" >
           <input type="submit" id="boton" name="boton" value="Publicar anuncio">
       </div>
     </form>
</div>

<!-- Incluimos el footer, el cual lo tenemos en otra página y lo importamos, así reciclamos código -->
     <%@ include file="footer.jsp" %>
	
</body>
</html>