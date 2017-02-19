package moda.praia.modulo.avaliacao.bean;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.produtos.bean.Produto;

public class Comentario {
	
	private long id;
	private Cliente cliente;
	private String descricao;
	private int nota;
	private Produto produto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

}
