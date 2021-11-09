package dwes;

import java.util.HashMap;
/**
 * 
 * @author Javier Campos Cuesta
 *
 */
public class Usuario {
	

	HashMap<String, String> login = new HashMap<String, String>();
	
	
	public Usuario() {
		super();
		login.put("usuario", "usuario");
		login.put("admin", "admin");
		login.put("user", "user");
		login.put("javier", "javier");
	}


/**
 * 
 * @param Recibe el nombre que se introducirá en el formulario
 * @param Recibe la pass que se introducirá en el formulario
 * @return Devolverá true o false segun se encuentren los datos en el hashMap o no
 */
	public boolean comprobarUsuario(String nombre, String pass) {
		boolean resultado = false;
		if(login.containsKey(nombre) && login.get(nombre).equals(pass)) {
			
				resultado = true;
		}
		return resultado;

	}
}
