package moda.praia.modulo.estoque;


import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moda.praia.modulo.estoque.repositorios.HistoricoMovimentacaEstoqueRepository;
import moda.praia.modulo.estoque.repositorios.ItemProdutoEstoqueRepository;
import moda.praia.modulo.produtos.repositorios.ItemProdutoRepository;
import moda.praia.modulo.produtos.bean.ItemProduto;
import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.repositorios.ProdutoRepository;

@Service
public class EstoqueBusinessImpl implements EstoqueBusiness {
	
	private final Logger log = Logger.getLogger(EstoqueBusiness.class);
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemProdutoRepository itemProdutoRepository;
	@Autowired
	private ItemProdutoEstoqueRepository itemProdutoEstoqueRepository;
	@Autowired
	private HistoricoMovimentacaEstoqueRepository historicoMovimentacaEstoqueRepository;
	
	
	@Transactional
	@Override
	public boolean entrarProdutoEstoque(Produto produto,ItemProduto itemProduto, ItemProdutoEstoque itemProdutoEstoque, int quantidadeEstoque) {
		synchronized(produto){
			
			try{
				if(itemProduto!=null && itemProdutoEstoque != null){
					ItemProdutoEstoque itemProdutoArmazenado = procurarItemProdutoEstoque(itemProduto, itemProdutoEstoque);
					int quantidadeEntradaEstoque = 0 ;
					if(itemProdutoArmazenado != null){
						quantidadeEntradaEstoque = itemProdutoArmazenado.getQuantidade() + quantidadeEstoque;
						itemProdutoArmazenado.setQuantidade(quantidadeEntradaEstoque);
					}else{
						quantidadeEntradaEstoque = quantidadeEstoque;
						itemProdutoEstoque.setQuantidade(quantidadeEntradaEstoque);
						if(itemProduto.getMapItemProdutoEstoque() == null){
							itemProduto.setMapItemProdutoEstoque(new HashMap<String,ItemProdutoEstoque>());
						}
						itemProduto.getMapItemProdutoEstoque().put(itemProdutoEstoque.getTamanho(), itemProdutoEstoque);
						
						/*
						if(itemProduto.getItensEstoque()==null){
							List<ItemProdutoEstoque> lista = new ArrayList<>();
							itemProduto.setItensEstoque(lista);
						}
						itemProduto.getItensEstoque().add(itemProdutoEstoque);
						*/
					}
					itemProdutoRepository.save(itemProduto);
					//grava historico
					HistoricoMovimentacaoEstoque historicoMovimentacaoEstoque = new HistoricoMovimentacaoEstoque();
					//data atual
					historicoMovimentacaoEstoque.setData(new GregorianCalendar());
					historicoMovimentacaoEstoque.setProduto(produto);
					historicoMovimentacaoEstoque.setQuantidade(quantidadeEstoque);
					historicoMovimentacaEstoqueRepository.save(historicoMovimentacaoEstoque);
					
					//verifica disponibilidade do produto
					if(!produto.isDisponivelEstoque()){
						//caso nao esteja disponivel verifica se a quantidade dos itens em estoque maior que zero
						if(verificadisponibilidadeItensProdutoEstoque(produto)){
							produto.setDisponivelEstoque(true);
							produtoRepository.save(produto);
						}
					}
					return true;
				}
				
			}catch(Exception e){
				log.error("erro na entrada de produto em estoque");
			}
			
		}
		
		
		return false;
	}
	
