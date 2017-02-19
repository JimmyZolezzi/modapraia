package moda.praia.modulo.pedido;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.StatusPedido;
import moda.praia.modulo.produtos.bean.Produto;


public interface PedidoBusiness {
	
	public void calcularFretePedido(Pedido pedido);
	public void mudarStatusPedido(Pedido pedido, StatusPedido statusPedido);
	public void realizarPedido(Pedido pedido, Cliente cliente);
	public void addProdutoPedido(Pedido pedido, Produto produto);
	public void excluirProdutoPedido(Pedido pedido, Produto produto);

}
