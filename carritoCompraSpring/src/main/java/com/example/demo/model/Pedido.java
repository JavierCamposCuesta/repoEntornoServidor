package com.example.demo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class Pedido {
	//La referencia se ira incrementa a la vez que se vayan creando nuevos pedidos
	private int ref = 0;
	private static int contador=0;
	public HashSet<Producto> productos = new HashSet<Producto>();
	private String direccion;
	private String telefono;
	private String email;
	private Double costeEnvio;
	private String metodoPago;
	private Double importe;
	//total con iva es importe + iva
	private Double totalConIva;
	private Double iva;
	//importe total es el importe + envio + iva
	private Double importeTotal;
	private LocalDate fecha;
	
	public Pedido(int ref) {
		this.ref = ref;
	}
	public Pedido() {
		this.ref = contador;
		contador++;
	}
	
	public Pedido(HashSet<Producto> productos, String direccion, String telefono, String email,
			Double costeEnvio, String metodoPago) {
		super();
		this.ref= contador;
		this.productos = productos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.costeEnvio = costeEnvio;
		this.metodoPago = metodoPago;
		this.fecha = LocalDate.now();
		
		calcularImporte();
		contador++;
	}
	/**
	 * Este metodo asignará una nueva referencia al crear el pedido
	 */

	
	public void calcularImporte() {
		Double calculaImporte = 0.0;
		for(Producto producto : productos) {
			calculaImporte+= producto.getPrecio()*producto.getCantidad();
		}
		this.importe = calculaImporte;
		this.iva = this.importe * 0.21;
		this.totalConIva= this.importe + this.iva;
		this.importeTotal = (this.importe + this.iva) + this.costeEnvio; 
	}

	public int getRef() {
		return ref;
	}
	
	

	public LocalDate getFecha() {
		return fecha;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public HashSet<Producto> getProductos() {
		return productos;
	}

	public void setProductos(HashSet<Producto> productos) {
		this.productos = productos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getEnvio() {
		return costeEnvio;
	}

	public void setEnvio(Double costeEnvio) {
		this.costeEnvio = costeEnvio;
	}

	public Double getImporte() {
		importe = Math.round(importe * Math.pow(10, 2)) / Math.pow(10, 2);

		return importe;
	}

	public void setImporte(Double importe) {
		
		this.importe = importe;
	}
	

	public Double getCosteEnvio() {
		return costeEnvio;
	}

	public void setCosteEnvio(Double costeEnvio) {
		this.costeEnvio = costeEnvio;
	}

	public Double getIva() {
		iva = Math.round(iva * Math.pow(10, 2)) / Math.pow(10, 2);

		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getImporteTotal() {
		importeTotal = Math.round(importeTotal * Math.pow(10, 2)) / Math.pow(10, 2);

		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
	

	public Double getTotalConIva() {
		totalConIva = Math.round(totalConIva * Math.pow(10, 2)) / Math.pow(10, 2);
		return totalConIva;
	}

	public void setTotalConIva(Double totalConIva) {
		this.totalConIva = totalConIva;
	}
	
	

	/**
	 * Los pedidos se identificaran por la referencia
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ref);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(ref, other.ref);
	}
	
	
	
	
	

}
