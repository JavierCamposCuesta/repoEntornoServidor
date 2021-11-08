<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<header class="header">
      <div class="divHeader">
          <span class="logo">
              <a href="index.html" class="enlaceLogo">
                    <img src="imagenes/logoSinFondo.png">
              </a>
          </span>
          <input type="checkbox" id="botonMenu">
          <label for="botonMenu" class="labelBoton"><img src="imagenes/IconoMenu.png"></label>

          <nav class="menuNavegacion">
            <ul>
              <li><a href="<%= request.getContextPath()+"/Catalogo" %>">Catálogo</a></li>
              <li><a href="<%= request.getContextPath()+"/CarritoCompra" %>">Carrito</a></li>
              <li><a href="<%= request.getContextPath()+"/Logout" %>">Cerrar Sesión</a></li>
              
            </ul>
          </nav>
      </div>
  </header>
<body>

</body>
</html>