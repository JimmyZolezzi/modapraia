package moda.praia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.pedido.PedidoBusiness;
import moda.praia.modulo.pedido.bean.Pedido;

@Controller
public class MeusPedidosController {
	
	@Autowired
	private PedidoBusiness pedidoBusiness;
	@Autowired
	private ClienteBusiness clienteBusiness;
	
	@RequestMapping(value = "/meus-pedidos",method = RequestMethod.GET)
	public String visualizarMeusPedidos(Model model){
		
		String email = getPrincipal();
		Cliente cliente = clienteBusiness.obterClientePorEmail(email);
		
		Integer pagina = 1;
		Page<Pedido> page = pedidoBusiness.buscarPedidosPorCliente(cliente, pagina);
		model.addAttribute("page", page);
		modePagination(page, model);
		
		return "pages/meus-pedidos";
	}
	
	@RequestMapping(value = "/meus-pedido/{idPedido}",method = RequestMethod.GET)
	public String visualizarPedido(Model model, @PathVariable Long idPedido){
		
		Pedido pedido = pedidoBusiness.bucarPedidoPorId(idPedido);
		
		model.addAttribute("pedido", pedido);
		
		return "pages/pedido-finalizado";
		
	}
	
	@RequestMapping(value = "/meus-pedidos/pages/{pageNumber}",  method = RequestMethod.GET)
	public String visualizarMeusPedidos(@PathVariable Integer pageNumber, Model model){
	
		String email = getPrincipal();
		Cliente cliente = clienteBusiness.obterClientePorEmail(email);
		
		Page<Pedido> page = pedidoBusiness.buscarPedidosPorCliente(cliente, pageNumber);
		model.addAttribute("page", page);
		modePagination(page, model);
		
		return "pages/meus-pedidos";
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
	
	private void modePagination(Page<Pedido> page, Model model){
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());

	    model.addAttribute("pedidoPage", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
		
	}

}
