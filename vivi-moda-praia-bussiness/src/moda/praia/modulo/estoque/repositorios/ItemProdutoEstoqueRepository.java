package moda.praia.modulo.estoque.repositorios;

import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemProdutoEstoqueRepository extends JpaRepository<ItemProdutoEstoque,Long> {
	
	public ItemProdutoEstoque findById(long id);

}
