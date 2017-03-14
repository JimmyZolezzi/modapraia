package moda.praia.modulo.pedido.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import moda.praia.modulo.produtos.bean.Produto;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "produto")
public class ProdutoPedido {

	public ProdutoPedido(){
		itensPedidoTamanho = new ArrayList<ItemPedidoTamanho>();
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "quantidade")
	private double quantidade;
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JsonProperty(value = "produto")
	private Produto produto;
	@JsonProperty(value = "valorUnitario")
	private BigDecimal valorUnitario;
	@JsonProperty(value = "valorTotal")
	private BigDecimal valorToral;
	@JsonProperty(value = "itensPedidoTamanho")
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<ItemPedidoTamanho> itensPedidoTamanho;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getValorToral() {
		return valorToral;
	}
	public void setValorToral(BigDecimal valorToral) {
		this.valorToral = valorToral;
	}
	public List<ItemPedidoTamanho> getItensPedidoTamanho() {
		return itensPedidoTamanho;
	}
	public void setItensPedidoTamanho(List<ItemPedidoTamanho> itensPedidoTamanho) {
		this.itensPedidoTamanho = itensPedidoTamanho;
	}
	
	
}
