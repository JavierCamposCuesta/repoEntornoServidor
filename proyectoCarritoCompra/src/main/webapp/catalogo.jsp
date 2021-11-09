<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   import="java.util.*"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head lang="en">
<meta charset="UTF-8">
<title>Catalogo</title>
<link rel="stylesheet" type="text/css" href="style.css">
<!-- Incluye las librerias de bootstrap -->
<jsp:include page="bootstrap.jsp"/>
</head>
<jsp:include page="header.jsp"/>
<body>

	<div class="container">
           <form method="post" class="d-flex justify-content-end mb-5">
         			<button type="submit" name="Ir al carrito" value="Ir al carrito" class="btn btn-primary mt-5"><i class="bi bi-cart-plus-fill"> Ir al carrito</i></button>
                 	
             	</form>
          <h1>Hola <%=session.getAttribute("nombre") %>, ¿Qué desea comprar?</h1>

       <!-- Recorremos la lista de productos que tenemos en sesion y vamos creando el html con cada uno de los productos -->
       <div class="row row-cols-1 row-cols-md-3 g-4">
       <c:forEach items="${productos}" var="producto">
       <div class="col">
       		<div class="card h-100">
            	<img class="imagenCatalogo mt-3" src="${producto.getImagen() }" >
	            <div class="card-body">
	                <h5 class="card-title"><b>${producto.getNombre() }</b></h5>
	                <p class="card-text">${ producto.getDescripcion() }</p>
	                
	                <form action="/proyectoCarritoCompra/Carrito" method="post">
                    	<input type="hidden" name="productoId" value="${ producto.getId() }">
                    	<button type="submit" name="Entrar" value="Entrar" class="btn btn-primary w-100 mt-5"><i class="bi bi-cart-plus-fill"> Añadir al carrito</i></button>
                	</form>
					
	            </div>
	           </div>
        </div>
         </c:forEach>
      </div>
         <form method="post" class="d-flex justify-content-end">
   			<button type="submit" name="Ir al carrito" value="Ir al carrito" class="btn btn-primary mt-5"><i class="bi bi-cart-plus-fill"> Ir al carrito</i></button>
       	</form>
       
      </div>

</body>
<jsp:include page="footer.jsp"/>
</html>