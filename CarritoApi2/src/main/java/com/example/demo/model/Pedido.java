package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pedidos")
public class Pedido {
	//La referencia se ira incrementa a la vez que se vayan creando nuevos pedidos

	@Id
	private int ref=0;
	
	private static int contador=0;

	
	
	@OneToMany(cascade = CascadeType.ALL
		, orphanRemoval = true
			,fetch=FetchType.LAZY
			)
	@JsonManagedReference
	public List<LineaPedido> lineasPedidos = new ArrayList<LineaPedido>();
	

	private String direccion;
	

	private String telefono;
	

	private String email;
	

	private Double costeEnvio;
	

	private String metodoPago;
	

	private Double importe=0.0;
	//total con iva es importe + iva
	

	private Double totalConIva=0.0;
	

	private Double iva=0.0;
	//importe total es el importe + envio + iva
	
	
	private Double importeTotal=0.0;
	
	
	private LocalDate fecha;
	
	public Pedido(int ref) {
		this.ref = ref;
		this.lineasPedidos = (List<LineaPedido>) lineasPedidos;
//		this.fecha = LocalDate.now();
	}
	public Pedido() {
		this.ref = contador;
		contador++;
		this.fecha = LocalDate.now();
		this.lineasPedidos = (List<LineaPedido>) lineasPedidos;
	}
	
	public Pedido(ArrayList<LineaPedido> lineasPedidos, String direccion, String telefono, String email,
			Double costeEnvio, String metodoPago) {
		super();
		this.ref= contador;
		this.lineasPedidos = (List<LineaPedido>) lineasPedidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.costeEnvio = costeEnvio;
		this.metodoPago = metodoPago;
		this.fecha = LocalDate.now();
		
		calcularImporte();
		contador++;
		
	}
	
	public Pedido(String direccion, String telefono, String email,
			Double costeEnvio, String metodoPago) {
		super();
//		this.ref= contador;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.costeEnvio = costeEnvio;
		this.metodoPago = metodoPago;
		this.fecha = LocalDate.now();
		this.lineasPedidos = (List<LineaPedido>) lineasPedidos;
//		calcularImporte();
		contador++;
		
	}


	/**
	 * Este m??todo calcular?? el importe del pedido, y asiganr?? a cada alemento(importe, iva, totalConIva y importeTotal
	 * su correspondiente valor
	 */
	public void calcularImporte() {
		Double calculaImporte = 0.0;
		for(LineaPedido lineaPedido : lineasPedidos) {
			calculaImporte+= lineaPedido.getProducto().getPrecio()*lineaPedido.getCantidad();
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

	
	public List<LineaPedido> getLineasPedidos() {
		return lineasPedidos;
	}
	public void setLineasPedidos(LineaPedido lineaPedido) {
		this.lineasPedidos.add(lineaPedido);
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


	
	/**
	 * Este metodo recibir?? el coste del envio
	 * @param costeEnvio 
	 */
	public void setEnvio(Double costeEnvio) {
		this.costeEnvio = costeEnvio;
	}

	/**
	 * Get para mostrar el importe
	 * @return el total del importe con dos decimales
	 */
	
	public Double getImporte() {
		importe = Math.round(importe * Math.pow(10, 2)) / Math.pow(10, 2);

		return importe;
	}

	
	/**
	 * Este metodo recibir?? el importe
	 * @param importe 
	 */
	public void setImporte(Double importe) {
		
		this.importe = importe;
	}
	
	/**
	 * Get para mostrar el importe del envio
	 * @return el total del envio
	 */

	public Double getCosteEnvio() {
		return costeEnvio;
	}

	
	/**
	 * Este metodo recibir?? el coste del envio
	 * @param costeEnvio 
	 */
	public void setCosteEnvio(Double costeEnvio) {
		this.costeEnvio = costeEnvio;
	}

	/**
	 * Get para mostrar el importe del iva
	 * @return el total del importe del iva con dos decimales
	 */
	
	public Double getIva() {
		iva = Math.round(iva * Math.pow(10, 2)) / Math.pow(10, 2);

		return iva;
	}

	
	/**
	 * Este metodo recibir?? el iva
	 * @param iva 
	 */
	public void setIva(Double iva) {
		this.iva = iva;
	}

	/**
	 * Get para mostrar el importe total
	 * @return el total del importe total con dos decimales
	 */
	
	public Double getImporteTotal() {
		importeTotal = Math.round(importeTotal * Math.pow(10, 2)) / Math.pow(10, 2);

		return importeTotal;
	}

	
	/**
	 * Este metodo recibir?? el importe total
	 * @param importeTotal 
	 */
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	/**
	 * Get para mostrar el metodo de pago
	 * @return el total del metodo de pago
	 */
	
	public String getMetodoPago() {
		return metodoPago;
	}

	/**
	 * Este metodo recibir?? el metodo de pago
	 * @param metodoPago
	 */
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
	
	/**
	 * Get para mostrar el importe total con iva
	 * @return el total del importe total con iva con dos decimales
	 */
	
	public Double getTotalConIva() {
		totalConIva = Math.round(totalConIva * Math.pow(10, 2)) / Math.pow(10, 2);
		return totalConIva;
	}

	/**
	 * Este metodo recibir?? el total con iva
	 * @param totalConIva 
	 */
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
