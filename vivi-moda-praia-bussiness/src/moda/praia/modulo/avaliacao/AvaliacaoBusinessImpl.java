package moda.praia.modulo.avaliacao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import moda.praia.modulo.avaliacao.bean.Avaliacao;
import moda.praia.modulo.avaliacao.repositorios.AvaliacaoRepository;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.repositorios.ClienteRepository;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.repositorios.ProdutoRepository;

@Service
public class AvaliacaoBusinessImpl implements AvaliacaoBusiness {
	
	private final AvaliacaoRepository avaliacaoRepository;
	private final ProdutoRepository produtoRepository;
	private final ClienteRepository clienteRepository;
	
	private final Logger log = Logger.getLogger(AvaliacaoBusinessImpl.class);
	@Autowired
	public AvaliacaoBusinessImpl(AvaliacaoRepository avaliacaoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
		this.produtoRepository = produtoRepository;
		this.clienteRepository = clienteRepository;
	}
	
	@Override
	public boolean salvarAvaliacao(Avaliacao avaliacao) {
		try{
			if(avaliacao != null && avaliacao.getProduto() != null && avaliacao.getProduto().getId() != 0 && avaliacao.getCliente() != null && avaliacao.getCliente().getId() !=0){
				Produto produto = produtoRepository.findById(avaliacao.getProduto().getId());
				Cliente cliente = clienteRepository.findById(avaliacao.getCliente().getId());
				if(produto != null){
					double mediaAvaliacao = produto.getMediaAvaliacao();
					int totalAvaliacao = produto.getQuantidadeAvaliacao();
					double somaAvaliacao =  mediaAvaliacao * totalAvaliacao;
					totalAvaliacao ++;
					somaAvaliacao =  somaAvaliacao + avaliacao.getNota();
					double mediaAvaliacaoNova = somaAvaliacao / totalAvaliacao;
					produto.setMediaAvaliacao(mediaAvaliacaoNova);
					produto.setQuantidadeAvaliacao(totalAvaliacao);
					avaliacao.setCliente(cliente);
					produtoRepository.save(produto);
					avaliacaoRepository.save(avaliacao);
					return true;
				}
				
			}

			return false;
		}catch(Exception e){
			log.error("erro ao tentar salvar a avaliacao" + e);
			return false;
		}
	}

	@Override
	public Page<Avaliacao> avaliacoes(Integer pagina, Produto produto) {
		
		try{
			PageRequest request = new PageRequest(pagina - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
			return avaliacaoRepository.findByProduto(request, produto);
			
		}catch(Exception e){
			log.error("erro ao buscar avaliacoes" + e);
			
			return null;
		}
	}
	
	private final static int PAGE_SIZE = 3;

}
