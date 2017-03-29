package moda.praia.web.controller;

import java.math.BigDecimal;

import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.ImagemProduto;
import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.web.controller.editor.ImagemProdutoEditor;
import moda.praia.web.controller.editor.ProdutoEditor;
import moda.praia.web.controller.form.FormPedido;
import moda.praia.web.controller.form.FormProdutoPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
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

		populateForm(model, idProduto,1);
		
		return "pages/produto-info";
	}
	 
	
	@RequestMapping(value = "/add/produto/pedido", method = RequestMethod.GET)
	public String addProdutoPedido(@RequestParam("idProduto") String idProduto, @RequestParam("quantidade") String quantidade,  Model model){
		
		if(idProduto != null && idProduto.matches("[0-9].*") && quantidade != null && quantidade.matches("[0-9].*")){
			long idProdutoLong = Long.valueOf(idProduto);
			int quantidadeLong = Integer.valueOf(quantidade);
			populateForm(model, idProdutoLong, quantidadeLong);
		}
		
		
		return "pages/componentes/form-entrada-produto-carrinho";
	}
	
	private void populateForm(Model model, long idProduto, int quantidadeProdutoPedido){
		
		FormProdutoPedido formProdutoPedido = new FormProdutoPedido();
		formProdutoPedido.setQuantidade(quantidadeProdutoPedido);
		Produto produto = produtoBusiness.pesquisarProduto(idProduto);

		for (int i = 0;i<quantidadeProdutoPedido;i++) {
			ProdutoPedido produtoPedido = new ProdutoPedido();
			
			//obtem o primeiro produto
			if(produto.getItensProduto() != null){
				
				for (ItemProduto itemProduto : produto.getItensProduto()) {

					ItemPedidoTamanho itemPedidoTamanho = new ItemPedidoTamanho();
					itemPedidoTamanho.setNome(itemProduto.getNome());
					itemPedidoTamanho.setItemProduto(itemProduto);
					produtoPedido.getItensPedidoTamanho().add(itemPedidoTamanho);
				
				}
			}
			
			produtoPedido.setProduto(produto);
			produtoPedido.setValorUnitario(produto.getValor());
			BigDecimal valorTotal = produto.getValor().multiply(new BigDecimal(1));
			produtoPedido.setValorToral(valorTotal);
			formProdutoPedido.getListaProdutoPedido().add(produtoPedido);
			
		}
		
		model.addAttribute("produto", produto);
		model.addAttribute("title", produto.getDescricao());
		formProdutoPedido.setIdProduto(idProduto);
		model.addAttribute("formProdutoPedido",formProdutoPedido);
		
	}
	

}
