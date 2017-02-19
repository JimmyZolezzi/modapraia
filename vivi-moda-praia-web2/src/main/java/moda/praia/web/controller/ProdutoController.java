package moda.praia.web.controller;

import java.util.List;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.Subcategoria;
import moda.praia.web.controller.editor.CategoriaEditor;
import moda.praia.web.controller.editor.SubcategoriaEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
public class ProdutoController {

	@Autowired
	private ProdutoBusiness produtoBusiness;
	@Autowired
	private CategoriaEditor categoriaEditor;
	@Autowired
	private SubcategoriaEditor subcategoriaEditor;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Categoria.class, categoriaEditor);
		binder.registerCustomEditor(Subcategoria.class, subcategoriaEditor);
	}
	
	@RequestMapping(value = "/produtos/{idCategoria}", method = RequestMethod.GET)
	public String produtos(Model model,@PathVariable("idCategoria") int idCategoria) {
		
		Categoria categoria = produtoBusiness.pesquisarCategoria(idCategoria);
		List<Produto> produtos = produtoBusiness.pesquisaProdutos(idCategoria);
		model.addAttribute("categoria", categoria);
		model.addAttribute("produtosCategoria", produtos);
		
		return "pages/produtos-categoria";
	}
	
	@RequestMapping(value = "/produtos/{idCategoria}/{idSubCategoria}", method = RequestMethod.GET)
	public String produtos(Model model,@PathVariable("idCategoria") int idCategoria, @PathVariable("idSubCategoria") Subcategoria subcategoria) {
		List<Produto> produtos = produtoBusiness.pesquisaProdutos(subcategoria);
		model.addAttribute("categoria", subcategoria);
		model.addAttribute("produtosCategoria", produtos);
		
		return "pages/produtos-categoria";
	}
	
}
