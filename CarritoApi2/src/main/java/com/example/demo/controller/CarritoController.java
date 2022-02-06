package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
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
public class CarritoController {
	
	
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
	 * Mostrar  productos de la bd por id
	 * @return un json el producto en caso de que existaproductos, si no existe devulve una exepción
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
		 * Mostrar todos los productos de la bd
		 * @return un json con la lista de productos
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
	 * Muestra todos los pedidos de la bd
	 * @return un json con la lista de pedidos, si no hay devuelve una exepcion
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
		 * @return un json con el pedido en caso de que exista, si no devuelve una exepcion
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
		 * @return el pedido modificado en caso de que existiese, si no devulve una exepcion
		 */
		@PostMapping("/pedido/{ref}")
		public ResponseEntity<Pedido>modificarPedido(@PathVariable(value="ref")int ref, @RequestBody Pedido pedido){
			Pedido pedidoBuscar = pedidoService.findById(ref);
			if(pedidoBuscar == null) {
				throw new PedidoNotFoundExeption(ref);
			}
			else {
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.modificarPedido(ref, pedido));
			}
				
				
		}
		
		/**
		 * Añadir Linea de pedido, le pasaremos una ref de pedido y una linea de pedido y actualizaremos todo
		 * Si el pedido o el producto no existe devolverá una exepcion
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
		 * Si no existe el pedido devolverá una exepcion
		 * @return el pedido modificado
		 */
		@GetMapping("/pedido/{refPedido}/lineaPedido")
		public ResponseEntity<List<LineaPedido>> verLineasPedido(@PathVariable(value="refPedido")int refPedido){
			Pedido respuesta = pedidoService.findById(refPedido);
			if(respuesta == null) {
				throw new PedidoNotFoundExeption(refPedido);
			}
			else {
				
				return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.mostrarLineas(refPedido));
				}
		}
		
		/**
		 * Este metodo recibirá una ref de pedido , un  id de linea de pedido y una linea de pedido ya editada
		 * En caso de no existir el pedido o la linea devolvera una exepcion correspondiente
		 * @param ref se corresponde con la ref de pedido
		 * @param id Se corresponde con el id de la linea de pedido
		 * @param linea Le pasamos un json que se corresponda con la nueva linea de pedido, constará del producto y de las cantidades
		 * @return la linea de pedido ya editada
		 */
		@PutMapping("/pedido/{refPedido}/lineaPedido/{id}")
		public ResponseEntity<LineaPedido> editarLinea(@PathVariable Integer refPedido, @PathVariable Integer id, @RequestBody LineaPedido linea){
			Pedido pedidoBuscar = pedidoService.findById(refPedido);
			LineaPedido resultado = this.pedidoService.buscarLinea(id);
			if(pedidoBuscar == null) {
				throw new PedidoNotFoundExeption(refPedido);
			}
			else if(resultado == null) {
				throw new LineaPedidoNotFoundException(id);
			}else {
				resultado.setProducto(linea.getProducto());
				resultado.setCantidad(linea.getCantidad());
				this.pedidoService.editLinea(resultado);
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
		}
		
		
		/**
		 * Metodo para borrar pedido, le pasamos una ref de pedido y un json
		 * con un usuario, al cual le pertenece el pedido. En caso que no existe el pedido o el usuario devulve una exeption
		 * @param ref
		 * @param usuario
		 * @return el pedido que ha borrado
		 */
		@DeleteMapping("/pedido/{ref}")
		public ResponseEntity<?> borrarPedido(@PathVariable Integer ref,@RequestBody Usuario usuario){
			
			Pedido pedido = pedidoService.findById(ref);
			Usuario usuarioBuscar = usuarioService.findById(usuario.getNickName());
			if(pedido == null) {
				throw new PedidoNotFoundExeption(ref);
			}
			else if(usuarioBuscar == null) {
				throw new UsuarioNotFoundExeption(usuario.getNickName());
			}
			else {
				pedidoService.borrarPedido(ref, usuario.getNickName());
				return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
			}
			
		}
		
		/**
		 * Metodo para borrar linea de pedido, le pasamos una ref de pedido y un id de linea por url, ademas le pasamos un json
		 * con un usuario, al cual le pertenece el pedido. En caso que no existe el pedido o la linea devuelve una exeption
		 * @param ref
		 * @param usuario
		 * @return el pedido que ha borrado
		 */
		@DeleteMapping("/pedido/{ref}/lineaPedido/{idLinea}")
		public ResponseEntity<?> borrarLineaPedido(@PathVariable Integer ref, @PathVariable Integer idLinea, @RequestBody Usuario usuario){
			
			Pedido pedido = pedidoService.findById(ref);
			Usuario usuarioBuscar = usuarioService.findById(usuario.getNickName());
			LineaPedido lineaBuscar = pedidoService.buscarLinea(idLinea);
			if(pedido == null) {
				throw new PedidoNotFoundExeption(ref);
			}
			else if(usuarioBuscar == null) {
				throw new UsuarioNotFoundExeption(usuario.getNickName());
			}
			else if(lineaBuscar == null) {
				throw new LineaPedidoNotFoundException(idLinea);
			}
			else {
				pedidoService.borrarLineaPedido(ref, usuario.getNickName(), idLinea);
				return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
			}
			
		}
		
		@ExceptionHandler(ProductoNotFoundExeption.class)
		public ResponseEntity<ApiError> handleProductoNoEncontrado(ProductoNotFoundExeption ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}
		
		@ExceptionHandler(PedidoNotFoundExeption.class)
		public ResponseEntity<ApiError> handleProductoNoEncontrado(PedidoNotFoundExeption ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}
		
		@ExceptionHandler(UsuarioNotFoundExeption.class)
		public ResponseEntity<ApiError> handleProductoNoEncontrado(UsuarioNotFoundExeption ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}
		
		@ExceptionHandler(LineaPedidoNotFoundException.class)
		public ResponseEntity<ApiError> handleProductoNoEncontrado(LineaPedidoNotFoundException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}

	
	

}
