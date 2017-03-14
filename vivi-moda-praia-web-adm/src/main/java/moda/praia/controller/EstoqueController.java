package moda.praia.controller;


import java.util.List;

import moda.praia.controller.form.FormEntradaEstoque;
import moda.praia.modulo.estoque.EstoqueBusiness;
import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.bean.TamanhoLetra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = "/carregar/form/entrada/estoque",method = RequestMethod.GET)
	public String carregarFormularioEntradaEstoque(@RequestParam("idItemProduto") String idItemProduto, @RequestParam("idProduto") String idProduto, Model model){
		if(idItemProduto != null && idItemProduto.matches("[0-9].*") && idProduto != null && idProduto.matches("[0-9].*")){
			long idItemProdutoLong = Long.parseLong(idItemProduto);
			long idProdutoLong = Long.parseLong(idProduto);
			ItemProduto itemProduto = estoqueBusiness.carregarItemProduto(idItemProdutoLong);
			if(itemProduto!=null && itemProduto.getTipoMedida() !=  null){
				model.addAttribute("tamanhosLetra",TamanhoLetra.values());
				model.addAttribute("itemProduto",itemProduto);
				//Cria formEntradaEstoque
				FormEntradaEstoque formEntradaEstoque = new FormEntradaEstoque();
				formEntradaEstoque.setNomeItem(itemProduto.getNome());
				ItemProdutoEstoque itemProdutoEstoque = new ItemProdutoEstoque();
				itemProdutoEstoque.setTipoMedida(itemProduto.getTipoMedida());
				formEntradaEstoque.setItemProdutoEstoque(itemProdutoEstoque);
				formEntradaEstoque.setIdItemEstoque(itemProduto.getId());
				formEntradaEstoque.setIdProduto(idProdutoLong);
				model.addAttribute("formEntradaEstoque",formEntradaEstoque);
				
			}
			
		}

		return "pages/componentes/form-entrada-estoque";
	
	}

	@RequestMapping(value = "/selecionar/item/produto/estoque",method = RequestMethod.GET)
	public String selecionarItemProddutoEstoque(@RequestParam("idItemProduto") String idItemProduto,@RequestParam("tamanho") String tamanho,@RequestParam("idProduto") String idProduto ,Model model){
		
		if(idItemProduto != null && idItemProduto.matches("[0-9].*") && idProduto !=null && idProduto.matches("[0-9].*")){
			long idItemProdutoLong = Long.parseLong(idItemProduto);
			long idProdutoLong = Long.parseLong(idProduto);
			ItemProduto itemProduto = estoqueBusiness.carregarItemProduto(idItemProdutoLong);
			if(itemProduto!=null && itemProduto.getTipoMedida() !=  null){
				
				model.addAttribute("tamanhosLetra",TamanhoLetra.values());
				model.addAttribute("itemProduto",itemProduto);
				//Cria formEntradaEstoque
				ItemProdutoEstoque itemProdutoEstoque = procurarItemProdutoEstoque(itemProduto, tamanho);
				
				FormEntradaEstoque formEntradaEstoque = new FormEntradaEstoque();
				formEntradaEstoque.setNomeItem(itemProduto.getNome());
				if(itemProdutoEstoque == null){
					itemProdutoEstoque = new ItemProdutoEstoque();
				}
				itemProdutoEstoque.setTamanho(tamanho);
				itemProdutoEstoque.setTipoMedida(itemProduto.getTipoMedida());
				formEntradaEstoque.setItemProdutoEstoque(itemProdutoEstoque);
				formEntradaEstoque.setIdItemEstoque(idItemProdutoLong);
				formEntradaEstoque.setIdProduto(idProdutoLong);
				model.addAttribute("formEntradaEstoque",formEntradaEstoque);
				
			}
			
		}

		return "pages/componentes/form-entrada-estoque";
	
	}

	@RequestMapping(value = "/realizar/entrada/produto/estoque",method = RequestMethod.POST)
	public String entradaEstoque(@ModelAttribute("formEntradaEstoque") FormEntradaEstoque formEntradaEstoque,
			BindingResult result,Model model){
		
		if(formEntradaEstoque !=null && formEntradaEstoque.getItemProdutoEstoque() != null ){
			Produto produto = estoqueBusiness.visualizarProdutoEstoque(formEntradaEstoque.getIdProduto());
			ItemProduto itemProduto = estoqueBusiness.carregarItemProduto(formEntradaEstoque.getIdItemEstoque());
			ItemProdutoEstoque itemProdutoEstoque = formEntradaEstoque.getItemProdutoEstoque();
			boolean salvo = estoqueBusiness.entrarProdutoEstoque(produto, itemProduto, itemProdutoEstoque, formEntradaEstoque.getQuantidadeEntrada());
			if(salvo){
				model.addAttribute("msg", "Entrada de produto em estoque realizada com sucesso.");
				model.addAttribute("css", "alert-success");
			}else{
				model.addAttribute("msg", "Erro ao tentar realizar a entrada de produto em estoque.");
				model.addAttribute("css", "alert-danger");
			}
			
		}
		
		return "pages/componentes/form-entrada-estoque";
	}
	
	@RequestMapping(value = "/atualizar/pecas/estoque",method = RequestMethod.GET)
	public String atualizarPecas(@RequestParam String idProduto, Model model){
		
		if(idProduto != null && idProduto.matches("[0-9].*")){
			long idProdutoLong = Long.valueOf(idProduto);
			Produto produto = estoqueBusiness.visualizarProdutoEstoque(idProdutoLong);
			
			if(produto != null){
				model.addAttribute("produto", produto);
			}
			
		}
		
		return "pages/componentes/pecas-estoque";
	}
	
	
	private ItemProdutoEstoque procurarItemProdutoEstoque(ItemProduto itemProduto, String tamanho){
		List<ItemProdutoEstoque> itensEstoque = itemProduto.getItensEstoque();
		for (ItemProdutoEstoque itemProdutoEstoque : itensEstoque) {
			if(itemProdutoEstoque.getTamanho() != null && itemProdutoEstoque.getTamanho().equals(tamanho)){
				return itemProdutoEstoque;
			}
		}
		
		return null;
	}
	

}
