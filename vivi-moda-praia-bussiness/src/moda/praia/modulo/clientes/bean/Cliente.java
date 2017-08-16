package moda.praia.modulo.clientes.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 5982718144647039827L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "nome")
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(value = "dataNascimento")
	@JsonFormat(locale = "pt_br", shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataNascimento;
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
	@JsonProperty(value = "cpfOuCnpj")
	private String cpfCnpj;
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "tipoCliente")
	private TipoCliente tipoCliente;
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JsonProperty(value = "enderecoCliente")
	private Endereco enderecoCliente;
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JsonProperty(value = "enderecoEntrega")
	private Endereco enderecoEntrega;
	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "senha")
	private String senha;
	
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
	public Date getDataNascimento() {
		
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
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
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public final static String ATIVO = "ativo";
	public final static String INATIVO = "inativo";
	
}
