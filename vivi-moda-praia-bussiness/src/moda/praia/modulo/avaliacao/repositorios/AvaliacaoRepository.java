package moda.praia.modulo.avaliacao.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moda.praia.modulo.avaliacao.bean.Avaliacao;
import moda.praia.modulo.produtos.bean.Produto;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {

	@Query("select a from Avaliacao a where a.produto = ?1 order by a.id desc")
	public Page<Avaliacao> findByProduto(Pageable pageable, Produto produto);
}
