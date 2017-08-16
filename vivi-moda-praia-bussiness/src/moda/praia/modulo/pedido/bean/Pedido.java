package moda.praia.modulo.pedido.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.endereco.Endereco;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "pedido")
public class Pedido {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JsonProperty(value = "cliente")
	private Cliente cliente;
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JsonProperty(value = "enderecoEntrega")
	private Endereco enderecoEntrega;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonProperty(value = "produtosPedido")
	private List<ProdutoPedido> produtosPedido;
	@JsonProperty(value = "valorProdutos")
	private BigDecimal valorProdutos;
	@JsonProperty(value = "valorFrete")
	private BigDecimal valorFrete;
	@JsonProperty(value = "valorTotal")
	private BigDecimal valorTotal;
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "statusPedido")
	private StatusPedido statusPedido;
	@JsonProperty(value = "observacao")
	private String observacao;
	private Date dataPedido;
	private Date previsaoEntrega;
	private Date dataEntrega;

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
	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	public List<ProdutoPedido> getProdutosPedido() {
		return produtosPedido;
	}
	public void setProdutosPedido(List<ProdutoPedido> produtosPedido) {
		this.produtosPedido = produtosPedido;
	}
	public BigDecimal getValorProdutos() {
		return valorProdutos;
	}
	public void setValorProdutos(BigDecimal valorProdutos) {
		this.valorProdutos = valorProdutos;
	}
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Date getPrevisaoEntrega() {
		return previsaoEntrega;
	}
	public void setPrevisaoEntrega(Date previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
