package moda.praia.controller;

import java.util.List;

import moda.praia.controller.editor.CategoriaEditor;
import moda.praia.controller.validator.SubCategoriaValidator;
import moda.praia.modulo.jsonview.Views;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Subcategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
public class SubCategoriaController {
	
	@Autowired
	private SubCategoriaValidator validator;
	@Autowired
	private ProdutoBusiness produtoBusiness;
	@Autowired
	private CategoriaEditor categoriaEditor;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		binder.registerCustomEditor(Categoria.class, categoriaEditor);
	}

	@RequestMapping(value = "/cadastro-subcategoria", method = RequestMethod.GET)
	public String handleRequest(Model model) {
		Subcategoria subCategoria = new Subcategoria();
		model.addAttribute("subcategoriaForm", subCategoria);
		populateCategoria(model);
		return "pages/cadastro-subcategoria";
		
		
	}
	
	@RequestMapping(value = "/subcategoria/add", method = RequestMethod.POST)
	public String addSubcategoria(@ModelAttribute("subcategoriaForm") @Validated Subcategoria subcategoria, BindingResult result, Model model){

		model.addAttribute("hasError", result.hasErrors());
	
		populateCategoria(model);
			
		if(!result.hasErrors()){
			
			boolean cadastrado = produtoBusiness.cadastrarSubCategoria(subcategoria);

			if(cadastrado){
				model.addAttribute("msg", "Subcategoria cadastrada com sucesso!");
				model.addAttribute("css", "alert-success");
			}else{
				model.addAttribute("msg", "Erro ao cadastrar subcategoria!");
				model.addAttribute("css", "alert-danger");
			}
			
		}
			

		return "pages/cadastro-subcategoria";
	}
	
	public void populateCategoria(Model model){
		List<Categoria> categorias = produtoBusiness.pesquisarCategorias();
		model.addAttribute("categorias", categorias);
	}
	
	@RequestMapping(value = "/subcategoria/pesquisa")
	@ResponseBody
	@JsonView(Views.Public.class)
	public List<Subcategoria> pesquisaCategorias() {

		List<Subcategoria> subcategorias = produtoBusiness.pesquisarSubcategoria();

		return subcategorias;

	}

}
