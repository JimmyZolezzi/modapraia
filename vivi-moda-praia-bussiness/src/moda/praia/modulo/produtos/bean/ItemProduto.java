package moda.praia.modulo.produtos.bean;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "produto")
public class ItemProduto {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "nome")
	private String nome;
	@JsonProperty(value = "tipoMedida")
	@Enumerated(EnumType.STRING)
	private TipoMedida tipoMedida;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoMedida getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(TipoMedida tipoMedida) {
		this.tipoMedida = tipoMedida;
	}
}
