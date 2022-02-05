package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categoria {
	
	@Id
	private String nombre;
	private String icono;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Anuncio>listaAnuncios = new ArrayList<Anuncio>();
	
	
	
	public Categoria() {
		
	}
	
	
	public Categoria(String nombre, String icono) {
		super();
		this.nombre = nombre;
		this.icono = icono;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getIcono() {
		return icono;
	}


	public void setIcono(String icono) {
		this.icono = icono;
	}


	public List<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}


	public void setListaAnuncios(List<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}
	
	

}
