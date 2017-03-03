package moda.praia.modulo.estoque;

import org.springframework.data.domain.Page;

import moda.praia.modulo.produtos.bean.Produto;

public interface EstoqueBusiness {

	public boolean entrarProdutoEstoque(Produto produto, Estoque estoque);
	public Page<Produto> listaProdutoEstoque(Integer pageNumber);
	public Produto visualizarProdutoEstoque(long idProduto);
	
}
