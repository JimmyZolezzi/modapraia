package moda.praia.modulo.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.repositorios.ClienteRepository;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.repositorios.PedidoRepository;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.uteis.Criptografia;

@Service
@Scope(value="session",proxyMode = ScopedProxyMode.INTERFACES)
public class LoginClienteImpl implements LoginCliente {

	private final static int PAGE_SIZE_PEDIDO = 10;
	private final Logger log = Logger.getLogger(LoginClienteImpl.class);
	
	private final ClienteRepository clienteRepository;
	private final PedidoRepository pedidoRepository;
	
	@Autowired
	public LoginClienteImpl(ClienteRepository clienteRepository, PedidoRepository pedidoRepository){
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
	}
	
	private boolean clienteLogado = false;
	private Cliente cliente;

	@Override
	public boolean login(String email, String senha) {
		
		if(email !=null && senha != null && !email.trim().equals("") && !senha.trim().equals("")){

			Cliente cliente = clienteRepository.findByEmail(email);
			if(cliente != null ){
				senha = Criptografia.criptografaSenha(senha);
				this.cliente = cliente;
				if(senha.equals(cliente.getSenha())){
					clienteLogado = true;
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public Page<Pedido> obterPedidosCliente(Integer pageNumber) {
		PageRequest pageable = new PageRequest(pageNumber - 1, PAGE_SIZE_PEDIDO, Sort.Direction.DESC, "id");
		try{
			return pedidoRepository.findByCliente(pageable, cliente);
			
		}catch (Exception e) {

			log.error(e.getMessage());
			
			return null;
		}

	}

	@Override
	public boolean verificaClienteLogado() {

		return clienteLogado;
	}

	@Override
	public Page<Produto> produtoDesejados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente obterDadosCadastrais() {

		return cliente;
	}

	@Override
	public boolean atualizarDadosCadastrais(Cliente cliente) {

		try{
			clienteRepository.save(cliente);
			this.cliente = cliente;
			return true;
			
		}catch(Exception e){
			log.error("Erro ao tentar salvar o cliente" + e.getMessage());
			return false;
		}
	}

	@Override
	public void logout() {
		this.cliente = null;
		this.clienteLogado = false;
	}

}
