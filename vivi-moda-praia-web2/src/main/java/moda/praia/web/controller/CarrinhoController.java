package moda.praia.web.controller;

import moda.praia.modulo.carrinho.CarrinhoBusiness;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.web.controller.editor.ProdutoEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
@Scope("session")
public class CarrinhoController {

	@Autowired
	private CarrinhoBusiness carrihoBusiness;
	@Autowired
	private ProdutoEditor produtoEditor;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Produto.class, produtoEditor);
	}
	
	@ResponseBody @RequestMapping(value="produto/add/carrinho",method=RequestMethod.GET)
	public Pedido colocarProdutoCarrinho(@RequestParam("idProduto") Produto produto,@RequestParam("quantidade") int quantidade){

		carrihoBusiness.colocarProdutoCarrinho(produto, quantidade);
		
		return carrihoBusiness.obterPedidoCarrinho();
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

}
