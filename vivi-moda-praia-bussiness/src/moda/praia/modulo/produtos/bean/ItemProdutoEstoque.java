package moda.praia.modulo.produtos.bean;


import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "itemProdutoEstoque")
public class ItemProdutoEstoque implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "tipoMedida")
	private TipoMedida tipoMedida;
	@JsonProperty(value = "tamanho")
	private String tamanho;
	@JsonProperty(value = "quantidade")
	private int quantidade;
	@JsonProperty(value = "quantidadeReservada")
	private int quantidadeReservada;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TipoMedida getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(TipoMedida tipoMedida) {
		this.tipoMedida = tipoMedida;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getQuantidadeReservada() {
		return quantidadeReservada;
	}
	public void setQuantidadeReservada(int quantidadeReservada) {
		this.quantidadeReservada = quantidadeReservada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + quantidade;
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result
				+ ((tipoMedida == null) ? 0 : tipoMedida.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemProdutoEstoque other = (ItemProdutoEstoque) obj;
		if (id != other.id)
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tipoMedida != other.tipoMedida)
			return false;
		return true;
	}

}
