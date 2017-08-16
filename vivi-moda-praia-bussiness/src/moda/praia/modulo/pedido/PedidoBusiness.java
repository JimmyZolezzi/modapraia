package moda.praia.modulo.pedido;

import org.springframework.data.domain.Page;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.StatusPedido;
import moda.praia.modulo.produtos.bean.Produto;


public interface PedidoBusiness {
	
	public void calcularFretePedido(Pedido pedido);
	public void mudarStatusPedido(Pedido pedido, StatusPedido statusPedido);
	public void addProdutoPedido(Pedido pedido, Produto produto);
	public void excluirProdutoPedido(Pedido pedido, Produto produto);
	public boolean finalizarPedido(Pedido pedido);
	public Page<Pedido> buscarPedidosPorCliente(Cliente cliente, Integer pageNumber);
	public Pedido bucarPedidoPorId(long id);
	
	

}
