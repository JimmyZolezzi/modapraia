package moda.praia.modulo.login;

import org.springframework.data.domain.Page;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.produtos.bean.Produto;

public interface LoginCliente {
	
	
	public boolean login(String email, String senha);
	public void logout();
	public Page<Pedido> obterPedidosCliente(Integer pageNumber);
	public boolean verificaClienteLogado();
	public Page<Produto> produtoDesejados();
	public Cliente obterDadosCadastrais();
	public boolean atualizarDadosCadastrais(Cliente cliente);

}
