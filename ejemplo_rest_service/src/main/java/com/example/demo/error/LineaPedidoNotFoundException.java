package com.example.demo.error;

public class LineaPedidoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * devolverá un mensaje si la linea de pedido con el id indicado no exite
	 * @param recibe el id de la linea de pedido
	 */
	public LineaPedidoNotFoundException(int id) {
		super("No existe la linea de pedido con id: "+id);
	}

}
