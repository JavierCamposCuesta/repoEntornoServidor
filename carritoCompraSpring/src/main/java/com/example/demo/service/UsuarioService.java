package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


import com.example.demo.model.Usuario;

@Service
public class UsuarioService {
	
	/**
	 * Coleccion de usuarios
	 * get by nickName
	 * login
	 * add pedido
	 * get allPedidos
	 * get pedido by ref
	 * remove pedido
	 */
	
	private HashSet<Usuario> listaUsuarios = new HashSet<>();
	
	@PostConstruct
	public void init() {
		listaUsuarios.addAll(Arrays.asList(
				new Usuario("JCampos", "Javier Garcia", "675446757", "Huelva, 23", "holaKAse"), 
				new Usuario("Antonio123", "Antonio Perez", "675446757", "Cadiz 12", "passAntonio"), 
				new Usuario("user", "", "", "", "user"),
				new Usuario("pepe", "Pepe Campos", "675446757", "Sevilla 22", "pepe")));
	}
	
	public HashSet<Usuario> findAll(){
		return listaUsuarios;
	}
	
	public boolean comprobarUsuario(Usuario usuario) {
		boolean encontrado = false;
		for (Usuario usuarioExistente : listaUsuarios) {
			if(usuarioExistente.getNickName().equals(usuario.getNickName()) && usuarioExistente.getPass().equals(usuario.getPass())) {
				encontrado = true;
			}
			else {
				encontrado=false;
			}
			
		}
		
		return encontrado;
	}
	
	
	public Usuario darUsuario(Object object) {
		ArrayList<Usuario> listaUsuariosArrayList = new ArrayList<Usuario>(listaUsuarios);
		Usuario usuario;
		usuario = listaUsuariosArrayList.get(listaUsuariosArrayList.indexOf(object));
		return usuario;
		
	}
	
	
	
	
	
	

}
