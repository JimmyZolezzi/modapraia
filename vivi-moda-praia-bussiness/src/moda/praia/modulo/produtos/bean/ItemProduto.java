package moda.praia.modulo.produtos.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "itemProduto")
public class ItemProduto implements Serializable{

	private static final long serialVersionUID = 1534259197878283272L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "nome")
	private String nome;
	@JsonProperty(value = "tipoMedida")
	@Enumerated(EnumType.STRING)
	private TipoMedida tipoMedida;
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Map<String, ItemProdutoEstoque> mapItemProdutoEstoque;
	
	//@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	//@JsonProperty(value = "itensEstoque")
	//private List<ItemProdutoEstoque> itensEstoque;
	
	
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
	
	public Map<String, ItemProdutoEstoque> getMapItemProdutoEstoque() {
		return mapItemProdutoEstoque;
	}
	public void setMapItemProdutoEstoque(Map<String, ItemProdutoEstoque> mapItemProdutoEstoque) {
		this.mapItemProdutoEstoque = mapItemProdutoEstoque;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mapItemProdutoEstoque == null) ? 0 : mapItemProdutoEstoque.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoMedida == null) ? 0 : tipoMedida.hashCode());
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
		ItemProduto other = (ItemProduto) obj;
		if (id != other.id)
			return false;
		if (mapItemProdutoEstoque == null) {
			if (other.mapItemProdutoEstoque != null)
				return false;
		} else if (!mapItemProdutoEstoque.equals(other.mapItemProdutoEstoque))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoMedida != other.tipoMedida)
			return false;
		return true;
	}

}
