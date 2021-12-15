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
	
	private HashSet<Usuario> listaUsuarios = new HashSet<>();
	
	@PostConstruct
	public void init() {
		listaUsuarios.addAll(Arrays.asList(
				new Usuario("JCampos", "Javier Garcia", "675446757", "Huelva, 23", "holaKAse", "the__campos@hotmail.com"), 
				new Usuario("Antonio", "Antonio Perez", "675446757", "Cadiz 12", "passAntonio","xulitoVacilon@hotmail.com"), 
				new Usuario("user", "", "", "", "user", "xulitoVacilon@hotmail.com"),
				new Usuario("pepe", "Pepe Campos", "675446757", "Sevilla 22", "pepe", "xulitoVacilon@hotmail.com")));
	}
	
	public HashSet<Usuario> findAll(){
		return listaUsuarios;
	}
	
	public boolean comprobarUsuario(Usuario usuario) {

		return listaUsuarios.contains(usuario);

	}
	
	
	public Usuario darUsuario(Object object) {
		ArrayList<Usuario> listaUsuariosArrayList = new ArrayList<Usuario>(listaUsuarios);
		Usuario usuario;
		usuario = listaUsuariosArrayList.get(listaUsuariosArrayList.indexOf(object));
		return usuario;
		
	}
	
	public Pedido darPedido(Pedido pedidoEditar, Usuario usuario) {
		return usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoEditar));
		
	}
	
	public void modificarPedido (Pedido pedidoBuscar, Usuario usuario, String metodoPago, Double costeEnvio, String direccion, String telefono, String email) {
		
		
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setMetodoPago(metodoPago);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setCosteEnvio(costeEnvio);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setDireccion(direccion);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setTelefono(telefono);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).setEmail(email);
		usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).calcularImporte();;
		
	}
	
	public boolean addUsuario(Usuario newUsuario) {
		return listaUsuarios.add(newUsuario);
	}
	
	
	
	
	
	

}
