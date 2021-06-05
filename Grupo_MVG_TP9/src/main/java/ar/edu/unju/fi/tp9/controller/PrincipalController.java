package ar.edu.unju.fi.tp9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

	@GetMapping("/principal")
	public String getHomePage() {
		return "index";
	}
}
