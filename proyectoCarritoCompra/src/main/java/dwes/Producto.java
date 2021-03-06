package dwes;


import java.util.Objects;

/**
 * 
 * @author Javier Campos Cuesta
 *
 */
public class Producto{
private String nombre;
private String id;
private String imagen;
private String descripcion;
private double precio;
private int cantidad;


/**
 * 
 * @param id
 * @param nombre
 * @param descripcion
 * @param precio
 * @param imagen
 * @param cantidad
 */
public Producto( String id, String nombre, String descripcion,  double precio, String imagen, int cantidad) {
	super();
	this.nombre = nombre;
	this.id = id;
	this.imagen = imagen;
	this.descripcion = descripcion;
	this.precio= precio;
	this.cantidad = cantidad;
	
}

/**
 * Metodo para calcular el total de productos por el precio
 * @return el total de la cantidad por el precio
 */
public double getImporteTotal() {
	return this.precio * this.cantidad;
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
