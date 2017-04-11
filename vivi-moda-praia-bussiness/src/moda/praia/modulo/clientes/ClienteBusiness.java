package moda.praia.modulo.clientes;

import java.util.List;

import moda.praia.modulo.administradores.bean.Adminitrador;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.exceptions.ClienteJahCadastradoException;

public interface ClienteBusiness {

	public boolean cadastrarCliente(Cliente cliente) throws ClienteJahCadastradoException;
	public boolean atualizarCliente(Cliente cliente);
	public boolean login(String nome, String senha);
	public List<Cliente> obterClientes(int posInicial, int quantidadeRegistros);
	public Cliente obterCliente(long id);
	public Cliente obterClientePorEmail(String email);
	
	
}
