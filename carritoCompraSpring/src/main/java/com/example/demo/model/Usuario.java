package com.example.demo.model;

import java.util.HashSet;
import java.util.Objects;

public class Usuario {
	private String nickName;
	private String nombre;
	private String telefono;
	private String direccion;
	private String email;
	private String pass;
	
	private HashSet<Pedido> listaPedidos = new HashSet<Pedido>();
	
	

	public Usuario() {
		
	}
	public Usuario(String nickName, String nombre, String telefono, String direccion, String pass) {
		this.nickName = nickName;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.pass = pass;
	}
	
	public void addPedido(Pedido pedido) {
		listaPedidos.add(pedido);
	}
	
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HashSet<Pedido> getListaPedidos() {
		return listaPedidos;
	}
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nickName, other.nickName);
	}
	
	
}


