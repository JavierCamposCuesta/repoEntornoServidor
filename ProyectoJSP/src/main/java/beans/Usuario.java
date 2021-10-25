package beans;

import java.time.LocalDate;

public class Usuario {
	private String nombre;
	private int edad;
	private String passw;
	
	public Usuario() {
		super();
		this.nombre = null;
		this.passw = null;
		this.edad= 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

//	Vamos a hacer que en vez de la edad que tiene nos muestre el año en que nacio, para ello modificamos el get
	public int getEdad() {
		LocalDate fecha = LocalDate.now();
		int yearNacimiento= fecha.getYear() - this.edad;
		return yearNacimiento;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public boolean validarNombre() {
		return this.nombre!=null;
	}
	
	public boolean validarEdad() {
		return this.edad>=18&&this.edad<=150;
	}
	
	
	
	
	
	
	

}
