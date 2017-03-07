package moda.praia.modulo.estoque.repositorios;

import moda.praia.modulo.estoque.HistoricoMovimentacaoEstoque;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMovimentacaEstoqueRepository extends JpaRepository<HistoricoMovimentacaoEstoque,Long> {

}
