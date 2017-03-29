package moda.praia.modulo.produtos.bean;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;

import moda.praia.modulo.jsonview.Views;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "categoria")
@JsonInclude(value=Include.NON_NULL)
public class Categoria {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private int id;
	@JsonProperty(value = "descricao")
	private String descricao;
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Subcategoria> subcategorias;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + id;
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
		Categoria other = (Categoria) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	

}
