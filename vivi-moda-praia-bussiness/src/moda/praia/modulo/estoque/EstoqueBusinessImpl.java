package moda.praia.modulo.estoque;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moda.praia.modulo.estoque.repositorios.HistoricoMovimentacaEstoqueRepository;
import moda.praia.modulo.estoque.repositorios.ItemProdutoRepository;
import moda.praia.modulo.produtos.ProdutoBusinessImpl;
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
	private HistoricoMovimentacaEstoqueRepository historicoMovimentacaEstoqueRepository;
	
	
	@Transactional
	@Override
	public boolean entrarProdutoEstoque(Produto produto,ItemProduto itemProduto, ItemProdutoEstoque itemProdutoEstoque, int quantidadeEstoque) {

		try{
			if(itemProduto!=null && itemProdutoEstoque != null){
				ItemProdutoEstoque itemProdutoArmazenado = procurarItemProdutoEstoque(itemProduto, itemProdutoEstoque);
				int quantidadeEntradaEstoque = 0 ;
				if(itemProdutoArmazenado != null){
					quantidadeEntradaEstoque = itemProdutoArmazenado.getQuantidade() + quantidadeEstoque;
				}else{
					quantidadeEntradaEstoque = quantidadeEstoque;
					itemProdutoEstoque.setQuantidade(quantidadeEntradaEstoque);
					if(itemProduto.getItensEstoque()==null){
						List<ItemProdutoEstoque> lista = new ArrayList<>();
						itemProduto.setItensEstoque(lista);
					}
					itemProduto.getItensEstoque().add(itemProdutoEstoque);
				}
				itemProdutoRepository.save(itemProduto);
				//grava historico
				HistoricoMovimentacaoEstoque historicoMovimentacaoEstoque = new HistoricoMovimentacaoEstoque();
				//data atual
				historicoMovimentacaoEstoque.setData(new GregorianCalendar());
				historicoMovimentacaoEstoque.setProduto(produto);
				historicoMovimentacaoEstoque.setQuantidade(quantidadeEstoque);
				historicoMovimentacaEstoqueRepository.save(historicoMovimentacaoEstoque);
				
			}
			
		}catch(Exception e){
			log.error("erro na entrada de produto em estoque");
		}
		
		return false;
	}
	
	private ItemProdutoEstoque procurarItemProdutoEstoque(ItemProduto itemProduto, ItemProdutoEstoque itemProdutoEstoque){
		List<ItemProdutoEstoque> itensEstoque = itemProduto.getItensEstoque();
		
		
		for (ItemProdutoEstoque item : itensEstoque) {
			if(item.getTamanho() != null && item.getTamanho().equals(itemProdutoEstoque.getTamanho())){
				return itemProdutoEstoque;
			}
		}
		
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
	

}
