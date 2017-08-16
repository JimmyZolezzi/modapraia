package moda.praia.web.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.endereco.Endereco;
import moda.praia.modulo.endereco.EnderecoBusiness;
import moda.praia.web.controller.editor.DateddMMyyyyEditor;
import moda.praia.web.controller.form.FormDadosCliente;
import moda.praia.web.controller.form.FormEnderecoCliente;
import moda.praia.web.controller.validator.FormDadosClienteValidador;
import moda.praia.web.controller.validator.FormEnderecoClienteValidator;

@Controller
public class DadosPessoaisController {
	
	private final static String URL_ATUALIZAR_MEU_ENDERECO = "/atualizar/meu/endereco";
	private final static String URL_ATUALIZAR_ENDERECO_ENTREGA = "/atualizar/endereco/entrega";
	
	@Autowired
	private ClienteBusiness clienteBusiness;
	@Autowired
	private DateddMMyyyyEditor dateEditor;
	@Autowired
	private EnderecoBusiness enderecoBusiness;
	@Autowired
	private FormDadosClienteValidador formDadosClienteValidador;
	@Autowired
	private FormEnderecoClienteValidator formEnderecoClienteValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@RequestMapping(value = "/carregar/dados/cliente", method = RequestMethod.GET)
	public String carregarDadosCliente(Model model, @RequestParam("idCliente") String idCliente){
		FormDadosCliente formDadosCliente = new FormDadosCliente();
		
		if(idCliente !=null && idCliente.matches("[0-9].*")){
			long id = Long.valueOf(idCliente);
			Cliente cliente = clienteBusiness.obterCliente(id);
			if(cliente != null){
				formDadosCliente.setIdCliente(String.valueOf(id));
				formDadosCliente.setNomeCompleto(cliente.getNome());
				formDadosCliente.setCelular(cliente.getCelular());
				formDadosCliente.setCpf(cliente.getCpfCnpj());
				formDadosCliente.setEmail(cliente.getEmail());
				formDadosCliente.setRg(cliente.getRg());
				formDadosCliente.setTelefone(cliente.getTelefone());
				formDadosCliente.setDataNascimento(cliente.getDataNascimento());
			}
			
		}
		
		model.addAttribute("formDadosCliente", formDadosCliente);
		
		return "pages/componentes/form-dados-cliente";
		
	}

	
	@RequestMapping(value = "/buscar/endereco/por/cep", method = RequestMethod.GET)
	@ResponseBody
	public Endereco buscarEndereco(@RequestParam("cep") String cep){
		
		Endereco endereco = enderecoBusiness.buscarEndereco(cep);

		return endereco;
		
	}
	
	@RequestMapping(value = "/carregar/meu/endereco", method = RequestMethod.GET)
	public String carregarMeuEndereco(Model model, @RequestParam("idCliente") String idCliente){
		
		FormEnderecoCliente formEnderecoCliente = new FormEnderecoCliente();
		if(idCliente !=null && idCliente.matches("[0-9].*")){
			long id = Long.valueOf(idCliente);
			formEnderecoCliente.setIdCliente(id);
			Cliente cliente = clienteBusiness.obterCliente(id);
			if(cliente != null && cliente.getEnderecoCliente() != null){
				formEnderecoCliente.setEndereco(cliente.getEnderecoCliente());
				
			}else{
				formEnderecoCliente.setEndereco(new Endereco());
			}
			
		}
		model.addAttribute("idFormulario", "formMeuEndereco");
		model.addAttribute("formEnderecoCliente", formEnderecoCliente);
		model.addAttribute("action", URL_ATUALIZAR_MEU_ENDERECO);
		return "pages/componentes/form-meu-endereco";
	}
	
	@RequestMapping(value = "/carregar/endereco/entrega", method = RequestMethod.GET)
	public String carregarEnderecoEntrega(Model model, @RequestParam("idCliente") String idCliente){
		
		FormEnderecoCliente formEnderecoCliente = new FormEnderecoCliente();
		if(idCliente !=null && idCliente.matches("[0-9].*")){
			long id = Long.valueOf(idCliente);
			formEnderecoCliente.setIdCliente(id);
			Cliente cliente = clienteBusiness.obterCliente(id);
			if(cliente != null && cliente.getEnderecoEntrega() != null){
				formEnderecoCliente.setEndereco(cliente.getEnderecoEntrega());
				
			}else{
				formEnderecoCliente.setEndereco(new Endereco());
			}
			
		}
		model.addAttribute("idFormulario", "formEnderecoEntrega");
		model.addAttribute("formEnderecoCliente", formEnderecoCliente);
		model.addAttribute("action", URL_ATUALIZAR_ENDERECO_ENTREGA);
		return "pages/componentes/form-meu-endereco";
	}
	
