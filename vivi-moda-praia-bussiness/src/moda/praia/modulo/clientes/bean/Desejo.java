package moda.praia.modulo.clientes.bean;

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

import moda.praia.modulo.produtos.bean.Produto;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Desejo {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Cliente cliente;
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Produto> produtos;
	
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
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
