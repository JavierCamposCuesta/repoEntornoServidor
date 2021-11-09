package dwes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalogo() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    final String LOGEADO = "LOGEADO";
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		
		//Comprobamos que existe la sesion y el usuario está logeado, si no es así lo redirigimos al inicio para que inicie sesion
		if(!sesion.isNew() && sesion.getAttribute(LOGEADO)!=null && (boolean)sesion.getAttribute(LOGEADO)) {
			
			
			
			/* 
			 * Creamos los productos con sus atributos
			 * Metemos los productos en un HashSet
			 * Creamos un nuevo atributo en la sesion que incluirá la lista de los productos
			 * ...
			 * 
			 * pasar al JSP la list
			 */
			
			
			Producto producto1 = new Producto("1", "NJSJ Altavoces PC", "Altavoz 10W ALTAVOZ 2.0 USB GAMING sobremesa, con luces led que cambian de color ajustable", 20.5, "imagenes/altavoz.jpg", 0);
			Producto producto2 = new Producto("2", "HyperX HX-HSCF Cascos", "Cascos Gaming inalámbricos, sonido 5.1 envolvente, 100% Gaming, sonido perfecto para juegos", 104.13, "imagenes/cascos.jpg", 0);
			Producto producto3 = new Producto("3", "Exco-Alfombrilla Gaming", "Alfombrilla antidezlizante de goma, gruesa, perfecto movimiento del raton, sugeción articular", 11.9, "imagenes/alfombrilla.jpg", 0);
			Producto producto4 = new Producto("4", "Mesa Gaming MGD", "Mesa ergonómica Gaming fabricada en fibra de carbono, patas de aluminio, maxima resistencia", 114.56, "imagenes/mesa.jpg", 0);
			Producto producto5 = new Producto("5", "MSI Teclado Gaming", "Teclado mecánico Gaming RGB, máxima rapidez, tacto inmejorable, dureza ajustable colores cambiantes", 28.92, "imagenes/teclado.jpg", 0);
			Producto producto6 = new Producto("6", "MSI Optix MAG30", "Monitor Plano Gaming 29.5 pulgadas 200HZ, máxima calidad, distintos puertos de entrada, brillo ajustable", 446.10, "imagenes/pantalla.jpg", 0);
			
			
			//Creamos una lista de productos de tipo HashSet, para no poder añadir productos repetidos(Con la misma id)
			HashSet<Producto>productos= new HashSet<Producto>();
			productos.add(producto1);
			productos.add(producto2);
			productos.add(producto3);
			productos.add(producto4);
			productos.add(producto5);
			productos.add(producto6);

			
			//Creamos un atributo en sesion con la lista de productos
			sesion.setAttribute("productos", productos);
			
			//Nos mandará al jsp de catalogo, y nos mostrará los productos que hemos creado
			request.getRequestDispatcher("catalogo.jsp").forward(request, response);
			
			
		}
		
		//La sesion no existe o el usuario no está logeado, por que lo mandamos a la página de inicio para que inicie sesión
		else {
			sesion.invalidate();
			response.sendRedirect(request.getContextPath());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		//Si el boton que hemos pulsado es el de ir al carrito nos mandará a al servlet de CarritoCompra
		String IrCarrito = "Ir al carrito";
		if(!sesion.isNew() && sesion.getAttribute(LOGEADO)!=null && (boolean)sesion.getAttribute(LOGEADO)) {
		
			if(request.getParameter(IrCarrito)!=null && request.getParameter(IrCarrito).equals(IrCarrito)) {
				response.sendRedirect(request.getContextPath()+"/CarritoCompra");
		}
			}else {
			sesion.invalidate();
			response.sendRedirect(request.getContextPath());
		}
	}

}
