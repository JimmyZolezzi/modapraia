package moda.praia.controller.form;

import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;

public class FormEntradaEstoque {
	
	private String nomeItem;
	private String status;
	private String mensagem;
	private int quantidadeEntrada;
	private long idItemEstoque;
	private long idProduto;
	
	private ItemProdutoEstoque itemProdutoEstoque;

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public ItemProdutoEstoque getItemProdutoEstoque() {
		return itemProdutoEstoque;
	}

	public void setItemProdutoEstoque(ItemProdutoEstoque itemProdutoEstoque) {
		this.itemProdutoEstoque = itemProdutoEstoque;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getQuantidadeEntrada() {
		return quantidadeEntrada;
	}

	public void setQuantidadeEntrada(int quantidadeEntrada) {
		this.quantidadeEntrada = quantidadeEntrada;
	}

	public long getIdItemEstoque() {
		return idItemEstoque;
	}

	public void setIdItemEstoque(long idItemEstoque) {
		this.idItemEstoque = idItemEstoque;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	
}
