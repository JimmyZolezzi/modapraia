package moda.praia.modulo.produtos.repositorios;

import moda.praia.modulo.produtos.bean.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@org.springframework.stereotype.Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{

	public Page<Produto> findAll(Pageable pageable);
	public Produto findById(long id);
	public Page<Produto> findByDestaque(boolean destaque, Pageable pageable);

}
