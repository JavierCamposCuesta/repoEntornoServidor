package com.example.demo.error;

public class ProductoNotFoundExeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * recibe un id de producto en el caso de que no exista y devulve el mensaje
	 * @param idProducto
	 */
	public ProductoNotFoundExeption(int idProducto) {
		super("No existe el producto con id: "+idProducto);
	}

}
