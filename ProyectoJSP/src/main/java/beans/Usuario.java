package beans;

import java.time.LocalDate;


/**
 * 
 * @author Javier Campos Cuesta
 *
 */
public class Usuario {
	private String nombre;
	private int edad;
	private String passw;
	
	/**
	 * Contructor vacio para usuarios
	 */
	public Usuario() {
		super();
		this.nombre = null;
		this.passw = null;
		this.edad= 0;
	}

	/**
	 * 
	 * @return devulve el String nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param Le pasamos el String nombre recogido del formulario de registro
	 *
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return devulve el String passw
	 */
	public String getPassw() {
		return passw;
	}

	/**
	 * 
	 * @param Le pasamos el String passw recogido del formulario de registro
	 *
	 */
	public void setPassw(String passw) {
		this.passw = passw;
	}

	/**
	 * A través de la edad que hemos introducido calcula el año en el que nació restandolo con el año del sistema
	 * @return devulve el año de nacimiento
	 */
	public int getEdad() {
		LocalDate fecha = LocalDate.now();
		int yearNacimiento= fecha.getYear() - this.edad;
		return yearNacimiento;
	}

	/**
	 * 
	 * @param Le pasamos el int edad recogido del formulario de registro
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}


}
