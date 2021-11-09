<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FacturaCompra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>

<style>
    .envio>div>img, .envio>div>p,.pago>div>img, .pago>div>p{
        width: 100%;
    }
    .envio>div, .pago>div{
        padding: 15px;
    }
img{
    height: 80px;
}

i{
    width: 30px;
    height: auto;
    color: rgb(255, 0, 0);
}

</style>
<jsp:include page="header.jsp"/>

<body>
    
    <div class="container">

        <div class="d-flex justify-content-end p-4">
            Bienvenid@, <h5><%=session.getAttribute("nombre") %></h5>
            <a href="<%= request.getContextPath()+"/Logout" %>" class="btn btn-danger">Logout</a>
        </div>

        <div class="d-flex flex-row" style="background-color: rgba(255, 226, 145, 0.603);">

        

       

        <div class="mt-4">
            <div class="d-flex p-5">
                <i class="bi bi-cart4"></i>
                <h4>FACTURA</h4>
            </div>
            <div class="row mb-4">
            	<div class="col">
            	</div>
            	<div class="col colAlineadoCentro">
            	<h6><b>Descripción</b></h6>
            	</div>
            	<div class="col colAlineadoCentro">
            	<h6><b>Precio unitario</b></h6>
            	</div>
            	<div class="col colAlineadoCentro">
            	<h6><b>Cantidad</b></h6>
            	</div>
            	<div class="col colAlineadoCentro">
            	<h6><b>Precio total</b></h6>
            	</div>
            </div>
		 <c:forEach items="${productosEnCarrito}" var="producto">
         <div class="row mb-4">
       		<div class="col colAlineadoCentro">
            	<img class="imagenPedido" src="${producto.getImagen() }">
			</div>
	        <div class="col">
	                <h5 class="card-title">${producto.getNombre() }</h5>
	                <p class="card-text">${ producto.getDescripcion() }</p>
            </div>
            <div class="col colAlineadoCentro">
            	<p>${producto.getPrecio() } €</p>
			</div>
            <div class="col colAlineadoCentro">
            		<p class="card-text">${ producto.getCantidad() } uds</p>
			</div>
			 <div class="col colAlineadoCentro">
            		<p class="card-text">${ producto.getCantidad() * producto.getPrecio() } €</p>
			</div>
			
		</div>
		</c:forEach>
<%-- 			 <div class="col colAlineadoCentro">
            		<p class="card-text"><%=session.getAttribute("totalSinIva") %>€</p>
			</div> --%>
		<div class="row mb-4">
       		<div class="col"></div>
	        <div class="col"/></div>
            <div class="col"/></div>
            <div class="col colAlineadoCentro">
            		<p class="card-text"><strong>Total sin IVA</strong></p>
			</div>
			 <div class="col colAlineadoCentro">
            		<p class="card-text"><%=session.getAttribute("totalSinIva") %> €</p>
			</div>
		</div>
		<div class="row mb-4">
       		<div class="col"></div>
	        <div class="col"/></div>
            <div class="col"/></div>
            <div class="col colAlineadoCentro">
            		<p class="card-text"><strong>IVA</strong></p>
			</div>
			 <div class="col colAlineadoCentro">
            		<p class="card-text"><%=session.getAttribute("iva") %> €</p>
			</div>
		</div>
		<div class="row mb-4">
       		<div class="col"></div>
	        <div class="col"/></div>
            <div class="col"/></div>
            <div class="col colAlineadoCentro">
            		<p class="card-text"><strong>Total con IVA</strong></p>
			</div>
			 <div class="col colAlineadoCentro">
            		<p class="card-text"><%=session.getAttribute("totalConIva") %> €</p>
			</div>
		</div>
		<div class="row mb-4">
       		<div class="col"></div>
	        <div class="col"/></div>
            <div class="col"/></div>
            <div class="col colAlineadoCentro">
            		<p class="card-text"><strong>Envío</strong></p>
			</div>
			 <div class="col colAlineadoCentro">
            		<p class="card-text"><%=session.getAttribute("envio") %> €</p>
			</div>
		</div>
		<div class="row mb-4">
       		<div class="col"></div>
	        <div class="col"/></div>
            <div class="col"/></div>
            <div class="col colAlineadoCentro" style="border-top: 2px solid black;">
            		<p class="card-text"><strong>Total Factura</strong></p>
			</div>
			 <div class="col colAlineadoCentro" style="border-top: 2px solid black;">
            		<p class="card-text"><%=session.getAttribute("totalFactura") %> €</p>
			</div>
		</div>
		
		
		
        </div>


    </div>
    </div>

</body>
<jsp:include page="footer.jsp"/>
</html>