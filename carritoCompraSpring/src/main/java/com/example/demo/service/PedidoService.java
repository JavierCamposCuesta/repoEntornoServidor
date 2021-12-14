package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;


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
	
	private Pedido nuevoPedido = new Pedido();
	
	@PostConstruct
	public void init() {
		
	}
	
	private HashSet<Producto> productosEnCarrito = new HashSet<Producto>();
	
	public void addProducto (Producto productoNuevo) {
		for(Producto producto: productoService.findAll()) {
			//Si el productoId es igual al getId lo que haremos será aumentar la variable cantidad de producto y ademas lo añadiremos al array productosEnCarrito
			if(producto.getId().equals(productoNuevo.getId()) ) {
				for(Producto productoCarrito: productosEnCarrito) {
					//Si el producto es igual al producto en productoCarrito lo que haremos será aumentar la cantidad de productoCarrito
					if(producto.equals(productoCarrito)) {
						productoCarrito.setCantidad(productoCarrito.getCantidad()+1);
					}
				}
				if(!productosEnCarrito.contains(producto)) {
					//Si no ha encontrado el producto en la lista productoCarrito es porque es la primera vez ue se añade, por lo que modifico la cantidad en la lista productos
					//IMPORTANTE si no le indico que es 1 no va a funcionar bien si borro y vuelvo a añadir el producto
					producto.setCantidad(1);
					productosEnCarrito.add(producto);
				}
				
			}
		}
	}
	
	//Devolvemos los productos que estan en el carrito
	public HashSet<Producto> mostrarProductosEnCarrito(){
		return productosEnCarrito;
	}
	
	public void borrarProducto (Producto productoBorrar) {
		productosEnCarrito.remove(productoBorrar);
		
		//Tenia toda esta estructuta, pero con la linea de arriba solo es como mejor esta
		
//		for(Producto producto: productosEnCarrito) {
//			//Si el productoId es igual al getId lo que haremos será aumentar la variable cantidad de producto y ademas lo añadiremos al array productosEnCarrito
//			if(producto.getId().equals(productoBorrar.getId()) ) {
//	
//			}
//		}
	}
	
	//Modificamos las cantidades de los productos que vienen del carrito
	//Modicamos la lista de productos en carrito
	public void modificarCantidades (Integer[] cantidades) {
		int contador=0;
		for (Producto producto: productosEnCarrito ) {
			producto.setCantidad(cantidades[contador]);
			contador++;
		}
	}
	
	//Creamos un nuevo pedido
//	Creamos una constante para la referencia de los pedidos
	public void crearPedido (String metodoPago, Double costeEnvio, String direccion, String telefono, String email) {
		
		nuevoPedido = new Pedido(productosEnCarrito, direccion, telefono, email, costeEnvio, metodoPago);
		System.out.println(productosEnCarrito + "productos");
	}
	
	public Pedido mostrarPedido() {
		
		return nuevoPedido;
	}
	
	
	
	public boolean borrarPedido (Pedido pedidoBorrar, Usuario usuario) {
		return usuario.listaPedidos.remove(pedidoBorrar);
		
	}
	
	public void modificarCantidadesEditar (Integer[] cantidades, Pedido pedidoBuscar, Usuario usuario) {
		int contador=0;
System.out.println(usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).productos);
		for (Producto producto: usuario.listaPedidos.get(usuario.listaPedidos.indexOf(pedidoBuscar)).productos ) {
			producto.setCantidad(cantidades[contador]);
			contador++;
		}
	}

	

}
