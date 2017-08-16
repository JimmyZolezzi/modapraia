package test.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.clientes.bean.TipoCliente;
import moda.praia.modulo.endereco.Endereco;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.pedido.bean.StatusPedido;
import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Produto;

public class PedidoJson {

	public static void main(String[] args) throws JsonProcessingException {
		
		Pedido pedido = new Pedido();
		pedido.setCliente(mockCliente());
		pedido.setEnderecoEntrega(pedido.getCliente().getEnderecoEntrega());
		pedido.setObservacao("Proximo aos supermercado Wallmart");
		pedido.setProdutosPedido(listaProdutoPedido());
		pedido.setStatusPedido(StatusPedido.REALIZADO);
		pedido.setValorFrete(new BigDecimal(10));
		pedido.setValorProdutos(new BigDecimal(50));
		pedido.setValorTotal(new BigDecimal(60));
		pedido.setId(2);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonPedido = mapper.writeValueAsString(pedido);
		System.out.println(jsonPedido);
		
		
	}
	
	private static List<ProdutoPedido> listaProdutoPedido(){
		
		List<ProdutoPedido> lista = new ArrayList<ProdutoPedido>();
		//produto 1
		ProdutoPedido produtoPedido1 = new ProdutoPedido();
		Produto produto1 = new Produto();
		produto1.setDescricao("Camisa");
		Categoria categoria1 = new Categoria();
		categoria1.setId(2);
		categoria1.setDescricao("Roupa");
		produto1.setCategoria(categoria1);
		produtoPedido1.setId(1);
		produtoPedido1.setProduto(produto1);
		produtoPedido1.setQuantidade(2);
		produtoPedido1.setValorUnitario(new BigDecimal(10));
		produtoPedido1.setValorTotal(new BigDecimal(20));
		lista.add(produtoPedido1);
		//produto 2
		ProdutoPedido produtoPedido2 = new ProdutoPedido();
		Produto produto2 = new Produto();
		produto2.setDescricao("Vestido");
		Categoria categoria2 = new Categoria();
		categoria2.setId(3);
		categoria2.setDescricao("Malha");
		produto1.setCategoria(categoria2);
		produtoPedido2.setId(2);
		produtoPedido2.setProduto(produto2);
		produtoPedido2.setQuantidade(3);
		produtoPedido2.setValorUnitario(new BigDecimal(10));
		produtoPedido2.setValorTotal(new BigDecimal(30));
		lista.add(produtoPedido2);
		
		
		return lista;
		
	}
	
	private static Cliente mockCliente(){
		Cliente cliente = new Cliente();
		cliente.setContato("Jimmy");
		cliente.setCpfCnpj("36816511832");
		cliente.setRg("442490896");
		cliente.setTelefone("1139432495");
		cliente.setCelular("962124711");
		cliente.setId(1);
		cliente.setDataNascimento(new Date());
		cliente.setObservacao("Cliente Especial");
		cliente.setTipoCliente(TipoCliente.FISICO);
		cliente.setStatusCliente("ativo");
		Endereco enderecoCliente = new Endereco();
		enderecoCliente.setBairro("Jardim Marilu");
		enderecoCliente.setCep("02989010");
		enderecoCliente.setEstado("SP");
		enderecoCliente.setCidade("São Paulo");
		enderecoCliente.setNumero("9");
		enderecoCliente.setEndereco("Rua Joaquim Nascimento");
		
		Endereco enderecoEntrega = new Endereco();
		enderecoEntrega.setBairro("Jardim Marilu");
		enderecoEntrega.setCep("02989010");
		enderecoEntrega.setEstado("SP");
		enderecoEntrega.setCidade("São Paulo");
		enderecoEntrega.setNumero("9");
		enderecoEntrega.setEndereco("Rua Joaquim Nascimento");
		
		cliente.setEnderecoCliente(enderecoCliente);
		cliente.setEnderecoEntrega(enderecoEntrega);
		
		return cliente;
	}
}
