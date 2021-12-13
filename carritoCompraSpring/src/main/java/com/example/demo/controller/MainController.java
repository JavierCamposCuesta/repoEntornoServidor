package com.example.demo.controller;




import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller 
public class MainController {


	
	
	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoService pedidoService;
	
	
//	###### IR AL INICIO
	@GetMapping("/inicio")
	public String listaUsuario(Model model) {
	
		model.addAttribute("usuario", new Usuario());
		return "inicio";
	}
	
	
//	###### IR AL CATALOGO
	//Llego a este post a traves del boton, y aqui se comprobará que el usuario es correcto
	//Si es correcto se enviará al get de inicio/catalogo
	//Si es incorrecto se devolverá al inicio
	@PostMapping("inicio/catalogo") 
	public String comprobarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
		
		
		
		
		//Con el nuevo usuario que creamos para hacer la comprobacion comprobamos a traves del metodo del servicio si el usuario existe, en cuyo caso lo mandaremos al catalogo
		if(usuarioService.comprobarUsuario(usuario)) {
//			Creamos un atributo LOGUEADO para ir comprobando si el usuario se encuentra o no Logueado
			sesion.setAttribute("LOGUEADO", true);
			sesion.setAttribute("usuario", usuarioService.darUsuario(usuario));
			return "redirect:/inicio/catalogo";
		}
		else {
			sesion.setAttribute("LOGUEADO", false);
			return "redirect:";
		}
	}
	
	@GetMapping("inicio/catalogo")
	public String catalogo(Model model) {
		
		model.addAttribute("productos", productoService.findAll());
		String respuesta = "inicio";
		if(sesion.getAttribute("LOGUEADO") != null && (boolean) sesion.getAttribute("LOGUEADO")) {
			//Si existe la sesion y el usuario esta logueado lo mandamos al catalogo
			respuesta = "catalogo";
		}
		else {
			//Si el usuario no esta logado lo mandamos al getMapping del inicio, para que se loguee
			respuesta = "redirect:";
		}
		
			return respuesta;
		
	}
	
	//Añadir producto
	
	@GetMapping("inicio/addProducto")
	public String addProducto(Model model) {

			return "redirect:";
		
	}

	
	@PostMapping("inicio/addProducto") 
	public String addProducto(@ModelAttribute("productoAdd") Producto productoAdd) {
	
			pedidoService.addProducto(productoAdd);
			return "redirect:/inicio/catalogo";
		
	}
	
	
	//######## IR AL CARRITO
	@GetMapping("inicio/carrito")
	public String carrito(Model model) {

		model.addAttribute("productosEnCarrito", pedidoService.mostrarProductosEnCarrito());
		String respuesta = "inicio";
		if(sesion.getAttribute("LOGUEADO") != null && (boolean) sesion.getAttribute("LOGUEADO")) {
			//Si existe la sesion y el usuario esta logueado lo mandamos al catalogo
			respuesta = "carrito";
		}
		else {
			//Si el usuario no esta logado lo mandamos al getMapping del inicio, para que se loguee
			respuesta = "redirect:";
		}
		
			return respuesta;
		
	}

	
	@PostMapping("inicio/carrito") 
	public String carrito() {

		return "redirect:/inicio/carrito";
		
	}
	
	
	//Borrar producto del resumen de pedido
	//La id la mandamos al cliclar un enlace, y viaje en la url, por lo que solo necesitamos un get
	@GetMapping("inicio/borrarProducto/{id}")
	public String borrarProducto(@PathVariable(value="id")String idProducto) {

		String respuesta = "inicio";
		if(sesion.getAttribute("LOGUEADO") != null && (boolean) sesion.getAttribute("LOGUEADO")) {
			Producto productoBorrar = new Producto(idProducto);
			pedidoService.borrarProducto(productoBorrar);
			respuesta = "redirect:/inicio/carrito/";
		}
		else {
			//Si el usuario no esta logado lo mandamos al getMapping del inicio, para que se loguee
			respuesta = "redirect:";
		}
		
			return respuesta;
		
	}

	
	
	//######## IR A LA FACTURA
	@GetMapping("inicio/factura")
	public String factura(Model model) {
		

		model.addAttribute("pedido", pedidoService.mostrarPedido());
		model.addAttribute("usuario", sesion.getAttribute("usuario"));
		String respuesta = "inicio";
		if(sesion.getAttribute("LOGUEADO") != null && (boolean) sesion.getAttribute("LOGUEADO")) {
			//Si existe la sesion y el usuario esta logueado lo mandamos a la factura
//			System.out.println(pedidoService);
			respuesta = "factura";
		}
		else {
			//Si el usuario no esta logado lo mandamos al getMapping del inicio, para que se loguee
			respuesta = "redirect:";
		}
		
			return respuesta;
		
	}

	
	@PostMapping("inicio/factura") 
	public String factura(
			Model model,
			@RequestParam(name= "costeEnvio") Double costeEnvio,
			@RequestParam(name= "metodoPago") String metodoPago,
			@RequestParam(name= "direccion") String direccion,
			@RequestParam(name= "telefono") String telefono,
			@RequestParam(name= "email") String email,
			@RequestParam(name= "cantidad") Integer[] cantidades
			) {
		
		pedidoService.modificarCantidades(cantidades);
		pedidoService.crearPedido(metodoPago, costeEnvio, direccion, telefono, email);
		System.out.println(pedidoService.mostrarPedido() + "fNo funciona");
		System.out.println(pedidoService.mostrarProductosEnCarrito().size() + "fNo funciona");
		
		

		return "redirect:/inicio/factura";
		
	}
	
	
	
	//######## AÑADIR PEDIDOS
	@GetMapping("inicio/addPedido")
	public String addPedido(Model model) {
		

		model.addAttribute("pedido", pedidoService.mostrarPedido());
		String respuesta = "inicio";
		if(sesion.getAttribute("LOGUEADO") != null && (boolean) sesion.getAttribute("LOGUEADO")) {
			//Si existe la sesion y el usuario esta logueado lo mandamos a la factura
			 usuarioService.darUsuario(sesion.getAttribute("usuario")).addPedido(pedidoService.mostrarPedido());
			 System.out.println(usuarioService.darUsuario(sesion.getAttribute("usuario")).getListaPedidos());
			 model.addAttribute("listaPedidos", usuarioService.darUsuario(sesion.getAttribute("usuario")).getListaPedidos());
			
			respuesta = "pedidos";
		}
		else {
			
			//Si el usuario no esta logado lo mandamos al getMapping del inicio, para que se loguee
			respuesta = "redirect:";
		}
		
			return respuesta;
		
	}

	
	@PostMapping("inicio/addPedido") 
	public String addPedido() {

		return "redirect:/inicio/addPedido";
		
	}
	
	
	
	//TODO cuando pulso el boton de añadir tengo que crear el nuevo producto en el getmapping
	
	
}
