package moda.praia.modulo.carrinho;

import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.bean.Produto;

public interface CarrinhoBusiness {
	
	public void colocarProdutoCarrinho(ProdutoPedido produtoPedido);
	public void mudarQuantidadeProdutoPedido(String chave, int quantidade);
	public void excluirProdutoPedidoCarrinho(String chave);
	public void colocarProdutoCarrinho(Produto produto, int quantidade);
	public boolean excluirProdutoCarrinho(Produto produto);
	public Pedido obterPedidoCarrinho();
	
}
