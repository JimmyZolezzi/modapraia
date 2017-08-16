package moda.praia.controller;


import moda.praia.controller.converter.ConverterImagemProduto;
import moda.praia.controller.editor.ImagemProdutoEditor;
import moda.praia.controller.editor.ProdutoEditor;
import moda.praia.controller.form.FormImagemProduto;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.modulo.produtos.bean.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InfoProdutoController {
	
	@Autowired
	private ProdutoBusiness produtoBusiness;
	@Autowired
	private ConverterImagemProduto converterImagemProduto;
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
	public String infoProduto(@RequestParam("idProduto") long idProduto,@RequestParam("msg")String msg, Model model){

		populateForm(model, idProduto);
		model.addAttribute("msg", msg);
		
		return "pages/componentes/produto-info";
	}
	
	@RequestMapping(value = "/detalhe-produto", method = RequestMethod.GET)
	public String detalheProduto(@RequestParam("idProduto") long idProduto, Model model){

		populateForm(model, idProduto);
		
		return "pages/detalhes-produto";
	}
	
	@RequestMapping(value = "/info-produto/add/foto", method = RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public String adicionarFoto(@ModelAttribute("formImagemProduto") FormImagemProduto formImagemProduto ,Model model){
		
		ImagemProduto imagemProduto = converterImagemProduto.coverter(formImagemProduto.getFoto());
		Produto produto = produtoBusiness.pesquisarProduto(formImagemProduto.getIdProduto());
		boolean imagemAdicionada = produtoBusiness.cadastrarImagemProduto(produto, imagemProduto);
		if(imagemAdicionada){
			populateForm(model, produto.getId());
			model.addAttribute("msg", "Imagem adicionada com sucesso!");
			model.addAttribute("css", "alert-success");
		}else{
			model.addAttribute("msg", "Ops! Houve um erro ao tentar adicionar a imagem");
			model.addAttribute("css", "alert-danger");
			
		}
			
		return "pages/componentes/produto-info";
	}
	
	
	@RequestMapping(value = "/info-produto/remover/foto", method = RequestMethod.GET)
	public String removerFoto(@RequestParam("idImagemProduto") ImagemProduto imagemProduto,@RequestParam("idProduto") Produto produto ,Model model){
		
		populateForm(model, produto.getId());
		
		boolean imagemRemovida = produtoBusiness.removerImagemProduto(produto, imagemProduto);
		if(imagemRemovida){
	
			model.addAttribute("msg", "Imagem removida com sucesso!");
			model.addAttribute("css", "alert-success");
		}else{
			model.addAttribute("msg", "Ops! Houve um erro ao tentar remover a imagem");
			model.addAttribute("css", "alert-danger");
			
		}
			
		return "pages/produto-info";
	}
	
	private void populateForm(Model model, long idProduto){
		
		FormImagemProduto formImagemProduto = new FormImagemProduto();
		
		formImagemProduto.setIdProduto(idProduto);
		Produto produto = produtoBusiness.pesquisarProduto(idProduto);
		model.addAttribute("produto", produto);
		model.addAttribute("formImagemProduto", formImagemProduto);
		
	}
}
