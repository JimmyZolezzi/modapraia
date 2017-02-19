package moda.praia.web.controller;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.web.controller.editor.ImagemProdutoEditor;
import moda.praia.web.controller.editor.ProdutoEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoInfoController {
	
	@Autowired
	private ProdutoBusiness produtoBusiness;
	@Autowired
	private ProdutoEditor produtoEditor;
	@Autowired
	private ImagemProdutoEditor imagemProdutoEditor;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Produto.class, produtoEditor);
		binder.registerCustomEditor(ImagemProduto.class, imagemProdutoEditor);
		
	}
	
	@RequestMapping(value = "/info-produto", method = RequestMethod.GET)
	public String infoProduto(@RequestParam("idProduto") long idProduto, Model model){

		populateForm(model, idProduto);
		
		return "pages/produto-info";
	}
	
	
	private void populateForm(Model model, long idProduto){
		
		Produto produto = produtoBusiness.pesquisarProduto(idProduto);
		model.addAttribute("produto", produto);
		
	}
	

}
