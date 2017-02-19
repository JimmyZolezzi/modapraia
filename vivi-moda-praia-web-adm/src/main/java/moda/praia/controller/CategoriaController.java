package moda.praia.controller;


import java.util.List;

import moda.praia.controller.validator.CategoriaFormValidator;
import moda.praia.modulo.jsonview.Views;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaFormValidator validator;
	@Autowired
	private ProdutoBusiness produtoBusiness;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/cadastro-categoria", method = RequestMethod.GET)
	public String handleRequest(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoriaForm", categoria);
		return "pages/cadastro-categoria";
		
		
	}
	
	@RequestMapping(value = "/categoria/add", method = RequestMethod.POST)
	public String addProduto(@ModelAttribute("categoriaForm") @Validated Categoria categoria,BindingResult result, Model model){

		model.addAttribute("hasError", result.hasErrors());
	
		if(result.hasErrors()){

		}else{
			
			if(!result.hasErrors()){
				
				boolean cadastrado = produtoBusiness.cadastrarCategoria(categoria);

				if(cadastrado){
					model.addAttribute("msg", "Categoria cadastrada com sucesso!");
					model.addAttribute("css", "alert-success");
				}else{
					model.addAttribute("msg", "Erro ao cadastrar categoria!");
					model.addAttribute("css", "alert-danger");
				}
				
			}
			
		}

		return "pages/cadastro-categoria";
	}
	
	@RequestMapping(value = "/categoria/pesquisa")
	@ResponseBody
	@JsonView(Views.Public.class)
	public List<Categoria> pesquisaCategorias() {

		List<Categoria> categorias = produtoBusiness.pesquisarCategorias();

		return categorias;

	}
	
	
	
}
