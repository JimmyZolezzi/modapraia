package moda.praia.web.controller;

import java.util.List;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.Subcategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class IndexController {
	
	@Autowired
	private ProdutoBusiness produtoBusiness;

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public ModelAndView handleRequest(Model model) {

	    Page<Produto> page = produtoBusiness.pesquisaProdutosDestaque(1);
	    model.addAttribute("produtoPage", page);
	    modePagination(page, model);
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/produtos/destaque/pages/{pageNumber}", method = RequestMethod.GET)
	public String pageEstoqueProduto(@PathVariable Integer pageNumber, Model model) {
	    Page<Produto> page = produtoBusiness.pesquisaProdutosDestaque(pageNumber);
	    model.addAttribute("produtoPage", page);
	    modePagination(page, model);
	    return "home";
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
	
	@RequestMapping(value = "/menu/categoria",method = RequestMethod.GET)
	@ResponseBody
	public List<Categoria> carregarMenuCategorias(){
		
		List<Categoria> categorias = produtoBusiness.pesquisarCategorias();
				
		
		return categorias;
		
	}
	
	@RequestMapping(value = "/menu/subcategoria",method = RequestMethod.GET)
	@ResponseBody
	public List<Subcategoria> carregarMenuSubcategorias(@RequestParam("idCategoria") String idCategoria){
		Categoria categoria = new Categoria();
		if(idCategoria != null && !idCategoria.equals("") && idCategoria.matches("[1-9].*")){
			categoria.setId(Integer.valueOf(idCategoria));
			List<Subcategoria> subcategorias = produtoBusiness.pesquisarSubcategoria(categoria);
			return subcategorias;
			
		}

		return null;
		
	}

}
