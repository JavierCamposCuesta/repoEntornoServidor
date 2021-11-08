package dwes;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ObjetosCatalogo {

HashMap<String, List<String>> productos = new HashMap<String, List<String>>();

	
	
	
	public ObjetosCatalogo() {
		productos.put("1", Arrays.asList("Titulo1", "Descripcion", "/imagenes/foto1.png"));
		productos.put("2", Arrays.asList("Titulo2", "Descripcion", "/imagenes/foto2.png"));
		productos.put("3", Arrays.asList("Titulo3", "Descripcion", "/imagenes/foto3.png"));
		productos.put("4", Arrays.asList("Titulo4", "Descripcion", "/imagenes/foto4.png"));
	
	}

}
