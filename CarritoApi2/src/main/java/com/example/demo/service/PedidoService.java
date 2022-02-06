package com.example.demo.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;


@Service
public class PedidoService {
	/**
	 * Coleccion de pedidos
	 * get by idPedido
	 * getAll
	 * addProducto
	 * remove producto
	 */
	
	@Autowired
	private ProductoService productoService;
	

	
	@Autowired
	private PedidoRepository repositorioPedido;
	
	@Autowired
	private LineaPedidoRepository repositorioLineaPedido;
	
	@Autowired
	private UsuarioRepository repositorioUsuario;
	
	@Autowired
	private ProductoRepository repositorioProducto;
	
	private Pedido nuevoPedido = new Pedido();
	
	@PostConstruct
	public void init() {
		
	}
	
	/*
	 * lista de productos en carrito, aqui se añadiran los productos que se vayan cliclando en
	 * el catálogo
	 */
	private HashSet<Producto> productosEnCarrito = new HashSet<Producto>();
	
	
	/**
	 * Metodo para devolver toda la lista de pedidos que existen en la bd
	 * @return lista de pedidos
	 */
	public List<Pedido>findAll(){
		return repositorioPedido.findAll();
	}
	
	
	/**
	 * Metodo para crear un pedido vacío
	 * @return el nuevo pedido que ha creado
	 */
	public Pedido crearPedido() {
		Pedido nuevoPedido = new Pedido();
		repositorioPedido.save(nuevoPedido);
		return nuevoPedido;
	}

	
	/**
	 * Metodo para modificar un pedido, recibe todos los datos a modificar y los cambia en el pedido
	 * @param ref
	 * @param pedido
	 * @return el pedido modificado
	 */
	public Pedido modificarPedido(int ref, Pedido pedido) {
		Pedido pedidoModificar = repositorioPedido.findById(ref).orElse(null);
		pedidoModificar.setEmail(pedido.getEmail());
		pedidoModificar.setEmail(pedido.getDireccion());
		pedidoModificar.setTelefono(pedido.getTelefono());
		repositorioPedido.save(pedidoModificar);
		return pedidoModificar;
		
	}

	
	/**
	 * Metodo para añadir una linea de pedido que recibe como parametro, a esta linea le añadimos un pedido y un producto
	 * @param refPedido
	 * @param idProducto
	 * @param lineaPedido
	 * @return el pedido que ha sido editado añadiendole la linea de pedido
	 */
	public Pedido addLineaPedido(int refPedido, int idProducto, LineaPedido lineaPedido) {
	
		Pedido nuevoPedido1 = repositorioPedido.getById(refPedido);
		nuevoPedido1.lineasPedidos.clear();
		Producto nuevoProducto = repositorioProducto.getById(idProducto);
		lineaPedido.setPedido(nuevoPedido1);
		lineaPedido.setProducto(nuevoProducto);
		Pedido pedidoEditar = repositorioPedido.getById(refPedido);
		lineaPedido.getPedido().lineasPedidos.clear();
		System.out.println(lineaPedido.getPedido().getLineasPedidos().size()+"no se que pasa");
		pedidoEditar.setLineasPedidos(lineaPedido);
		System.out.println(pedidoEditar.getLineasPedidos().size()+"fdsfs");
		repositorioPedido.save(pedidoEditar);
		return pedidoEditar;
		

	}
	
	
	/**
	 * Metodo para crear una nueva linea de pedido
	 * @param cantidad
	 * @param refPedido
	 * @param idProducto
	 * @return la linea de pedido que ha sido creada
	 */
	public LineaPedido crearLineaPedido(int cantidad, int refPedido, int idProducto) {
		
		
		LineaPedido linea = new LineaPedido( repositorioPedido.getById(refPedido), repositorioProducto.getById(idProducto), cantidad);
		this.repositorioLineaPedido.save(linea);
		Pedido pedido = repositorioPedido.getById(refPedido);
		pedido.setLineasPedidos(linea);
		return repositorioLineaPedido.findById(linea.getId()).orElse(null);
	}

	
	/**
	 * Metodo para buscar un pedido por su id
	 * @param refPedido
	 * @return el pedido o null en caso de que no exista
	 */
	public Pedido findById(int refPedido) {
		
		return repositorioPedido.findById(refPedido).orElse(null);
	}
	
	
	/**
	 * Metodo para editar un pedido
	 * @param e
	 * @return 
	 */
	public Pedido edit(Pedido e) {
		return repositorioPedido.save(e);
	}

	
	/**
	 * Metodo para mostrar las lineas de pedido de un pedido concreto
	 * @param refPedido
	 * @return devuelve la linea de pedido o null en caso de que no exista
	 */
	public List<LineaPedido> mostrarLineas(int refPedido) {

		return repositorioPedido.getById(refPedido).getLineasPedidos();
		
	}

	
	/**
	 * Metodo para buscar una linea concreta
	 * @param id
	 * @return la linea o null en caso de que no exista
	 */
	public LineaPedido buscarLinea(Integer id) {
		return repositorioLineaPedido.findById(id).orElse(null);
	}

	/**
	 * Metodo para editar una linea de pedido, se le pasa una linea ya editada y la guardamos
	 * @param resultado
	 * @return 
	 */
	public LineaPedido editLinea(LineaPedido resultado) {
		return repositorioLineaPedido.save(resultado);
		
	}

	
	/**
	 * Metodo para borrar un pedido, le pasamos la ref del pedido y el id del usuario y borramos el pedido y actualizamos el usuairo en la bd
	 * @param refPedido
	 * @param idUsuario
	 */
	public void  borrarPedido(int refPedido, String idUsuario) {
			
		Usuario usuarioNuevo = repositorioUsuario.findById(idUsuario).orElse(null);
		int posicion = usuarioNuevo.getListaPedidos().indexOf(new Pedido(refPedido));
		Pedido pedido =usuarioNuevo.getListaPedidos().get(posicion);
		usuarioNuevo.getListaPedidos().remove(pedido);
//		usuServ.saveUser(usuario);
		Usuario usuarioGuardar = repositorioUsuario.getById(idUsuario);
		usuarioGuardar.setListaPedidos(usuarioNuevo.getListaPedidos());
		repositorioUsuario.save(usuarioGuardar);
	}
	
	/**
	 * Metodo para borrar un pedido, le pasamos la ref del pedido y el id del usuario y borramos el pedido y actualizamos el usuairo en la bd
	 * @param refPedido
	 * @param idUsuario
	 */
	public Pedido  borrarLineaPedido(int refPedido, String idUsuario, int idLinea) {
			
		Usuario usuarioModificar = repositorioUsuario.getById(idUsuario);
		Pedido pedidoModificar = repositorioPedido.getById(refPedido);
		LineaPedido lineaBorrar = repositorioLineaPedido.getById(idLinea);
		
		usuarioModificar.getListaPedidos().remove(pedidoModificar);
		pedidoModificar.getLineasPedidos().remove(lineaBorrar);
		usuarioModificar.getListaPedidos().add(pedidoModificar);
		repositorioUsuario.save(usuarioModificar);
//		repositorioPedido.save(pedidoModificar);
		return pedidoModificar;
	}

	


	

}
