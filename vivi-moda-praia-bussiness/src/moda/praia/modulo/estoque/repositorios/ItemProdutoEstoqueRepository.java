package moda.praia.modulo.estoque.repositorios;

import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemProdutoEstoqueRepository extends JpaRepository<ItemProdutoEstoque,Long> {
	
	public ItemProdutoEstoque findById(long id);
	@Query("select ipe from ItemProdutoEstoque ipe  where ipe.id = ?1 and ipe.tamanho = ?2")
	public ItemProdutoEstoque findBydIdAndTamanho(long id, String tamanho); 

}
