package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainControllerWebEjemplo {

	
	
	
	//EJEMPLO WEB PARA PRACTICAR
	@GetMapping("/Index")
	public String index(Model model) {
		model.addAttribute("mensaje", "Ola ompare k ase");
		return "Index";
	}
	
	@GetMapping("/que")
	public String que(Model model) {
		model.addAttribute("mensajeQue", "Ya has pasado por el que tu ere weno");
		return "que";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
		model.addAttribute("mensajeContacto", "Ya has superado todas las pruebas, estas en contacto, eres el puto amo");
		return "contacto";
	}
}
