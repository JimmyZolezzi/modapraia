package moda.praia.controller;

import java.util.ArrayList;
import java.util.List;

import moda.praia.controller.converter.ConverterImagemProduto;
import moda.praia.controller.editor.CategoriaEditor;
import moda.praia.controller.editor.SubcategoriaEditor;
import moda.praia.controller.editor.TipoMedidaEditor;
import moda.praia.controller.form.FormProduto;
import moda.praia.controller.validator.ProdutoFormValidator;
import moda.praia.modulo.jsonview.Views;
import moda.praia.modulo.jsonview.Views.Public;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.Subcategoria;
import moda.praia.modulo.produtos.bean.TipoMedida;
import moda.praia.util.StatusRequest;

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

import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class ProdutoController {
	
	private final Logger log = Logger.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoBusiness produtoBusiness;
	@Autowired
	private CategoriaEditor categoriaEditor;
	@Autowired
	private SubcategoriaEditor subcategoriaEditor;
	@Autowired
	private TipoMedidaEditor tipoMedidaEditor;
	@Autowired
	private ConverterImagemProduto converterImagemProduto;
	@Autowired
	private ProdutoFormValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		binder.registerCustomEditor(Categoria.class, categoriaEditor);
		binder.registerCustomEditor(Subcategoria.class, subcategoriaEditor);
//		binder.registerCustomEditor(TipoMedida.class, tipoMedidaEditor);
	}

	@RequestMapping(value = "/cadastro-produto", method = RequestMethod.GET)
	public String handleRequest(Model model) {
		FormProduto formProduto = new FormProduto(new Produto());
		formProduto.setQuantidade(1);
		ItemProduto itemProduto = new ItemProduto();
		List<ItemProduto> itensProduto = new ArrayList<ItemProduto>();
		itensProduto.add(itemProduto);
		formProduto.setItensProduto(itensProduto);
		model.addAttribute("produtoForm",formProduto);
		populateDefaultModel(model,null);
		model.addAttribute("foto1", null);
		model.addAttribute("foto2", null);
		return "pages/cadastro-produto";
		
		
	}
	
	@RequestMapping(value = "/produto/add", method = RequestMethod.POST)
	public String addProduto(@ModelAttribute("produtoForm") @Validated FormProduto formProduto, BindingResult result, Model model){
		if(formProduto.getCategoria()!=null){
			model.addAttribute("categoriaId",formProduto.getCategoria().getId());
		}
		if(formProduto.getSubcategoria()!=null){
			model.addAttribute("subcategoriaId",formProduto.getSubcategoria().getId());
		}
		if(!result.hasErrors()){
			
			 
			if(formProduto.getFoto1() != null && formProduto.getFoto1().getSize() !=0){
				
				ImagemProduto imagemProduto1 = converterImagemProduto.coverter(formProduto.getFoto1());
				formProduto.setImagemProduto1(imagemProduto1);
			}
			if(formProduto.getFoto1() != null && formProduto.getFoto1().getSize() !=0){
				
				ImagemProduto imagemProduto1 = converterImagemProduto.coverter(formProduto.getFoto1());
				formProduto.setImagemProduto1(imagemProduto1);
			}
			Produto produto = FormProduto.valueOf(formProduto);
			boolean cadastrado = produtoBusiness.cadastrarProduto(produto);
			
			if(cadastrado){
				formProduto.zerarValores();
				model.addAttribute("msg", "Produto cadastrado com sucesso!");
				model.addAttribute("css", "alert-success");
			}else{
				model.addAttribute("msg", "Erro ao cadastrar produto!");
				model.addAttribute("css", "alert-danger");
				
			}
			
		}
			
		populateDefaultModel(model,formProduto);
		
		
		return "pages/cadastro-produto";
	}
	
	@RequestMapping(value = "/produto/alterar", method = RequestMethod.POST)
	public String alterarProduto(@ModelAttribute("produtoForm") @Validated FormProduto formProduto, BindingResult result, Model model){
		if(formProduto.getCategoria()!=null){
			model.addAttribute("categoriaId",formProduto.getCategoria().getId());
		}
		if(formProduto.getSubcategoria()!=null){
			model.addAttribute("subcategoriaId",formProduto.getSubcategoria().getId());
		}
		if(!result.hasErrors()){
			
			if(formProduto.getFoto1() != null && formProduto.getFoto1().getSize() !=0){
				
				ImagemProduto imagemProduto1 = converterImagemProduto.coverter(formProduto.getFoto1());
				formProduto.setImagemProduto1(imagemProduto1);
			}
			if(formProduto.getFoto2() != null && formProduto.getFoto2().getSize() !=0){
				
				ImagemProduto imagemProduto2 = converterImagemProduto.coverter(formProduto.getFoto2());
				formProduto.setImagemProduto2(imagemProduto2);
			}
			Produto produto = FormProduto.valueOf(formProduto);
			
			boolean alterado = produtoBusiness.alterarProduto(produto);
			
			if(alterado){
				formProduto.zerarValores();
				model.addAttribute("msg", "Produto alterado com sucesso!");
				model.addAttribute("css", "alert-success");
			}else{
				model.addAttribute("msg", "Erro ao alterar produto!");
				model.addAttribute("css", "alert-danger");
				
			}
			
		}
			
		populateDefaultModel(model,formProduto);
		
		
		return "pages/cadastro-produto";
	}
	
	@RequestMapping(value = "/produto/addItem", method = RequestMethod.POST)
	public String adicionarProdutoItem(@ModelAttribute("produtoForm")FormProduto formProduto,BindingResult result, Model model){
		int quantidade = formProduto.getQuantidade();
		
		if(formProduto.getItensProduto()==null){
			formProduto.setItensProduto(new ArrayList<ItemProduto>());
		}
		
		List<ItemProduto> itensProduto = formProduto.getItensProduto();
		int quantidadeRegistrado = itensProduto.size();
		//Aumentar quantidade
		if(quantidade > quantidadeRegistrado){
			int quantidadeAumentar = quantidade - quantidadeRegistrado;
			
			for(int i =0;i<quantidadeAumentar;i++){
				ItemProduto itemProduto = new ItemProduto();
				itensProduto.add(itemProduto);
			}
			
		}
		//diminuir quantidade
		if(quantidade < quantidadeRegistrado){
			int quantidadeDiminuir = quantidadeRegistrado - quantidade;
			for(int i =0;i<quantidadeDiminuir;i++){
				itensProduto.remove(quantidadeDiminuir + i);
			}
		}
		formProduto.setQuantidade(itensProduto.size());
		populateDefaultModel(model,formProduto);
		
		return "pages/cadastro-produto";
	}
	
	@RequestMapping(value = "/produto/remover", method = RequestMethod.GET)
	@JsonView(Public.class)
	@ResponseBody
	public StatusRequest removerProduto(@RequestParam("idProduto")String idProduto, Model model){
		boolean removido = false;
		if(idProduto!=null && !idProduto.equals("") && idProduto.matches("[1-9].*")){
			removido = produtoBusiness.removerProduto(Long.valueOf(idProduto));
			
		}
	
		if(removido){
		
			model.addAttribute("msg", "Produto removido com sucesso!");
			model.addAttribute("css", "alert-success");
			return new StatusRequest(StatusRequest.SUCESSO);
		}else{
			model.addAttribute("msg", "Não possível remover o produto!");
			model.addAttribute("css", "alert-danger");

			return new StatusRequest(StatusRequest.ERRO);
		}
	}	
	
	@RequestMapping(value = "/produto/carregar/alteracao", method = RequestMethod.GET)
	public String carregarProdutoAleracao(@RequestParam("idProduto")String idProduto, Model model){
		FormProduto formProduto = new FormProduto(new Produto());
		if(idProduto!=null && !idProduto.equals("") && idProduto.matches("[0-9].*")){
			Produto produto = produtoBusiness.pesquisarProduto(Long.parseLong(idProduto));
			model.addAttribute("idCategoriaSelecionada", produto.getCategoria().getId());
			formProduto = new FormProduto(produto);
		}
		
		model.addAttribute("msg", "");
		model.addAttribute("css", "");
		model.addAttribute("btnHabilitadoCadastrar", true);
		model.addAttribute("produtoForm",formProduto);
		populateDefaultModel(model, formProduto);
		
		return "pages/cadastro-produto";
		
	}
	
	
	@JsonView(Public.class)
	@ResponseBody @RequestMapping(value="pesquisa/categoria/onChange", method = RequestMethod.GET)
	public List<Subcategoria> onChangeCategoria(@RequestParam("categoria") Categoria categoria, Model model){

		return produtoBusiness.pesquisarSubcategoria(categoria);
		
	}
	
	@RequestMapping(value = "/produto/pesquisa")
	@ResponseBody
	@JsonView(Views.Public.class)
	public List<Produto> pesquisaProdutos() {

		List<Produto> produtos = produtoBusiness.pesquisaProdutos();

		return produtos;

	}

	private void populateDefaultModel(Model model, FormProduto formProduto) {
		
		List<Categoria> categorias = produtoBusiness.pesquisarCategorias();
		model.addAttribute("categorias", categorias);
		model.addAttribute("medidas", TipoMedida.values());
		if(formProduto != null){
			Categoria categoria = formProduto.getCategoria();
			if(categoria != null){
				
				model.addAttribute("subcategorias", produtoBusiness.pesquisarSubcategoria(categoria));
			}
		}
	}
}
