package beans;

public class Coche {
	private String marca;
	private String modelo;
	private int km;
	private int year;
	private String combustible;
	private int precio;
	private String descripcion;
	
	public Coche() {
		
	}

	/**
	 * 
	 * @return la marca en mayúsculas cuando no sea nula
	 * @throws Exception Saltará una excepcion de modelo no válido que nos llevará a la pagina error.jsp
	 */
	public String getMarca() throws Exception {
		if(marca == null) {
			throw new Exception("Marca no valida");
		}
		else {
			return marca.toUpperCase();
			
		}
		
	}

	public void setMarca(String marca) throws Exception{
			this.marca=marca;
	}
	
	/**
	 * 
	 * @return el modelo cuando no sea nulo
	 * @throws Exception Saltará una excepcion de modelo no válido que nos llevará a la pagina error.jsp
	 */
	public String getModelo() throws Exception {
		if(modelo==null) {
			throw new Exception("Modelo no válido");
		}
		else {
			return modelo;
			
		}
	}

	public void setModelo(String modelo){
			this.modelo=modelo;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	
	/**
	 * 
	 * @return el precio cuando no sea nulo
	 * @throws Exception Saltará una excepcion de precio no válido que nos llevará a la pagina error.jsp
	 */
	public int getPrecio() throws Exception {
		if(precio == 0) {
			throw new Exception("Precio no valido");
		}
		else {
			
			int total = (int) (precio*1.21);
			return total;
		}
		
		
	}

	public void setPrecio(int precio) throws Exception {
		
			this.precio=precio;
		
	}
/**
 * Si la descripción es nula la convertiremos en un string vacio
 * @return El string de la descripcion, ya sea vacia o con algún dato
 */
	
	public String getDescripcion() {
		if(descripcion == null) {
			this.descripcion=" ";
		}
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
	
	
	

}