	private boolean verificadisponibilidadeItensProdutoEstoque(Produto produto){
		if(produto != null){
			produto = produtoRepository.findById(produto.getId());
		}
		boolean disponivel = true;
		if(produto != null && produto.getItensProduto() != null){
			List<ItemProduto> listaItemProduto = produto.getItensProduto();
			for (ItemProduto itemProduto : listaItemProduto) {
				boolean temProdutoEstoque = false;
				
				Map<String,ItemProdutoEstoque> mapItemProdutoEstoque = itemProduto.getMapItemProdutoEstoque();
				
				Set<String> chavesProdutoEstoque = mapItemProdutoEstoque.keySet();
				
				for (String chave : chavesProdutoEstoque) {
					
					ItemProdutoEstoque itemProdutoEstoque = mapItemProdutoEstoque.get(chave);
					if(itemProdutoEstoque != null){
						long quantidadeDisponivel = itemProdutoEstoque.getQuantidade() - itemProdutoEstoque.getQuantidadeReservada();
						if(quantidadeDisponivel > 0){
							temProdutoEstoque = true;
						}
					}

					if(temProdutoEstoque){
						break;
					}
				}
				
				/*
				List<ItemProdutoEstoque> listaItemProdutoEstoque = itemProduto.getItensEstoque();
				for (ItemProdutoEstoque itemProdutoEstoque : listaItemProdutoEstoque) {
					if(itemProdutoEstoque.getQuantidade() > 0){
						temProdutoEstoque = true;
					}
					
					if(temProdutoEstoque){
						break;
					}
				}
				*/
				if(!temProdutoEstoque){
					disponivel = false;
					break;
				}
			}
		}
		
		return disponivel;
	}
	private ItemProdutoEstoque procurarItemProdutoEstoque(ItemProduto itemProduto, ItemProdutoEstoque itemProdutoEstoque){
		if(itemProdutoEstoque != null && itemProduto != null){
			String chave = itemProdutoEstoque.getTamanho();
			Map<String , ItemProdutoEstoque> mapItemProdutoEstoque =  itemProduto.getMapItemProdutoEstoque();
			if(mapItemProdutoEstoque != null){
				ItemProdutoEstoque item = mapItemProdutoEstoque.get(chave);
				return item;
			}
		}
		
		/*
		List<ItemProdutoEstoque> itensEstoque = itemProduto.getItensEstoque();
		
		
		for (ItemProdutoEstoque item : itensEstoque) {
			if(item.getTamanho() != null && item.getTamanho().equals(itemProdutoEstoque.getTamanho())){
				return item;
			}
		}
		*/
		return null;
	}

	
	@Override
	public Page<Produto> listaProdutoEstoque(Integer pageNumber) {
	        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "descricao");
	        return produtoRepository.findAll(request);
	}


	@Override
	public Produto visualizarProdutoEstoque(long idProduto) {
		
		return produtoRepository.findById(idProduto);
		
	}


	@Override
	public ItemProduto carregarItemProduto(long itemProduto) {
		
		return itemProdutoRepository.findById(itemProduto);
		
	}

	@Override
	public ItemProdutoEstoque carregarItemProdutoPorIdTamanho(long idItemProduto, String tamanho) {
		
		return  itemProdutoEstoqueRepository.findBydIdAndTamanho(idItemProduto, tamanho);
	}

	@Override
	public DisponibilidadeEstoque reservarProdutoEstoque(Produto produto, ItemProdutoEstoque itemProdutoEstoque, int quantidadeReserva) {
		
		synchronized (produto) {
			
			if(itemProdutoEstoque != null){
				long quantidadeReservadaAtual = itemProdutoEstoque.getQuantidadeReservada();
				long quantidadeReservadaPedido = (long) quantidadeReserva;
				quantidadeReservadaAtual = quantidadeReservadaAtual + quantidadeReservadaPedido;
				if(quantidadeReservadaAtual > itemProdutoEstoque.getQuantidade()){
					return DisponibilidadeEstoque.QUANTIDADE_PRODUTO_INDISPONIVEL;
				}else{
					
					itemProdutoEstoque.setQuantidadeReservada((int)quantidadeReservadaAtual);
					itemProdutoEstoqueRepository.save(itemProdutoEstoque);
					boolean disponivel = verificadisponibilidadeItensProdutoEstoque(produto);
					produto.setDisponivelEstoque(disponivel);
					try{
						
						produtoRepository.save(produto);
						return DisponibilidadeEstoque.SUCESSO;
						
					}catch(Exception e){
						log.error("erro ao tentar salvar a reserva " + e);
						return DisponibilidadeEstoque.ERRO;
					}
				}
				
			}
		}
		
		return DisponibilidadeEstoque.QUANTIDADE_PRODUTO_INDISPONIVEL;
	}
	

}
