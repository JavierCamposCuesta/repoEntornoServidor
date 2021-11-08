package dwes;

import java.util.HashMap;

public class Usuario {
	

	HashMap<String, String> login = new HashMap<String, String>();
	
	
	
	public Usuario() {
		super();
		login.put("usuario", "usuario");
		login.put("admin", "admin");
		login.put("user", "user");
		login.put("javier", "javier");
	}



	public boolean comprobarUsuario(String nombre, String pass) {
		boolean resultado = false;
		if(login.containsKey(nombre)) {
			if(login.get(nombre).equals(pass))
				resultado = true;
		}
		return resultado;

	}
}
