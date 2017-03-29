package moda.praia.web.controller.form;

import java.util.ArrayList;
import java.util.List;

import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.bean.Produto;

public class FormProdutoPedido {
	
	private long idProduto;
	private Produto produto;
	
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public FormProdutoPedido(){
		listaProdutoPedido = new ArrayList<ProdutoPedido>();
	}
	private int quantidade;
	private List<ProdutoPedido> listaProdutoPedido;
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public List<ProdutoPedido> getListaProdutoPedido() {
		return listaProdutoPedido;
	}
	public void setListaProdutoPedido(List<ProdutoPedido> listaProdutoPedido) {
		this.listaProdutoPedido = listaProdutoPedido;
	}
	
	
}
