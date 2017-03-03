package moda.praia.controller;


import moda.praia.modulo.estoque.EstoqueBusiness;
import moda.praia.modulo.produtos.bean.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EstoqueController {
	
	@Autowired
	private EstoqueBusiness estoqueBusiness;
	
	@RequestMapping(value = "/estoque-produtos", method = RequestMethod.GET)
	public String listaProdutoEstoque(Model model) {
		
		Page<Produto> page = estoqueBusiness.listaProdutoEstoque(1);
		model.addAttribute("produtoPage", page);
		modePagination(page, model);
		return "pages/estoque-produtos";
	
	}
	
	@RequestMapping(value = "/estoque-produtos/pages/{pageNumber}", method = RequestMethod.GET)
	public String pageEstoqueProduto(@PathVariable Integer pageNumber, Model model) {
	    Page<Produto> page = estoqueBusiness.listaProdutoEstoque(pageNumber);
	  
	    modePagination(page, model);
	    return "pages/estoque-produtos";
	}
	
	private void modePagination(Page<Produto> page, Model model){
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());

	    model.addAttribute("produtoPage", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
		
	}
	
	@RequestMapping(value = "/estoque-produtos/{idProduto}",method = RequestMethod.GET)
	public String visualizarEstoqueProduto(@PathVariable Long idProduto, Model model){
		
		if(idProduto != null){
			Produto produto = estoqueBusiness.visualizarProdutoEstoque(idProduto);
			
			if(produto != null){
				model.addAttribute("produto", produto);
			}
			
		}
		return "pages/visualizar-produto-estoque";
		
	}
	
	

}
