package moda.praia.modulo.clientes;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.exceptions.ClienteJahCadastradoException;
import moda.praia.modulo.clientes.repositorios.ClienteRepository;
import moda.praia.uteis.Criptografia;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class ClienteBusinessImpl implements ClienteBusiness{
	
	private final Logger log = Logger.getLogger(ClienteBusinessImpl.class);
	private final ClienteRepository clienteRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	public ClienteBusinessImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Override
	public boolean cadastrarCliente(Cliente cliente) throws ClienteJahCadastradoException{
			
			if(cliente != null && cliente.getEmail() != null && !cliente.getEmail().equals("")){
				String email = cliente.getEmail();
				Cliente clienteExistente = clienteRepository.findByEmail(email);
				//Criptografa a senha
				
				if(clienteExistente == null){
					if(cliente.getSenha() != null){
						
						try{
							String senhaCriptografada = passwordEncoder.encode(cliente.getSenha()); 
							cliente.setSenha(senhaCriptografada);
							cliente.setStatusCliente(Cliente.ATIVO);
							clienteRepository.save(cliente);

							return true;
						
						}catch(Exception e){
							log.error("Erro ao tentar cadastrar o cliente " + e.getMessage());
						
						}
					}
					
				}else{
					
					throw new ClienteJahCadastradoException();
				}
				
			}
			
		
		
		return false;
	}

	@Override
	public boolean atualizarCliente(Cliente cliente) {

		try{
			clienteRepository.save(cliente);
			return true;
			
		}catch(Exception e){
			log.error("Erro ao tentar atualizar o cliente " + e.getMessage());
		
		}
		
		return false;
		
	}

	@Override
	public boolean login(String nome, String senha) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> obterClientes(int posInicial, int quantidadeRegistros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente obterCliente(long id) {

		try{
			return clienteRepository.findById(id);
			
		}catch(Exception e){
		
			log.error("Erro ao tentar obter o cliente " + e.getMessage());
			return null;
		}
	}

	@Override
	public Cliente obterClientePorEmail(String email) {
		

		try{
			return clienteRepository.findByEmail(email);
			
		}catch(Exception e){
		
			log.error("Erro ao tentar obter o cliente por email " + e.getMessage());
			return null;
		}
	}

	@Override
	public void removerCliente(Cliente cliente) {
		
		try{
			clienteRepository.delete(cliente);
			
		}catch(Exception e){
		
			log.error("Erro ao tentar remover o cliente " + e.getMessage());
		}
	}

}
