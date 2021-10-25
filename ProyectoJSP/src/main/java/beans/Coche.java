package beans;

/**
 * 
 * @author Javier Campos Cuesta
 *
 */
public class Coche {
	private String marca;
	private String modelo;
	private int km;
	private int year;
	private String combustible;
	private int precio;
	private String descripcion;
	
	
	/**
	 * Constructor para el coche, este contructor es vacio
	 */
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

	/**
	 * 
	 * @param Le pasamos el String marca recogido del formulario
	 *
	 */
	public void setMarca(String marca){
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

	/**
	 * 
	 * @param Le pasamos el String modelo recogido del formulario
	 *
	 */
	public void setModelo(String modelo){
			this.modelo=modelo;
	}

	/**
	 * 
	 * @return devuelve el int km
	 */
	public int getKm() {
		return km;
	}

	/**
	 * 
	 * @param Le pasamos el int km recogido del formulario
	 *
	 */
	public void setKm(int km) {
		this.km = km;
	}

	/**
	 * 
	 * @return devuelve el int year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 
	 * @param Le pasamos el int year recogido del formulario
	 *
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 
	 * @return devulve el String combustible
	 */
	public String getCombustible() {
		return combustible;
	}

	/**
	 * 
	 * @param Le pasamos el String combustible recogido del formulario
	 *
	 */
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

	/**
	 * 
	 * @param Le pasamos el int precio recogido del formulario
	 *
	 */
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
