package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
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
	
	/**
	 * Lista de todos los usuarios que existen
	 */
	private HashSet<Usuario> listaUsuarios = new HashSet<>();
	
	@PostConstruct
	public void init() {
		
		/**
		 * Usuarios que creamos por defecto
		 */
		listaUsuarios.addAll(Arrays.asList(
				new Usuario("JCampos", "Javier Garcia", "675446757", "Huelva, 23", "holaKAse", "the__campos@hotmail.com"), 
				new Usuario("Antonio", "Antonio Perez", "675446757", "Cadiz 12", "passAntonio","xulitoVacilon@hotmail.com"), 
				new Usuario("user", "", "", "", "user", "xulitoVacilon@hotmail.com"),
				new Usuario("pepe", "Pepe Campos", "675446757", "Sevilla 22", "pepe", "xulitoVacilon@hotmail.com")));
	}
	
	
	/**
	 * Metodo para devolver la lista de usuarios
	 * @return lista de usuarios
	 */
	public HashSet<Usuario> findAll(){
		return listaUsuarios;
	}
	
	
	/**
	 * Metodo para comprobar que el usuario existe en la lista de usuarios
	 * @param usuario
	 * @return true o false
	 */
	public boolean comprobarUsuario(Usuario usuario) {

		return listaUsuarios.contains(usuario);

	}
	
	/**
	 * Metodo que devuelve un usuario, para ello creamos otro usuario con el mismo identificador
	 * para poder buscarlo en la lista de usuarios
	 * @param object que sera un usuario
	 * @return el usuario que buscamos
	 */
	public Usuario darUsuario(Object object) {
		ArrayList<Usuario> listaUsuariosArrayList = new ArrayList<Usuario>(listaUsuarios);
		Usuario usuario;
		usuario = listaUsuariosArrayList.get(listaUsuariosArrayList.indexOf(object));
		return usuario;
		
	}
	
	
	/**
	 * Metodo para mostrar un pedido concreto, para ello le pasamos un pedido y un usuario
	 * @param pedidoEditar
	 * @param usuario
	 * @return devuleve el pedido que buscamos
	 */
	public Pedido darPedido(Pedido pedidoEditar, Usuario usuario) {
		return usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoEditar));
		
	}
	
	
	/**
	 * Metodo para modificar un pedido, para ello le pasamos todos los nuevos parametros, ademas del
	 * un nuevo pedido para poder buscar el pedido que necesitamos y el usuario la que pertenece
	 * @param pedidoBuscar
	 * @param usuario
	 * @param metodoPago
	 * @param costeEnvio
	 * @param direccion
	 * @param telefono
	 * @param email
	 */
	public void modificarPedido (Pedido pedidoBuscar, Usuario usuario, String metodoPago, Double costeEnvio, String direccion, String telefono, String email) {
		
		
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setMetodoPago(metodoPago);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setCosteEnvio(costeEnvio);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setDireccion(direccion);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setTelefono(telefono);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setEmail(email);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).calcularImporte();;
		
	}
	
	
	/**
	 * Metodo para añadir nuevos usuarios
	 * @param newUsuario
	 * @return true o false
	 */
	public boolean addUsuario(Usuario newUsuario) {
		return listaUsuarios.add(newUsuario);
	}
	
	
	
	
	
	

}
