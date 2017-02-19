package moda.praia.modulo.avaliacao.bean;

import moda.praia.modulo.produtos.bean.Produto;

public class Avaliacao {
	
	private long id;
	private Produto produto;
	private int nota;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	

}
