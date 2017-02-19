package moda.praia.modulo.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.bean.Produto;

@Service
@Scope(value="session",proxyMode = ScopedProxyMode.INTERFACES)
public class CarrinhoBusinessImpl implements CarrinhoBusiness {

	private Map<Long, ProdutoPedido> mapProdutoPedidos = new HashMap<>(); 
	
	@Override
	public void colocarProdutoCarrinho(Produto produto, int quantidade) {
		
		if(produto != null && produto.getValor() != null && quantidade !=0){
			ProdutoPedido produtoPedido = new ProdutoPedido();
			produtoPedido.setProduto(produto);
			produtoPedido.setQuantidade(quantidade);
			produtoPedido.setValorUnitario(produto.getValor());
			BigDecimal valorTotal = BigDecimal.ZERO;
			valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
			produtoPedido.setValorToral(valorTotal);
			mapProdutoPedidos.put(produto.getId(), produtoPedido);
		}
	}

	@Override
	public Pedido obterPedidoCarrinho() {
		
		Pedido pedido = new Pedido();
		
		Set<Long> chavesProdutoPedido = mapProdutoPedidos.keySet();
		List<ProdutoPedido> listaProdutoPedido = new ArrayList<ProdutoPedido>();
		for (long chave : chavesProdutoPedido ) {
			ProdutoPedido produtoPedido = mapProdutoPedidos.get(chave);
			listaProdutoPedido.add(produtoPedido);
		}
		pedido.setProdutosPedido(listaProdutoPedido);
		
		return pedido;
	}

	@Override
	public boolean excluirProdutoCarrinho(Produto produto) {

		mapProdutoPedidos.remove(produto.getId());
		return true;
	}

}
