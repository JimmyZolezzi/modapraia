package moda.praia.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import moda.praia.modulo.avaliacao.AvaliacaoBusiness;
import moda.praia.modulo.avaliacao.bean.Avaliacao;
import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.jsonview.Views.Public;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.web.controller.form.FormComentarioAvaliacao;
import moda.praia.web.controller.validator.FormComentarioAvaliacaoValidator;

@Controller
public class ComentarioAvaliacaoProdutoController {
	
	@Autowired
	private ProdutoBusiness produtoBusiness;
	
	@Autowired
	private ClienteBusiness clienteBusiness;
	@Autowired
	private FormComentarioAvaliacaoValidator formComentarioAvaliacaoValidator;
	@Autowired
	private AvaliacaoBusiness avaliacaoBusiness;
	
	@RequestMapping(value = "/carregar-comentarios", method = RequestMethod.GET)
	public String carregarComentarios(@RequestParam("idProduto") String idProduto, Model model){
		model.addAttribute("idProduto", idProduto);
		if(idProduto != null && idProduto.matches("[0-9].*")){
			long idProdutoLong = Long.valueOf(idProduto);
			Produto produto = produtoBusiness.pesquisarProduto(idProdutoLong);
			model.addAttribute("produto", produto);
			Page<Avaliacao> page = avaliacaoBusiness.avaliacoes(1, produto);
			model.addAttribute("page", page);
			model.addAttribute("idProduto", idProduto);
			modePagination(page, model);
			
		}
		
		return "pages/componentes/comp-avaliacoes";
		
	}
	
	@RequestMapping(value = "/carregar-comentarios/pages/{pageNumber}",  method = RequestMethod.GET)
	public String carregarComentarios(@PathVariable Integer pageNumber, String idProduto, Model model){
	
		model.addAttribute("idProduto", idProduto);
		if(idProduto != null && idProduto.matches("[0-9].*")){
			long idProdutoLong = Long.valueOf(idProduto);
			Produto produto = produtoBusiness.pesquisarProduto(idProdutoLong);
			model.addAttribute("produto", produto);
			Page<Avaliacao> page = avaliacaoBusiness.avaliacoes(pageNumber, produto);
			model.addAttribute("avaliacoes", page.getContent());
			//modePagination(page, model);
			if(page != null){
				return "pages/componentes/comp-mais-avaliacoes";
			}
		}
		
		return null;
	}

	@RequestMapping(value = "/carregar-form-avaliacao", method = RequestMethod.GET)
	public String formularioAvaliacao(@RequestParam("idProduto") String idProduto, Model model){
		String email = getPrincipal();
		Cliente cliente = null;
		model.addAttribute("idProduto", idProduto);
		if(email != null){
			cliente = clienteBusiness.obterClientePorEmail(email);
		}
		FormComentarioAvaliacao formComentarioAvaliacao = new FormComentarioAvaliacao(new Avaliacao());
		if(idProduto != null && idProduto.matches("[0-9].*") && cliente != null){
			long idProdutoLong = Long.valueOf(idProduto);
			Produto produto = produtoBusiness.pesquisarProduto(idProdutoLong);
			formComentarioAvaliacao.getAvaliacao().setProduto(produto);
			model.addAttribute("produto", produto);
			formComentarioAvaliacao.getAvaliacao().setCliente(cliente);
			if(cliente.getNome() != null){
				String apelido = cliente.getNome().replaceAll(" .*", "");
				formComentarioAvaliacao.getAvaliacao().setApelido(apelido);
				
				
			}
		}
		model.addAttribute("formComentarioAvaliacao", formComentarioAvaliacao);
		return "pages/componentes/comp-form-avaliacao";
	}
	
	@RequestMapping(value = "/avaliar/produto", method = RequestMethod.POST)
	public String avaliar(@ModelAttribute("formComentarioAvaliacao") FormComentarioAvaliacao formComentarioAvaliacao, Model model, BindingResult results){
		formComentarioAvaliacaoValidator.validate(formComentarioAvaliacao, results);
		String idProduto = "";
		if(formComentarioAvaliacao != null && formComentarioAvaliacao.getAvaliacao() != null && formComentarioAvaliacao.getAvaliacao().getProduto() != null){
			idProduto = String.valueOf(formComentarioAvaliacao.getAvaliacao().getProduto().getId());
		}
		
		
		if(results.hasErrors()){
			return "pages/componentes/comp-form-avaliacao";
		}
		
		boolean avaliou = avaliacaoBusiness.salvarAvaliacao(formComentarioAvaliacao.getAvaliacao());
		if(avaliou){
			model.addAttribute("msg", "Obrigado por sua avaliação");
			model.addAttribute("css", "alert-success");
			
		}else{
			model.addAttribute("msg", "Desculpe, ocorreu um erro ao fazer a avaliação.");
			model.addAttribute("css", "alert-danger");
		}
		
		return carregarComentarios(idProduto, model);
		
	}
	
	private void modePagination(Page<Avaliacao> page, Model model){
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 3, page.getTotalPages());

	    //model.addAttribute("pedidoPage", page);
	    //model.addAttribute("beginIndex", begin);
	    //model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current); 
		
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
