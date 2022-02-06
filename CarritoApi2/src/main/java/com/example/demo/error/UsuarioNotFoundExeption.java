package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundExeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 926601293705427666L;

	/**
	 * 
	 */
	
	
	
	/**
	 * Recibe un nickname de usuario en el caso de que no exista y devuelve el mensaje
	 * @param idUsuario
	 */
	public UsuarioNotFoundExeption(String idUsuario) {
		super("No existe el usuario con nickname: "+idUsuario);
	}

}
