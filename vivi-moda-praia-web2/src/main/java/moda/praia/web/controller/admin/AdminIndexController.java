package moda.praia.web.controller.admin;

import moda.praia.modulo.produtos.bean.Produto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminIndexController {
	
	@RequestMapping(value = "/admin-index", method = RequestMethod.GET)
	public String handleRequest(Model model) {

		return "admin/template-admin";
		
	}

}
