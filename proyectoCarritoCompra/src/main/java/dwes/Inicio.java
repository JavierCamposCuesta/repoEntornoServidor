package dwes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		Usuario usuario = new Usuario();
		String nombre = "nombre";
		String nombreUsuario = request.getParameter(nombre);
		
		if(usuario.comprobarUsuario(request.getParameter(nombre), request.getParameter("pass"))) {
			sesion.setAttribute("LOGEADO", true);
			sesion.setAttribute(nombre, nombreUsuario);
			sesion.setAttribute("primeraVez", true);
			sesion.setAttribute("datosIncorrectos", "false");
			sesion.setAttribute("borrarSi", false);
			
			
			
			//El request.getContectPath() es para indicar la url actual y le sumamos a donde va a ir
			response.sendRedirect(request.getContextPath()+"/Catalogo");
			
		}
		else {
//			
			sesion.setAttribute("datosIncorrectos", "true");
			response.sendRedirect(request.getContextPath()+"/Inicio");
			
//			
			
			
		}
		
		

	}

}
