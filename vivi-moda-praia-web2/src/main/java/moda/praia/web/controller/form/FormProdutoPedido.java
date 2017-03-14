package moda.praia.web.controller.form;

import java.util.ArrayList;
import java.util.List;

import moda.praia.modulo.pedido.bean.ProdutoPedido;

public class FormProdutoPedido {
	
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
