package com.example.demo.model;

import java.util.Objects;



public class Producto {
	private String id;
	private String nombre;
	private double precio;
	private String descripcion;
	private String imagen;
	private int cantidad;
	private double importeTotal;
	public Producto() {
		
	}
	
	public Producto( String id) {
		this.id = id;
	}
	
	public Producto( String id, String nombre, String descripcion,  double precio, String imagen, int cantidad) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.precio= precio;
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	







	public double getImporteTotal() {
		return this.precio * this.cantidad;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	/**
	 * Los productos se indentificarán por el id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

}