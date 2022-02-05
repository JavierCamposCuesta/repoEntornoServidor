package com.example.demo.error;

public class PedidoNotFoundExeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Recibe una referencia de pedido en el caso de que no exista y muestra el mensaje
	 * @param refPedido
	 */
	public PedidoNotFoundExeption(int refPedido) {
		super("No existe el pedido con ref: "+refPedido);
	}

}
