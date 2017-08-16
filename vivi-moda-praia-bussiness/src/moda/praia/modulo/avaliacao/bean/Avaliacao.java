package moda.praia.modulo.avaliacao.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.modulo.produtos.bean.Produto;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "avaliacao")
@JsonAutoDetect
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = -8881004819484515630L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY)
	//@JsonProperty(value = "produto")
	@JsonIgnore
	private Produto produto;
	@JsonProperty(value = "nota")
	private int nota;
	@JsonProperty(value = "titulo")
	private String titulo;
	@JsonProperty(value = "comentario")
	private String comentario;
	@JsonProperty(value = "apelido")
	private String apelido;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private Cliente cliente;
	@JsonProperty(value = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
