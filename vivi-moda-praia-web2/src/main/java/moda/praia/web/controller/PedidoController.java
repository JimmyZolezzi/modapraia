package moda.praia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import moda.praia.modulo.carrinho.CarrinhoBusiness;
import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.endereco.Endereco;
import moda.praia.modulo.endereco.EnderecoBusiness;
import moda.praia.modulo.estoque.DisponibilidadeEstoque;
import moda.praia.modulo.estoque.exceptions.ProdutoIndisponivelException;
import moda.praia.modulo.estoque.exceptions.SaveReservaProdutoException;
import moda.praia.modulo.login.LoginCliente;
import moda.praia.modulo.pedido.PedidoBusiness;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.StatusPedido;
import moda.praia.web.controller.form.FormEnderecoCliente;
import moda.praia.web.controller.form.FormLogin;

@Controller
@Transactional
public class PedidoController {

	@Autowired
	private CarrinhoBusiness carrinhoBusiness;
	@Autowired
	private ClienteBusiness clienteBusiness;
	@Autowired
	private EnderecoBusiness enderecoBusiness;
	@Autowired
	private PedidoBusiness pedidoBusiness;
	
	@RequestMapping(value = "/confirmar-dados",method = RequestMethod.GET)
	public String irParafinalizarPedido(Model model, @RequestParam(value = "erro", required = false) String erro){
		
		if(erro != null){
			model.addAttribute("erro", erro);
		}
		
		String email = getPrincipal();
		if(email !=null){
			Cliente cliente = clienteBusiness.obterClientePorEmail(email);
			Pedido pedido = carrinhoBusiness.obterPedidoCarrinho();
			if(cliente != null && pedido != null){
				pedido.setCliente(cliente);
				boolean calcularFrete = false;
				if(cliente.getEnderecoEntrega() != null){
				
					Endereco enderecoEntrega = cliente.getEnderecoEntrega();
					if(enderecoEntrega!=null){
						pedido.setEnderecoEntrega(enderecoEntrega);
						calcularFrete = true;
					}

				}else{
					
					Endereco enderecoCliente = cliente.getEnderecoCliente();
					if(enderecoCliente!=null){
						pedido.setEnderecoEntrega(enderecoCliente);
						calcularFrete = true;
					}
				}
				
				if(pedido.getEnderecoEntrega()==null){
					Endereco endereco = new Endereco();
					FormEnderecoCliente formEnderecoCliente = new FormEnderecoCliente();
					formEnderecoCliente.setEndereco(endereco);
					model.addAttribute("formEnderecoCliente", formEnderecoCliente);
					
				}
				
				if(pedido.getEnderecoEntrega()!=null && pedido.getEnderecoEntrega().getCep() != null && calcularFrete){
					String cep = pedido.getEnderecoEntrega().getCep();
					carrinhoBusiness.calcularFretePedido(cep); 
				}
				model.addAttribute("pedido", pedido);
				return "pages/confirmacao-dados-pedido";
			}
			
		}

		FormLogin formLogin = new FormLogin();
		formLogin.setPaginaDestino("/confirmar-dados");
		formLogin.setPaginaAtual("pages/componentes/form-login");
		model.addAttribute("formLogin", formLogin);
		
		return "pages/login-cliente";
	}
	
	@RequestMapping(value = "/finalizar-pedido",method = RequestMethod.GET)
	public String finalizarDadosPedido(Model model){
		
		Pedido pedido = carrinhoBusiness.obterPedidoCarrinho();
		try{
			
			boolean efetivado = pedidoBusiness.finalizarPedido(pedido);
			
			if(efetivado){
				model.addAttribute("pedido", pedido);
				return "pages/pedido-finalizado";
			}
			
		} catch (ProdutoIndisponivelException e) {
			
			model.addAttribute("erro", DisponibilidadeEstoque.QUANTIDADE_PRODUTO_INDISPONIVEL);
		
		} catch (SaveReservaProdutoException e) {
			model.addAttribute("erro", "ERRO_AO_SALVAR_PEDIDO");

		}catch(Exception e){
			model.addAttribute("erro", DisponibilidadeEstoque.ERRO);
			
		}
		
		return "redirect:confirmar-dados";
	}
	
	@RequestMapping(value = "/busca/endereco/pedido/cep",method = RequestMethod.GET)
	public String buscaEnderecoPorCEP(@RequestParam("cep") String cep,@RequestParam("idCliente") String idCliente,Model model){
		
		FormEnderecoCliente formEnderecoCliente = new FormEnderecoCliente();
		Endereco endereco = new Endereco();
		formEnderecoCliente.setEndereco(endereco);
		if(cep!= null && !cep.equals("")){
			endereco  = enderecoBusiness.buscarEndereco(cep);
			formEnderecoCliente.setEndereco(endereco);
			model.addAttribute("formEnderecoCliente", formEnderecoCliente);
			
		}
		
		if(idCliente != null && idCliente.matches("[0-9].*")){
			long idClienteLong = Long.valueOf(idCliente);
			formEnderecoCliente.setIdCliente(idClienteLong);
		}
		return "pages/componentes/form-endereco";
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
	
	
}
