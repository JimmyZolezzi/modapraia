package test.json;

import java.util.Calendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.bean.TipoCliente;
import moda.praia.modulo.endereco.Endereco;

public class ClienteJson {

	public static void main(String[] args) throws JsonProcessingException {
		
		Cliente cliente = new Cliente();
		cliente.setContato("Jimmy");
		cliente.setCpf("36816511832");
		cliente.setRg("442490896");
		cliente.setTelefone("1139432495");
		cliente.setCelular("962124711");
		cliente.setId(1);
		cliente.setDataNascimento(Calendar.getInstance());
		cliente.setObservacao("Cliente Especial");
		cliente.setTipoCliente(TipoCliente.FISICO);
		cliente.setStatusCliente("ativo");
		Endereco enderecoCliente = new Endereco();
		enderecoCliente.setBairro("Jardim Marilu");
		enderecoCliente.setCep("02989010");
		enderecoCliente.setEstado("SP");
		enderecoCliente.setCidade("São Paulo");
		enderecoCliente.setNumero("9");
		
		Endereco enderecoEntrega = new Endereco();
		enderecoEntrega.setBairro("Jardim Marilu");
		enderecoEntrega.setCep("02989010");
		enderecoEntrega.setEstado("SP");
		enderecoEntrega.setCidade("São Paulo");
		enderecoEntrega.setNumero("9");
		
		cliente.setEnderecoCliente(enderecoCliente);
		cliente.setEnderecoEntrega(enderecoEntrega);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonCliente = mapper.writeValueAsString(cliente);
		System.out.println(jsonCliente);
		
	}
}
