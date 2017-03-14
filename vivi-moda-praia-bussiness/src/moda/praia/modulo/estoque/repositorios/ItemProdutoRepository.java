package moda.praia.modulo.estoque.repositorios;

import moda.praia.modulo.produtos.bean.ItemProduto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto,Long> {

	
	public ItemProduto findById(long id);
	
}
