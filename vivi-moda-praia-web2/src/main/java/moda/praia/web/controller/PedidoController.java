package moda.praia.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
public class PedidoController {

	
	@RequestMapping(value = "/pagina/finalizar/pedido",method = RequestMethod.GET)
	public String irParafinalizarPedido(){
		
		//logado ir para pagina de finalizar pedido
		
		//nao logado ir para pagina de login
		
		
		return "pages/login-cliente";
	}
}
