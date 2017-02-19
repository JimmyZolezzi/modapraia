package test.produto.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import moda.praia.modulo.produtos.bean.Categoria;
import moda.praia.modulo.produtos.bean.Produto;

public class TestProdutoJson {

	public static void main(String[] args) throws JsonProcessingException {
		Produto produto = new Produto();
		produto.setDescricao("Camisa");
		
		Categoria categoria = new Categoria();
		categoria.setId(10);
		categoria.setDescricao("Roupa");
		produto.setCategoria(categoria);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonProduto = mapper.writeValueAsString(produto);
		System.out.println(jsonProduto);
	}
}
