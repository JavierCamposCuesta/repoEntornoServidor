<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarritoCompra</title>
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

            <!-- MÉTODOS DE ENVÍO -->
            <div class="p-5 envio" style="width: 50%;">
                <div class="d-flex">
                    <i class="bi bi-mailbox2"></i>
                    <h4>Método de envío</h4>
                </div>

       <form  action="/proyectoCarritoCompra/Factura" method="post">
                <div class="d-flex">
                    <input type="radio" name="envio" value="Estandar" checked class="mt-4">
                    <img src="https://gssecurity.es/wp-content/uploads/2019/07/seur.png">
                    <p class="mt-4">Envío de 3-5 días laborables (<b>GRATIS</b>)</p>
                </div>


                <div class="d-flex">
                    <input type="radio" name="envio" value="Premium" class="mt-4">
                    <img src="https://gls-group.eu/ES/media/images/Video_Sound_Images_GLS_Logo_Positive_200x150px-35697_IMG_200x150.png">
                    <p class="mt-4">Envío de 1-3 días laborables (<b>2,99€</b>)</p>
                </div>
                <div class="d-flex">
                    <input type="radio" name="envio" value="Express" class="mt-4">
                    <img src="https://www.correosexpress.com/wpc/chx-portal62-theme/images/logo_correos_express.png">
                    <p class="mt-4">Envío express 24 horas(<b>4,99€</b>)</p>
                </div>

            </div>
    

            <!-- MÉTODOS DE PAGO -->
            <div class="p-5 pago" style="width: 50%;">
                <div class="d-flex">
                    <i class="bi bi-credit-card"></i>
                    <h4>Método de pago</h4>
                </div>

                <div class="d-flex">
                    <input type="radio" name="pago" value="Tarjeta" checked class="mt-4">
                    <img src="http://www.comprotucupoendolares.cl/wp-content/uploads/2019/02/visa-mastercard-amex_0.png">
                    <p class="mt-4">Pago con tarjeta</p>
                </div>

                <div class="d-flex">
                    <input type="radio" name="pago" value="Paypal" class="mt-4">
                    <img src="https://s3.cointelegraph.com/storage/uploads/view/3278bdc14c74dd4e85732b776d0e5b1d.png" alt="">
                    <p class="mt-4">Pago con Paypal</p>
                </div>

                <div class="d-flex">
                    <input type="radio" name="pago" value="Transferencia" class="mt-4">
                    <img src="https://www.donkiko.es/img/cms/transferenciabancaria.png">
                    <p class="mt-4">Pago por transferencia</p>
                </div>

            </div>
        </div>

        <div class="mt-4">
            <div class="d-flex p-5">
                <i class="bi bi-cart4"></i>
                <h4>RESUMEN DE PEDIDO</h4>
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
            	<h6><b></b></h6>
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
            	<!-- Este input se creará por cada producto que haya en la cesta -->
            	
            		<input type="number" name="${producto.getId() }" value="${producto.getCantidad()}" style="width: 50px;">
            		
            	
			</div>
			 <div class="col">
			 	<!-- Con este form creamos un input con icono de papelera para borrar los productos que queramos -->
            		<!-- En el input de tipo hidden almacenamos el id del producto que queremos borrar -->
            		<input type="hidden" name="productoId" value="${ producto.getId() }">
            		<button type="submit" value="${ producto.getId() }" name="borrar"><i class="bi bi-trash"></i></button>
			</div>
		</div>
		</c:forEach>
		<div class="d-flex justify-content-end">
		<button type="submit" name="pagar" value="pagar" class="btn btn-primary mt-5">Terminar y pagar</button>
		</div>
        </div>
       </form>

    </div>

</body>
<jsp:include page="footer.jsp"/>
</html>