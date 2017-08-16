package moda.praia.web.controller;

import moda.praia.modulo.carrinho.CarrinhoBusiness;
import moda.praia.modulo.jsonview.Views.Public;
import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.web.controller.editor.ProdutoEditor;
import moda.praia.web.controller.form.FormProdutoPedido;
import moda.praia.web.controller.validator.ProdutoPedidoFormValidator;
import moda.praia.web.dto.ValorPedidoFrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

@Controller
@Transactional
@Scope("session")
public class CarrinhoController {

	@Autowired
	private CarrinhoBusiness carrihoBusiness;
	@Autowired
	private ProdutoBusiness produtoBusiness;
	
	@Autowired
	private ProdutoEditor produtoEditor;
	@Autowired
	private ProdutoPedidoFormValidator produtoPedidoFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Produto.class, produtoEditor);
	}
	
	@ResponseBody @RequestMapping(value="produto/add/carrinho",method=RequestMethod.GET)
	public Pedido colocarProdutoCarrinho(@RequestParam("idProduto") Produto produto,@RequestParam("quantidade") int quantidade){

		carrihoBusiness.colocarProdutoCarrinho(produto, quantidade);
		
		return carrihoBusiness.obterPedidoCarrinho();
	}

	@RequestMapping(value="produtoPedido/add/carrinho",method=RequestMethod.POST)
	public String colocarProdutoPedidoCarrinho(@ModelAttribute("formProdutoPedido") @Validated FormProdutoPedido formProdutoPedido,BindingResult result, Model model){
		populateForm(model, formProdutoPedido);
		produtoPedidoFormValidator.validate(formProdutoPedido, result);
		if(formProdutoPedido!=null && formProdutoPedido.getListaProdutoPedido() != null && formProdutoPedido.getListaProdutoPedido().size() !=0){
			Produto produto = produtoBusiness.pesquisarProduto(formProdutoPedido.getIdProduto());
			if(!result.hasErrors()){
				List<ProdutoPedido> produtosPedido = formProdutoPedido.getListaProdutoPedido();
				for (ProdutoPedido produtoPedido : produtosPedido) {
					produtoPedido.setQuantidade(1);
					produtoPedido.setProduto(produto);
					carrihoBusiness.colocarProdutoCarrinho(produtoPedido);
				}
				Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
				model.addAttribute("pedido", pedido);
				model.addAttribute("title", "Carrinho de Compras");
			}else{

				return "pages/componentes/form-entrada-produto-carrinho";
				
			}
			
		}
		
		return "pages/componentes/comp-pedido-carrinho";
	}
	
	@ResponseBody @RequestMapping(value="produto/remover/carrinho",method=RequestMethod.GET)
	public Pedido excluirProdutoCarrinho(@RequestParam("idProduto") Produto produto){

		carrihoBusiness.excluirProdutoCarrinho(produto);
		return carrihoBusiness.obterPedidoCarrinho();
	}
	
	@ResponseBody @RequestMapping(value="produto/carregar/carrinho",method=RequestMethod.GET)
	public Pedido carregarCarrinho(){

		return carrihoBusiness.obterPedidoCarrinho();
	}

	@RequestMapping(value="produto/carregar/pagina/carrinho",method=RequestMethod.GET)
	public ModelAndView  carregarCarrinhoPagina(){
		ModelAndView modelView = new ModelAndView("pages/componentes/carrinho");
		Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
		
		modelView.addObject("pedido", pedido);
		return modelView;
	}
	
	@RequestMapping(value = "/pedido-carrinho",method = RequestMethod.GET)
	public ModelAndView produtoPedidoCarrinho(Model model) {
		ModelAndView modelView = new ModelAndView("pages/pedido-carrinho");
		Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
		modelView.addObject("pedido", pedido);
		modelView.addObject("title", "Carrinho de Compras");
		
		return modelView;
	}
	
	
	@RequestMapping(value = "/mudar/quantidade/produto/pedido",method = RequestMethod.GET)
	public String mudarQuantidadeProdutoPedido(@RequestParam("chave")String chave,@RequestParam("quantidade") String quantidade, Model model){
		
		if(chave !=null && !chave.equals("") && quantidade != null && quantidade.matches("[0-9].*")){
			
			int intQuantidade = Integer.valueOf(quantidade);
			carrihoBusiness.mudarQuantidadeProdutoPedido(chave, intQuantidade);
			Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
			model.addAttribute("pedido", pedido);
		}
		
		return "pages/componentes/form-pedido-carrinho";
		
	}
	
	@RequestMapping(value = "/calcular/frete/pedido/carrinho",method = RequestMethod.GET)
	public String calcularFretePedido(@RequestParam("cep") String cep, Model model){
		
		if(cep !=null && !cep.equals("")){
			
			carrihoBusiness.calcularFretePedido(cep);
			Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
			model.addAttribute("pedido", pedido);
		}
		
		return "pages/componentes/form-pedido-carrinho";
		
	}
	
	@RequestMapping(value = "/calcular/frete/pedidoJson",method = RequestMethod.GET)
	@ResponseBody
	@JsonView(Public.class)
	public ValorPedidoFrete calcularFretePedidoJson(@RequestParam("cep") String cep){
		ValorPedidoFrete valorPedidoFrete = new ValorPedidoFrete();
		if(cep !=null && !cep.equals("")){
			
			carrihoBusiness.calcularFretePedido(cep);
			Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
			if(pedido != null){
				valorPedidoFrete.setValorFrete(pedido.getValorFrete());
				valorPedidoFrete.setValorProdutos(pedido.getValorProdutos());
				valorPedidoFrete.setValorTotalPedido(pedido.getValorTotal());
			}
		}

		return valorPedidoFrete;
	}

	@RequestMapping(value = "/excluir/produto/pedido/carrinho",method = RequestMethod.GET)
	public String excluirProdutoPedido(@RequestParam("chave")String chave, Model model){
		
		if(chave !=null && !chave.equals("")){
			
			carrihoBusiness.excluirProdutoPedidoCarrinho(chave); 
			
		}
		
		Pedido pedido = carrihoBusiness.obterPedidoCarrinho();
		model.addAttribute("pedido", pedido);
		model.addAttribute("title", "Carrinho de Compras");
		
		return "pages/componentes/form-pedido-carrinho";
		
	}
	
	private void populateForm(Model model, FormProdutoPedido formProdutoPedido){
		if(formProdutoPedido != null){
			Produto produto = produtoBusiness.pesquisarProduto(formProdutoPedido.getIdProduto());
			List<ProdutoPedido> listaProdutoPedido = formProdutoPedido.getListaProdutoPedido();
			
			for (ProdutoPedido produtoPedido : listaProdutoPedido) {
				produtoPedido.setProduto(produto);
				List<ItemPedidoTamanho> listaItemPedidoTamanho = produtoPedido.getItensPedidoTamanho();
				
				for (ItemPedidoTamanho itemPedidoTamanho : listaItemPedidoTamanho) {
					ItemProduto itemProduto = itemPedidoTamanho.getItemProduto();
					itemProduto = produtoBusiness.pesquisaItemProduto(itemProduto.getId());
					itemPedidoTamanho.setItemProduto(itemProduto);
				}
			
			}
			model.addAttribute("formProdutoPedido",formProdutoPedido);
		}
	}

}