	@RequestMapping(value = "/atualizar/dados/cliente", method = RequestMethod.POST)
	public String atualizarDadosCliente(@ModelAttribute("formDadosCliente") FormDadosCliente formDadosCliente ,Model model, BindingResult result){
		
		formDadosClienteValidador.validate(formDadosCliente, result);
		boolean temErro = result.hasErrors();
		if(!temErro && formDadosCliente != null){
			String idCliente = formDadosCliente.getIdCliente();
			if(idCliente !=null && !idCliente.equals("0") && idCliente.matches("[0-9].*")){
				long id = Long.valueOf(idCliente);
				Cliente cliente = clienteBusiness.obterCliente(id);
				
				if(cliente != null){
					cliente.setNome(formDadosCliente.getNomeCompleto());
					cliente.setCelular(formDadosCliente.getCelular());
					cliente.setEmail(formDadosCliente.getEmail());
					cliente.setTelefone(formDadosCliente.getTelefone());
					cliente.setDataNascimento(formDadosCliente.getDataNascimento());
					cliente.setRg(formDadosCliente.getRg());
					cliente.setCpfCnpj(formDadosCliente.getCpf());
					boolean atualizado = clienteBusiness.atualizarCliente(cliente);

					if(atualizado){
						model.addAttribute("msg", "Dados pessoais atualizados com sucesso!");
						model.addAttribute("css", "alert-success");
					}else{
						temErro = true;
						model.addAttribute("msg", "Erro ao tentar atualizar os dados pessoais");
						model.addAttribute("css", "alert-danger");
					}
				}
				
				
			}else{
				temErro = true;
			}
			
		}
		model.addAttribute("idFormulario", "formEnderecoEntrega");
		model.addAttribute("temErro", temErro);
		
		return "pages/componentes/form-dados-cliente";
	}
	
	@RequestMapping(value = "/atualizar/meu/endereco", method = RequestMethod.POST)
	public String atualizarMeuEndereco(@ModelAttribute("formEnderecoCliente") FormEnderecoCliente formEnderecoCliente ,Model model, BindingResult result){
		
		formEnderecoClienteValidator.validate(formEnderecoCliente, result);
		
		boolean temErro = result.hasErrors();
		
		
		if(!temErro && formEnderecoCliente != null){
			long idCliente = formEnderecoCliente.getIdCliente();
			if(idCliente != 0){
				Cliente cliente = clienteBusiness.obterCliente(idCliente);
				
				if(cliente != null){
					cliente.setEnderecoCliente(formEnderecoCliente.getEndereco());
					boolean atualizado = clienteBusiness.atualizarCliente(cliente);

					if(atualizado){
						model.addAttribute("msg", "O seu endereço foi atualizado com sucesso!");
						model.addAttribute("css", "alert-success");
					}else{
						temErro = true;
						model.addAttribute("msg", "Erro ao tentar atualizar o endereço");
						model.addAttribute("css", "alert-danger");
					}
				}
				
				
			}
			
		}
		model.addAttribute("idFormulario", "formMeuEndereco");
		model.addAttribute("temErro", temErro);
		
		return "pages/componentes/form-meu-endereco";
	}
	
	@RequestMapping(value = "/atualizar/endereco/entrega", method = RequestMethod.POST)
	public String atualizarEnderecoEntrega(@ModelAttribute("formEnderecoCliente") FormEnderecoCliente formEnderecoCliente ,Model model, BindingResult result){
		formEnderecoClienteValidator.validate(formEnderecoCliente, result);
		boolean temErro = result.hasErrors();
		
		if(!temErro && formEnderecoCliente != null){
			long idCliente = formEnderecoCliente.getIdCliente();
			if(idCliente != 0){
				Cliente cliente = clienteBusiness.obterCliente(idCliente);
				
				if(cliente != null){
					cliente.setEnderecoEntrega(formEnderecoCliente.getEndereco());
					boolean atualizado = clienteBusiness.atualizarCliente(cliente);

					if(atualizado){
						model.addAttribute("msg", "O seu endereço de entrega foi atualizado com sucesso!");
						model.addAttribute("css", "alert-success");
					}else{
						temErro = true;
						
						model.addAttribute("msg", "Erro ao tentar atualizar o endereço de entrega");
						model.addAttribute("css", "alert-danger");
					}
				}
				
				
			}
			
		}
		model.addAttribute("idFormulario", "formEnderecoEntrega");
		model.addAttribute("temErro", temErro);
		
		return "pages/componentes/form-meu-endereco";
	}
}
