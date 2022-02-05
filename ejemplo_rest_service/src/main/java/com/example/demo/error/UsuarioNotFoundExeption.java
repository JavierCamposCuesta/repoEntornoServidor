package com.example.demo.error;

public class UsuarioNotFoundExeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Recibe un nickname de usuario en el caso de que no exista y devuelve el mensaje
	 * @param idUsuario
	 */
	public UsuarioNotFoundExeption(String idUsuario) {
		super("No existe el usuario con nickname: "+idUsuario);
	}

}
