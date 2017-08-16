package moda.praia.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class AcessoController {

	@RequestMapping(value="acesso-negado",method = RequestMethod.GET)
	public ModelAndView handleRequest() {

		return new ModelAndView("pages/acesso-negado");
	}
}
