package ar.edu.unju.fi.tp9.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp9.model.Beneficio;
import ar.edu.unju.fi.tp9.model.Cliente;
import ar.edu.unju.fi.tp9.service.IBeneficioService;

@Controller
public class BeneficioController {

	@Autowired
	private Beneficio beneficio;
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("beneficioServiceMysql")
	private IBeneficioService beneficioService;
	
	@GetMapping("/beneficio/buscar")
	public String buscarBeneficio(@RequestParam(name = "id")long id, Model model, @ModelAttribute(name = "cliente")Cliente cliente) {
		Optional<Beneficio> beneficioEncontrado = beneficioService.getBeneficioPorId(id);
		String mensaje = "";
		if(beneficioEncontrado != null) {
			beneficioService.agregarBeneficioEncontrado(beneficioEncontrado.get());
		}
		else {
			mensaje = "No existe el beneficio";
			model.addAttribute("mensaje",mensaje);
		}
		model.addAttribute("beneficioEncontrado", beneficioService.obtenerBeneficiosEncontrados());
		return "nuevocliente";
	}
	
	@GetMapping("/beneficio/quitar/{id}")
	public ModelAndView quitarBeneficio(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("nuevocliente");
		beneficioService.quitarBeneficioLista(id);
		model.addObject("cliente", cliente);
		model.addObject("cliente", beneficio);
		model.addObject("beneficosEncontrados", beneficioService.obtenerBeneficiosEncontrados());
		return model;
	}
}
