package moda.praia.web.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "valorPedidoFrete")
public class ValorPedidoFrete {

	@JsonProperty(value = "valorFrete")
	private BigDecimal valorFrete;
	@JsonProperty(value = "valorProdutos")
	private BigDecimal valorProdutos;
	@JsonProperty(value = "valorTotalPedido")
	private BigDecimal valorTotalPedido;
	
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public BigDecimal getValorProdutos() {
		return valorProdutos;
	}
	public void setValorProdutos(BigDecimal valorProdutos) {
		this.valorProdutos = valorProdutos;
	}
	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}
	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}
	
	
	
}
