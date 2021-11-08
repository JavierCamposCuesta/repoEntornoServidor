package dwes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Creo una lista HashSet para almacenar los productos que se vayan añadiendo a carrito, es del tipo hashSet para que no se puedan meter productos repetidos,
	 * Lo hago aqui fuera para que sea fija, si lo hiciese en el doPost me crearía una lista cada vez que se pulsase un boton de catálogo
	 * Creamos la lista, pero esta lista tenemos que resetearla cuando entremos con otra sesion
	 */
	final HashSet<Producto>productosEnCarrito= new HashSet<Producto>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrito() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    final String LOGEADO = "LOGEADO";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if(!sesion.isNew() && sesion.getAttribute(LOGEADO)!=null && (boolean)sesion.getAttribute(LOGEADO)) {
			request.getRequestDispatcher("carrito.jsp").forward(request, response);
		}else {
		sesion.invalidate();
		response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		
		if(!sesion.isNew() && sesion.getAttribute(LOGEADO)!=null && (boolean)sesion.getAttribute(LOGEADO)) {
			
			//Si es la primera vez que entramos en catalogo tenemos que resetear la lista de productos en carrito, para ello eliminamos todos los elementos que pueda tener
			if((boolean) sesion.getAttribute("primeraVez")) {
				productosEnCarrito.removeAll(productosEnCarrito);
				
				//Modificamos el atributo
				sesion.setAttribute("primeraVez", false);
			}
			
			//Aunque sea la primera vez que entra este bloque de código lo tiene que ejecutar siempre
			
			String productoId= request.getParameter("productoId");
			
			/**
			 * Creo un ArrayList al que le voy a meter la lista productos que tengo almacenada en sesion
			 */
			HashSet<Producto> productos = (HashSet<Producto>) sesion.getAttribute("productos");
	
			
			//Borramos el producto en el caso que pulsemos el boton de borrar, esto lo hacemos recuperando el id del producto (Que lo pasamos en el input de tipo hidden
			//y buscando en la lista de los productos en carrito el producto que tiene ese id, y ese producto lo eliminamos
//			final String borrar = "borrar";
//			final String pagar = "pagar";
//			if(request.getParameter(borrar)!=null && request.getParameter(borrar).equals(borrar))  {
//				
//				//Para eliminar el producto podríamos hacerlo con un for recorriendo toda la lista de producto en carrito, buscando el que es y borrandolo, de la sigueinte forma comentada
//	
////					//Si el producto es igual al producto en productoCarrito lo que haremos será aumentar la cantidad de productoCarrito
////					
//				/*
//				 * Pero la forma más óptima de borrarlo sería crear un nuevo producto con el id del que queremos borrar, al identificar cada producto por el id
//				 * y utilizando hashSet, donde no vamos a poder meter productos repetidos, cuando le pedimos que borre el producto que acabamos de crear, buscará 
//				 * un producto con el mismo id y lo borrará
//				 */
//
//				Producto productoBorrar = new Producto(productoId, "", "", 0, "", 0);
//						productosEnCarrito.remove(productoBorrar);
//				//Una vez borrado pedimos que nos lleve a la misma página para recargarla
//				response.sendRedirect(request.getContextPath()+"/Carrito");
//			}
			
			
			//Si no pulsamos el boton de borrar, ni de ir a factura será porque el boton pulsado ha sido el de añadir al carrito
//			else {
				
				//Recorremos toda la lista de productos que tenemos almacenada en sesion
				for(Producto producto: productos) {
					//Si el productoId es igual al getId lo que haremos será aumentar la variable cantidad de producto y ademas lo añadiremos al array productosEnCarrito
					if(producto.getId().equals(productoId) ) {
						for(Producto productoCarrito: productosEnCarrito) {
							//Si el producto es igual al producto en productoCarrito lo que haremos será aumentar la cantidad de productoCarrito
							if(producto.equals(productoCarrito)) {
								productoCarrito.setCantidad(productoCarrito.getCantidad()+1);
							}
						}
						//Si no ha encontrado el producto en la lista productoCarrito es porque es la primera vez ue se añade, por lo que modifico la cantidad en la lista productos
						producto.setCantidad(producto.getCantidad()+1);
						productosEnCarrito.add(producto);
					}
				}
				//Añadimos a la seguion otro atributo que será la lista de los productos que hemos añadido al carrito
				sesion.setAttribute("productosEnCarrito", productosEnCarrito);
				response.sendRedirect(request.getContextPath()+"/Catalogo");
			}
			
			
//		}
		else {
		
		sesion.invalidate();
		response.sendRedirect(request.getContextPath());
		}
	}

}
