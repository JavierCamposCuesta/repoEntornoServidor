package dwes;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Factura
 */
@WebServlet("/Factura")
public class Factura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Factura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    final String LOGEADO = "LOGEADO";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		sesion.setAttribute("borrarSi", "no");
		if(!sesion.isNew() && sesion.getAttribute(LOGEADO)!=null && (boolean)sesion.getAttribute(LOGEADO)) {
//			if(request.getParameter("borrar")!=null) {
//				String idBorrar = request.getParameter("borrar");
//				sesion.setAttribute("idBorrar", idBorrar);
//				sesion.setAttribute("borrarSi", true);
//				response.sendRedirect(request.getContextPath()+"/Carrito");
//			}
//			else {
				
		
			
			HashSet<Producto> productosEnCarrito = (HashSet<Producto>) sesion.getAttribute("productosEnCarrito");
			double totalSinIva=0;
			for(Producto productoCarrito: productosEnCarrito) {
				//Cojo el valor de cada input numerico que le asigne el nombre de la id
				int cantidadTotal = Integer.parseInt(request.getParameter(productoCarrito.getId()));
				productoCarrito.setCantidad(cantidadTotal);
				totalSinIva+= (cantidadTotal * productoCarrito.getPrecio());
			}
			totalSinIva= Math.round(totalSinIva * Math.pow(10, 2)) / Math.pow(10, 2);
			sesion.setAttribute("totalSinIva", totalSinIva);
			
//			Calculamos el IVA
			double iva=totalSinIva * 0.21;
			iva = Math.round(iva * Math.pow(10, 2)) / Math.pow(10, 2);
			sesion.setAttribute("iva", iva);
			
//			Calculamos el total con IVA
			double totalConIva = totalSinIva + iva;
			totalConIva = Math.round(totalConIva * Math.pow(10, 2)) / Math.pow(10, 2);
			sesion.setAttribute("totalConIva", totalConIva);
			double envio=0;
			
			//Calculamos los gastos de envío
			if(request.getParameter("envio").equals("Premium")) {
				sesion.setAttribute("envio", 2.99);
				envio = 2.99;
			}
			else if(request.getParameter("envio").equals("Express")) {
				sesion.setAttribute("envio", 4.99);
				envio = 4.99;
			}
			else {
				sesion.setAttribute("envio", 0);
			}
			
			//Calculamos el total de la factura
//			double totalFactura = totalConIva + (double)sesion.getAttribute("envio");
			double totalFactura = totalConIva + envio;
			totalFactura = Math.round(totalFactura * Math.pow(10, 2)) / Math.pow(10, 2);
			sesion.setAttribute("totalFactura", totalFactura);
			
			
			request.getRequestDispatcher("factura.jsp").forward(request, response);
//			}
		}else {
		sesion.invalidate();
		response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
