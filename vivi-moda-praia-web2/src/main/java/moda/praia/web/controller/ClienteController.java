package moda.praia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;

@Controller
@SessionAttributes("nomeUsuario")
public class ClienteController {

	@Autowired
	private ClienteBusiness clienteBusiness;
	private String nomeUsuario;

	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@RequestMapping(value = "/dados-pessoais", method = RequestMethod.GET)
	public String dadosPessoaisCliente(Model model) {

		String email = getPrincipal();
		Cliente cliente = clienteBusiness.obterClientePorEmail(email);

		if (cliente != null) {

			model.addAttribute("cliente", cliente);
			return "pages/dados-pessoais";
		}

		return "redirect:home";
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:home";
	}

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

	@ModelAttribute("nomeUsuario")
	public String getNomeUsuario() {
		
		if(nomeUsuario != null && !nomeUsuario.equals("")){
			String email = getPrincipal();
			Cliente cliente = clienteBusiness.obterClientePorEmail(email);
			nomeUsuario = cliente.getNome();
		}
		
		return nomeUsuario;
	}


}
