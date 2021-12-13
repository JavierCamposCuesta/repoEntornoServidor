package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	
//	@GetMapping("/")
//	public String welcome(Model model) {
//		model.addAttribute("mensaje", "Ola ompare k ase");
//		return "Index";
//	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="world") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	
	@GetMapping("/greeting/{name}")
	public String greetingPath(@PathVariable(name="name", required=false) String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	
	
	
}
