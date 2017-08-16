package moda.praia.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Produto;

@Controller
@SessionAttributes("nomeUsuario")
public class LoginController {
	
	private String nomeCliente = "";
	@Autowired
	private ClienteBusiness clienteBusiness;
	
	private String getPrincipal() {
		String userName = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}


	@RequestMapping(value = "/carregarClienteLogado", method = RequestMethod.GET)
	@ResponseBody
	public String carregaClienteLogado(Model model) {
		
		if(nomeCliente == null ||nomeCliente.equals("")){
			//String email = "teste";
			String email = getPrincipal();
			Cliente cliente = clienteBusiness.obterClientePorEmail(email);
			if(cliente != null){
				nomeCliente = cliente.getNome();
			}
		}
		return nomeCliente;
	}

	@ModelAttribute("nomeUsuario")
	public String getNomeCliente() {
		
		return nomeCliente;
	}


}
