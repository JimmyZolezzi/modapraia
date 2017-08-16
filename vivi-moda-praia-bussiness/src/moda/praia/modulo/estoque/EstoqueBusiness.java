package moda.praia.modulo.estoque;

import org.springframework.data.domain.Page;

import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;
import moda.praia.modulo.produtos.bean.Produto;

public interface EstoqueBusiness {

	public boolean entrarProdutoEstoque(Produto produto, ItemProduto itemProduto, ItemProdutoEstoque itemProdutoEstoque, int quantidadeEstoque);
	public DisponibilidadeEstoque reservarProdutoEstoque(Produto produto, ItemProdutoEstoque itemProdutoEstoque, int quantidadeReserva);
	public Page<Produto> listaProdutoEstoque(Integer pageNumber);
	public Produto visualizarProdutoEstoque(long idProduto);
	public ItemProduto carregarItemProduto(long itemProduto);
	public ItemProdutoEstoque carregarItemProdutoPorIdTamanho(long itemProduto, String tamanho);
	
}
