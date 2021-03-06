package moda.praia.web.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.ClienteBusinessImpl;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.exceptions.ClienteJahCadastradoException;
import moda.praia.modulo.endereco.Endereco;
import moda.praia.modulo.endereco.EnderecoBusiness;
import moda.praia.web.controller.editor.DateddMMyyyyEditor;
import moda.praia.web.controller.form.FormCliente;
import moda.praia.web.controller.form.FormEnderecoCliente;
import moda.praia.web.controller.validator.ClienteFisicoFormValidator;
import moda.praia.web.controller.validator.EnderecoFormValidator;

@Controller
@Transactional
@SessionAttributes("formEnderecoCliente")
public class CadastroClienteController {
	
	private final Logger log = Logger.getLogger(CadastroClienteController.class);
	
	@Autowired
	private ClienteFisicoFormValidator clienteFisicoFormValidator;
	@Autowired
	private EnderecoFormValidator enderecoFormValidator;
	@Autowired
	private DateddMMyyyyEditor datedEditor;
	@Autowired
	private ClienteBusiness clienteBusiness;
	@Autowired
	private EnderecoBusiness enderecoBusiness;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, datedEditor);
	}
	
	@RequestMapping(value = "/cadastro-cliente",method = RequestMethod.GET)
	public String carregarPaginaCadastroCliente(Model model,HttpSession httpSession){
		model.addAttribute("formEnderecoCliente", new FormEnderecoCliente());
		
		populateFormCliente(model);
		
		return "pages/cadastro-cliente";
	}

	
	@RequestMapping(value = "/busca/endereco/cep",method = RequestMethod.GET)
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
		return "pages/componentes/form-cadastro-endereco-cliente";
	}
	
	@RequestMapping(value="voltar/cadastro/cliente",method = RequestMethod.GET)
	public String voltarCadastroCliente(@ModelAttribute("formEnderecoCliente") FormEnderecoCliente formEnderecoCliente, Model model){
	
		if(formEnderecoCliente != null){

			long idCliente = formEnderecoCliente.getIdCliente();
			if(idCliente !=0){
				
				Cliente cliente  = clienteBusiness.obterCliente(idCliente);
				clienteBusiness.removerCliente(cliente);
				FormCliente formCliente = new FormCliente();
				formCliente.setCliente(cliente);
				model.addAttribute("formCliente", formCliente);
				model.addAttribute("formEnderecoCliente", formEnderecoCliente);
				
				return "pages/componentes/form-cadastro-cliente-fisico";
			}
			
			populateFormEnderecoCliente(model);
			
		}
		return "pages/componentes/form-cadastro-endereco-cliente";
	}

	@RequestMapping(value="/finaliza/cadastramento/cliente",method = RequestMethod.POST)
	public String finalizaCadastramento(@ModelAttribute("formEnderecoCliente") FormEnderecoCliente formEnderecoCliente, Model model, BindingResult result){
	
		enderecoFormValidator.validate(formEnderecoCliente, result);
		long idCliente = formEnderecoCliente.getIdCliente();
	
		if(!result.hasErrors() && idCliente !=0){
		
			boolean enderecoAtualizado = false;
			Cliente cliente = clienteBusiness.obterCliente(idCliente);
			if(cliente != null){
				
				cliente.setEnderecoCliente(formEnderecoCliente.getEndereco());
				enderecoAtualizado = clienteBusiness.atualizarCliente(cliente);
				if(enderecoAtualizado){
					
					model.addAttribute("cliente", cliente);
					return "pages/componentes/comp-dados-cliente";
					
				}else{
					formEnderecoCliente.setResultado("erro-atualizar-endereco");
					formEnderecoCliente.setMensagem("Desculpe, ocorreu um erro ao atualizar o endereço");
				}
				
			}
			
		}

		model.addAttribute("formEnderecoCliente", formEnderecoCliente);
		
		
		return "pages/componentes/form-cadastro-endereco-cliente";
	}
	
	@RequestMapping(value = "/cadastrar/cliente",method = RequestMethod.POST, produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String cadastrarCliente(@ModelAttribute("formCliente")  FormCliente formCliente, Model model,HttpSession session,BindingResult result){
		clienteFisicoFormValidator.validate(formCliente, result);

		if(!result.hasErrors()){
			boolean clienteCadastrado;
			try {
				clienteCadastrado = clienteBusiness.cadastrarCliente(formCliente.getCliente());
				//Vai para a tela de cadastro de endereco
				if(clienteCadastrado){
					Object objectFormEnderecoCliente = session.getAttribute("formEnderecoCliente");
					FormEnderecoCliente formEnderecoCliente = null;
					if(objectFormEnderecoCliente != null){
						formEnderecoCliente = (FormEnderecoCliente) objectFormEnderecoCliente;
					}else{
						
						formEnderecoCliente = new FormEnderecoCliente();
						
					}
					formEnderecoCliente.setIdCliente(formCliente.getCliente().getId());
					model.addAttribute("formEnderecoCliente", formEnderecoCliente);

					return "pages/componentes/form-cadastro-endereco-cliente";
				
				}else{
				
					formCliente.setResultado("erro_cadastro");
					formCliente.setMensagem("mensagem.erro.cadastro.cliente");
				}
				
				
			} catch (ClienteJahCadastradoException e) {
				
				formCliente.setResultado("erro_ja_cadastrado");
				formCliente.setMensagem("mensagem.cliente.ja.cadastrado");
				log.info("Cliente já cadastrado" + e.getMessage());
			}
			
			
			
		}else{
			model.addAttribute("formCliente", formCliente);
		}
		
		return "pages/componentes/form-cadastro-cliente-fisico";
	}
	
	private void populateFormCliente(Model model){
		
		FormCliente formCliente = new FormCliente();
		model.addAttribute("formCliente", formCliente);
	}
	
	
	private void populateFormEnderecoCliente(Model model){
		
		FormEnderecoCliente formEnderecoCliente = new FormEnderecoCliente();
		model.addAttribute("formEnderecoCliente", formEnderecoCliente);
	}
}
