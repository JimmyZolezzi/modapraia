package moda.praia.modulo.avaliacao;

import org.springframework.data.domain.Page;

import moda.praia.modulo.avaliacao.bean.Avaliacao;
import moda.praia.modulo.produtos.bean.Produto;

public interface AvaliacaoBusiness {

	public boolean salvarAvaliacao(Avaliacao avaliacao);
	public Page<Avaliacao> avaliacoes(Integer pagina, Produto produto);
}
