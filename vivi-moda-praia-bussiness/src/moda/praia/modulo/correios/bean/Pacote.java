package moda.praia.modulo.correios.bean;

import javax.annotation.Generated;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
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
@JsonRootName(value = "pacote")
public class Pacote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "nome")
	private String nome;
	@JsonProperty(value = "largura")
	private double largura;
	@JsonProperty(value = "altura")
	private double altura;
	@JsonProperty(value = "comprimento")
	private double comprimento;
	
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
	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		this.largura = largura;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getComprimento() {
		return comprimento;
	}
	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}
	

}
