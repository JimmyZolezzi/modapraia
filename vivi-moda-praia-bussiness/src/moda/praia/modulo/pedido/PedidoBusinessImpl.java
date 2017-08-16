package moda.praia.modulo.pedido;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.estoque.DisponibilidadeEstoque;
import moda.praia.modulo.estoque.EstoqueBusiness;
import moda.praia.modulo.estoque.exceptions.ProdutoIndisponivelException;
import moda.praia.modulo.estoque.exceptions.SaveReservaProdutoException;
import moda.praia.modulo.estoque.repositorios.ItemProdutoEstoqueRepository;
import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.pedido.bean.StatusPedido;
import moda.praia.modulo.pedido.repositorios.PedidoRepository;
import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.modulo.produtos.repositorios.ProdutoRepository;

@Service
public class PedidoBusinessImpl implements PedidoBusiness {
	
	private static final int PAGE_SIZE = 10;
	
	private final Logger log = Logger.getLogger(PedidoBusiness.class);
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private EstoqueBusiness estoqueBusiness;
	
	@Override
	public void calcularFretePedido(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mudarStatusPedido(Pedido pedido, StatusPedido statusPedido) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addProdutoPedido(Pedido pedido, Produto produto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirProdutoPedido(Pedido pedido, Produto produto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean finalizarPedido(Pedido pedido) {
		
		pedido.setDataPedido(new Date());
		pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
		DisponibilidadeEstoque disponibilidade =  reservarProdutosPedido(pedido);
		if(disponibilidade.equals(DisponibilidadeEstoque.SUCESSO)){
			pedidoRepository.save(pedido);
			
			return true;
		}
			
		return false;
	}
	
	private DisponibilidadeEstoque reservarProdutosPedido(Pedido pedido){
		DisponibilidadeEstoque disponibilidade = null;
		List<ProdutoPedido> produtosPedido = pedido.getProdutosPedido();
		
		for (ProdutoPedido produtoPedido : produtosPedido) {
			List<ItemPedidoTamanho> itensPedido = produtoPedido.getItensPedidoTamanho();
			for (ItemPedidoTamanho itemPedidoTamanho : itensPedido) {
				String chave = itemPedidoTamanho.getTamanho();
				Map<String, ItemProdutoEstoque> mapItemProdutoEstoque = itemPedidoTamanho.getItemProduto().getMapItemProdutoEstoque();
				
				if(mapItemProdutoEstoque != null){
					ItemProdutoEstoque itemProdutoEstoque = mapItemProdutoEstoque.get(chave);
					
					if(itemProdutoEstoque != null){
						disponibilidade = estoqueBusiness.reservarProdutoEstoque(produtoPedido.getProduto(), itemProdutoEstoque,(int) produtoPedido.getQuantidade());
						if(disponibilidade.equals(DisponibilidadeEstoque.QUANTIDADE_PRODUTO_INDISPONIVEL)){
							throw new ProdutoIndisponivelException();
						}
						if(disponibilidade.equals(DisponibilidadeEstoque.ERRO)){
							throw new SaveReservaProdutoException();
						}
					}
					
				}
				
			}
		}
		if(disponibilidade == null){
			throw new ProdutoIndisponivelException();
		}
		return disponibilidade;
	}

	@Override
	public Page<Pedido> buscarPedidosPorCliente(Cliente cliente, Integer pageNumber) {
		
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		Page<Pedido> page = pedidoRepository.findByCliente(request, cliente);
		
		return page;
	}

	@Override
	public Pedido bucarPedidoPorId(long id) {
		
		try{
			return pedidoRepository.findById(id);
			
		}catch(Exception e){
			log.error("Erro ao buscar o pedido por id: " + e);
		}
		
		return null;
	}

}
