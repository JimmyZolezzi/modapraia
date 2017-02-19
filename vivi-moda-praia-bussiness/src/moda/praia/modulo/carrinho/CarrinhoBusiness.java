package moda.praia.modulo.carrinho;

import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.produtos.bean.Produto;

public interface CarrinhoBusiness {
	
	public void colocarProdutoCarrinho(Produto produto, int quantidade);
	public boolean excluirProdutoCarrinho(Produto produto);
	public Pedido obterPedidoCarrinho();
	
}
