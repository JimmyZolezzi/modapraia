package moda.praia.modulo.clientes.bean;

import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import moda.praia.modulo.endereco.Endereco;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "cliente")
public class Cliente {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(value = "dataNascimento")
	@JsonFormat(locale = "pt_br", shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Calendar dataNascimento;
	@JsonProperty(value = "telefone")
	private String telefone;
	@JsonProperty(value = "celular")
	private String celular;
	@JsonProperty(value = "contato")
	private String contato;
	@JsonProperty(value = "observacao")
	private String observacao;
	@JsonProperty(value = "statusCliente")
	private String statusCliente;
	@JsonProperty(value = "rg")
	private String rg;
	@JsonProperty(value = "cpf")
	private String cpf;
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "tipoCliente")
	private TipoCliente tipoCliente;
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JsonProperty(value = "enderecoCliente")
	private Endereco enderecoCliente;
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JsonProperty(value = "enderecoEntrega")
	private Endereco enderecoEntrega;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatusCliente() {
		return statusCliente;
	}
	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	public Endereco getEnderecoCliente() {
		return enderecoCliente;
	}
	public void setEnderecoCliente(Endereco enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public final static String ATIVO = "ativo";
	public final static String INATIVO = "inativo";
	
}
