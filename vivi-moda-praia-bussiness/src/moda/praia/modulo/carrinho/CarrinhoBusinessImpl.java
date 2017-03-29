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

import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.bean.Produto;

@Service
@Scope(value="session",proxyMode = ScopedProxyMode.INTERFACES)
public class CarrinhoBusinessImpl implements CarrinhoBusiness {

	private Map<String, ProdutoPedido> mapProdutoPedidos = new HashMap<>(); 
	
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
			mapProdutoPedidos.put(String.valueOf(produto.getId()), produtoPedido);
		}
	}

	@Override
	public Pedido obterPedidoCarrinho() {
		
		Pedido pedido = new Pedido();
		
		Set<String> chavesProdutoPedido = mapProdutoPedidos.keySet();
		List<ProdutoPedido> listaProdutoPedido = new ArrayList<ProdutoPedido>();
		BigDecimal totalProdutos = new BigDecimal(0);
		for (String chave : chavesProdutoPedido) {
			ProdutoPedido produtoPedido = mapProdutoPedidos.get(chave);
			listaProdutoPedido.add(produtoPedido);
			totalProdutos = totalProdutos.add(produtoPedido.getValorToral());
		}
		pedido.setProdutosPedido(listaProdutoPedido);
		pedido.setValorProdutos(totalProdutos);
		return pedido;
	}

	@Override
	public boolean excluirProdutoCarrinho(Produto produto) {

		mapProdutoPedidos.remove(produto.getId());
		return true;
	}

	
	@Override
	public void colocarProdutoCarrinho(ProdutoPedido produtoPedido) {
		
		if(produtoPedido != null && produtoPedido.getProduto() != null && produtoPedido.getQuantidade() !=0){
			String chave = obterChaveProdutoPedido(produtoPedido);
			produtoPedido.setChave(chave);
			mapProdutoPedidos.put(chave, produtoPedido);
		}
		
	}
	
	private String obterChaveProdutoPedido(ProdutoPedido produtoPedido){
		
		StringBuilder sbChave = new StringBuilder();
		
		if(produtoPedido != null && produtoPedido.getProduto() != null && produtoPedido.getQuantidade() !=0){
			sbChave.append(produtoPedido.getProduto().getId());
			List<ItemPedidoTamanho> listaTamanhoPedido  = produtoPedido.getItensPedidoTamanho();
			for (ItemPedidoTamanho itemPedidoTamanho : listaTamanhoPedido) {
				sbChave.append(itemPedidoTamanho.getTamanho());
			}
			
		}
		
		return sbChave.toString();
		
	}

	@Override
	public void mudarQuantidadeProdutoPedido(String chave, int quantidade) {

		ProdutoPedido produtoPedido = mapProdutoPedidos.get(chave);
		produtoPedido.setQuantidade(quantidade);
		
		BigDecimal total = new BigDecimal(0);
		total = total.add(produtoPedido.getValorUnitario()).multiply(new BigDecimal(quantidade));
		produtoPedido.setValorToral(total);
		
		
	}

	@Override
	public void excluirProdutoPedidoCarrinho(String chave) {

		mapProdutoPedidos.remove(chave);
	}

}
