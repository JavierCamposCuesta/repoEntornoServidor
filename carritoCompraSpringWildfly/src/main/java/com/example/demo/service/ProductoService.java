package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;


import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;


@Service  
public class ProductoService {
	/**
	 * Coleccion de productos
	 * get by id
	 * get all
	 */

	
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	@PostConstruct
	public void init() {
		
		/**
		 * De esta forma cremaos todos los productos
		 */
		productos.addAll(Arrays.asList(
				new Producto("1", "NJSJ Altavoces PC", "Altavoz 10W ALTAVOZ 2.0 USB GAMING sobremesa, con luces led que cambian de color ajustable", 20.5, "/imagenes/altavoz.jpg", 0),
				new Producto("2", "HyperX HX-HSCF Cascos", "Cascos Gaming inalámbricos, sonido 5.1 envolvente, 100% Gaming, sonido perfecto para juegos", 104.13, "/imagenes/cascos.jpg", 0),
				new Producto("3", "Exco-Alfombrilla Gaming", "Alfombrilla antidezlizante de goma, gruesa, perfecto movimiento del raton, sugeción articular", 11.9, "/imagenes/alfombrilla.jpg", 0),
				new Producto("4", "Mesa Gaming MGD", "Mesa ergonómica Gaming fabricada en fibra de carbono, patas de aluminio, maxima resistencia", 114.56, "/imagenes/mesa.jpg", 0),
				new Producto("5", "MSI Teclado Gaming", "Teclado mecánico Gaming RGB, máxima rapidez, tacto inmejorable, dureza ajustable colores cambiantes", 28.92, "/imagenes/teclado.jpg", 0),
				new Producto("6", "MSI Optix MAG30", "Monitor Plano Gaming 29.5 pulgadas 200HZ, máxima calidad, distintos puertos de entrada, brillo ajustable", 446.10, "/imagenes/pantalla.jpg", 0)));
	}
	
	/**
	 * Lista para devolver todos los productos
	 * @return lista de prodcutos
	 */
	public ArrayList<Producto> findAll(){
		return productos;
	}
	
	
	
}
