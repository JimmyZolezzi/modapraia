package moda.praia.modulo.estoque;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import moda.praia.modulo.produtos.bean.ItemProdutoEstoque;
import moda.praia.modulo.produtos.bean.Produto;

@Entity
public class HistoricoMovimentacaoEstoque {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY)
	private Produto produto;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY)
	private ItemProdutoEstoque itemProdutoEstoque;
	private int quantidade;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public ItemProdutoEstoque getItemProdutoEstoque() {
		return itemProdutoEstoque;
	}
	public void setItemProdutoEstoque(ItemProdutoEstoque itemProdutoEstoque) {
		this.itemProdutoEstoque = itemProdutoEstoque;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
