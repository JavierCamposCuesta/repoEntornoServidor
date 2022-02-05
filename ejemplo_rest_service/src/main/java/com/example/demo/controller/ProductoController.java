package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.LineaPedidoNotFoundException;
import com.example.demo.error.PedidoNotFoundExeption;
import com.example.demo.error.ProductoNotFoundExeption;
import com.example.demo.error.UsuarioNotFoundExeption;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@RestController
public class ProductoController {
	
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoService pedidoService;
	

	
/**
 * Consultar todos los usuarios de la bd
 * @return un json con la lista de usuarios
 */
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario>listaUsuarios = this.usuarioService.findAll();
		if(listaUsuarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(listaUsuarios);
		}
	}
	
	/**
	 * Consultar usuario por id
	 * @return un json con el usuario
	 */
		@GetMapping("/usuarios/{idUsuario}")
		public ResponseEntity<Usuario> darUsuario(@PathVariable(value="idUsuario")String idUsuario) {
			Usuario usuario = usuarioService.findById(idUsuario);
			if(usuario==null) {
				throw new UsuarioNotFoundExeption(idUsuario);
			}
			else {
				return ResponseEntity.ok(usuario);
			}
		}
	
	
	/**
	 * Mostrar productos de la bd
	 * @return un json con la lista de usuarios
	 */
		@GetMapping("/productos/{idProducto}")
		public ResponseEntity<Producto> darProducto(@PathVariable(value="idProducto")int idProducto) {
			Producto producto = productoService.findById(idProducto);
			if(producto == null) {
				throw new ProductoNotFoundExeption(idProducto);
			}
			else {
				return ResponseEntity.ok(producto);
			}
		}
		
		/**
		 * Mostrar producto por id
		 * @return un json con la lista de usuarios
		 */
			@GetMapping("/productos")
			public ResponseEntity<List<Producto>> listaProductos() {
				List<Producto>listaProductos = this.productoService.findAll();
				if(listaProductos.isEmpty()) {
					return ResponseEntity.notFound().build();
				}
				else {
					return ResponseEntity.ok(listaProductos);
				}
			}
	
	/**
	 * Mostrar pedidos de la bd
	 * @return un json con la lista de usuarios
	 */
		@GetMapping("/pedidos")
		public ResponseEntity<List<Pedido>> listaPedidos() {
			List<Pedido>listaPedidos = this.pedidoService.findAll();
			if(listaPedidos.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			else {
				return ResponseEntity.ok(listaPedidos);
			}
		}
		
		/**
		 * Mostrar pedido de la bd por id
		 * @return un json con el pedido
		 */
		@GetMapping("/pedidos/{ref}")
		public ResponseEntity<Pedido> darPedido(@PathVariable(value="ref")int ref) {
			Pedido pedido = pedidoService.findById(ref);
			if(pedido == null) {
				throw new PedidoNotFoundExeption(ref);
			}
			else {
				return ResponseEntity.ok(pedido);
			}
		}
		
		
		/**
		 * Add pedido
		 * @return el nuevo pedido que ha añadido
		 */
		@PostMapping("/pedido")
		public ResponseEntity<Pedido>crearPedido(@RequestBody Usuario usuario){
			Usuario result = this.usuarioService.findById(usuario.getNickName());
			

				Pedido nuevoPedido = this.pedidoService.crearPedido();
				result.addPedido(nuevoPedido); 
				this.usuarioService.edit(result);
				return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
		}
		
		/**
		 * Modificar pedido, le pasaremos una ref de pedido y un pedido con los datos que queremos modificarle al nuevo pedido
		 * @return el pedido modificado
		 */
		@PostMapping("/pedido/{ref}")
		public ResponseEntity<Pedido>modificarPedido(@PathVariable(value="ref")int ref, @RequestBody Pedido pedido){
			
				
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.modificarPedido(ref, pedido));
		}
		
		/**
		 * Añadir Linea de pedido, le pasaremos una ref de pedido y una linea de pedido y actualizaremos todo
		 * @return el pedido modificado
		 */
		@PostMapping("/pedido/{refPedido}/{idProducto}")
		public ResponseEntity<Pedido>addLineaPedido(@PathVariable(value="refPedido")int refPedido, @PathVariable(value="idProducto")int idProducto, @RequestBody LineaPedido lineaPedido){
			Pedido respuesta = pedidoService.findById(refPedido);
			if(respuesta == null) {
				throw new PedidoNotFoundExeption(refPedido);
			}
			else if(productoService.findById(idProducto)==null) {
				throw new ProductoNotFoundExeption(idProducto);
			}
			else {
				pedidoService.crearLineaPedido(lineaPedido.getCantidad(), refPedido, idProducto);
				
//				Pedido pedidoRespuesta = pedidoService.addLineaPedido(refPedido, idProducto, lineaPedido);
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.edit(respuesta));
				}
		}
		
		/**
		 * Mostrar Linea de pedido, le pasaremos un id pedido  y nos devolverá todas las lineas de ese pedido
		 * @return el pedido modificado
		 */
		@GetMapping("/verLineasPedido/{refPedido}")
		public ResponseEntity<LineaPedido> verLineasPedido(@PathVariable(value="refPedido")int refPedido){
			Pedido respuesta = pedidoService.findById(refPedido);
			if(respuesta == null) {
				throw new PedidoNotFoundExeption(refPedido);
			}
			else {
				
				
//				Pedido pedidoRespuesta = pedidoService.addLineaPedido(refPedido, idProducto, lineaPedido);
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.mostrarLineas(refPedido));
				}
		}
		
		/**
		 * Este metodo recibirá un id de linea de pedido y una linea de pedido ya editada
		 * @param id Se corresponde con el id de la linea de pedido
		 * @param linea Le pasamos un json que se corresponda con la nueva linea de pedido, constará del producto y de las cantidades
		 * @return
		 */
		@PutMapping("/editarLineaPedido/{id}")
		public ResponseEntity<LineaPedido> editarLinea(@PathVariable Integer id, @RequestBody LineaPedido linea){
			
			LineaPedido resultado = this.pedidoService.buscarLinea(id);
			if(resultado == null) {
				throw new LineaPedidoNotFoundException(id);
			}else {
				resultado.setProducto(linea.getProducto());
				resultado.setCantidad(linea.getCantidad());
				this.pedidoService.editLinea(resultado);
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
		}
		
		
		/**
		 * Este metodo recibirá una ref de pedido y un id de usuario y borrará el pedido del usuario y actualizará tyodo
		 */
		@GetMapping("/borrarPedido/{ref}/{idUsuario}")
		public ResponseEntity<?> borrarPedido(@PathVariable Integer ref,@PathVariable String idUsuario){
			
			Pedido pedido = pedidoService.findById(ref);
			if(pedido == null) {
				throw new PedidoNotFoundExeption(ref);
			}else {
				pedidoService.borrarPedido(ref, idUsuario);
				return ResponseEntity.noContent().build();
			}
			
		}

	
	

}
