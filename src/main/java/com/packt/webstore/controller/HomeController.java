package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * The controller class for the welcome.jsp file
 * The class supports website with home page
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@Controller
public class HomeController {

	/** The controller method for displaying home page * */
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("greeting", "Witaj w sklepie internetowym!");//greeting - argument przekazywany do metody addAttribute
																		//mamy do jego wartości dostęp w widoku jsp
		model.addAttribute("tagline", "Wyjątkowym i jedynym sklepie internetowym");
		
		return "welcome";
	}
}
