package moda.praia.modulo.pedido.bean;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "quantidade")
	private double quantidade;
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JsonProperty(value = "produto")
	private Produto produto;
	@JsonProperty(value = "valorUnitario")
	private BigDecimal valorUnitario;
	@JsonProperty(value = "valorTotal")
	private BigDecimal valorToral;
	
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
	
	
}
