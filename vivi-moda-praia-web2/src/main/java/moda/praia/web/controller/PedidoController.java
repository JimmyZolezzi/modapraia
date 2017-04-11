package moda.praia.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moda.praia.modulo.clientes.bean.Cliente;

@Controller
@Transactional
public class PedidoController {

	
	@RequestMapping(value = "/finalizar-pedido",method = RequestMethod.GET)
	public String irParafinalizarPedido(){
		
		
		return "pages/login-cliente";
	}
	
	
}
