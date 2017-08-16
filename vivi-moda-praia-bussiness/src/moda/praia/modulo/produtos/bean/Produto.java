package moda.praia.modulo.produtos.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import moda.praia.modulo.estoque.Estoque;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@JsonRootName(value = "produto")
@JsonAutoDetect
public class Produto {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "descricao")
	private String descricao;
	@JsonProperty(value = "informacoes")
	private String informacoes;
	@JsonProperty(value = "valor")
	private BigDecimal valor;
	@JsonProperty(value = "descontoValor")
	private BigDecimal descontoValor;
	@JsonProperty(value = "descontoPerecentual")
	private BigDecimal descontoPercentual;
	@JsonIgnore
	private int tamanhoNumerico;
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private TamanhoLetra tamanhoLetra;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonProperty(value = "categoria")
	@JsonInclude(value=Include.NON_NULL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Categoria categoria;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonProperty(value = "subcategoria")
	@JsonInclude(value=Include.NON_NULL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Subcategoria subcategoria;
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JsonInclude(value=Include.NON_EMPTY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonProperty(value = "imagemProduto1")
	private ImagemProduto imagemProduto1;
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH}, fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonProperty(value = "imagemProduto2")
	@JsonInclude(value=Include.NON_EMPTY)
	private ImagemProduto imagemProduto2;
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<ImagemProduto> imagensProduto;
	@JsonProperty(value = "possuiMaisItem")
	private boolean possuiMaisUmItem;
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@JsonProperty(value = "itensProduto")
	private List<ItemProduto> itensProduto;
	@JsonProperty(value = "comprimento")
	private double comprimento;
	@JsonProperty(value = "altura")
	private double altura;
	@JsonProperty(value = "largura")
	private double largura;
	@JsonProperty(value = "disponivelEstoque")
	private boolean disponivelEstoque;
	@JsonProperty(value = "corPredominante")
	private String corPredominante;
	@JsonProperty(value = "peso")
	private double peso;
	@JsonProperty(value = "aplicarDesconto")
	private boolean aplicarDesconto;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(value = "dataCriacao")
	private Date dataCriacao;
	@JsonProperty(value = "favorito")
	private boolean favorito;
	@JsonProperty(value = "destaque")
	private boolean destaque;
	@JsonProperty(value = "mediaAvaliacao")
	private double mediaAvaliacao;
	@JsonProperty(value = "quantidadeAvaliacao")
	private int quantidadeAvaliacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getDescontoValor() {
		return descontoValor;
	}
	public void setDescontoValor(BigDecimal descontoValor) {
		this.descontoValor = descontoValor;
	}
	public BigDecimal getDescontoPercentual() {
		return descontoPercentual;
	}
	public void setDescontoPercentual(BigDecimal descontoPercentual) {
		this.descontoPercentual = descontoPercentual;
	}
	public int getTamanhoNumerico() {
		return tamanhoNumerico;
	}
	public void setTamanhoNumerico(int tamanhoNumerico) {
		this.tamanhoNumerico = tamanhoNumerico;
	}
	public TamanhoLetra getTamanhoLetra() {
		return tamanhoLetra;
	}
	public void setTamanhoLetra(TamanhoLetra tamanhoLetra) {
		this.tamanhoLetra = tamanhoLetra;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	public ImagemProduto getImagemProduto1() {
		return imagemProduto1;
	}
	public void setImagemProduto1(ImagemProduto imagemProduto1) {
		this.imagemProduto1 = imagemProduto1;
	}
	public ImagemProduto getImagemProduto2() {
		return imagemProduto2;
	}
	public void setImagemProduto2(ImagemProduto imagemProduto2) {
		this.imagemProduto2 = imagemProduto2;
	}
	public List<ImagemProduto> getImagensProduto() {
		return imagensProduto;
	}
	public void setImagensProduto(List<ImagemProduto> imagensProduto) {
		this.imagensProduto = imagensProduto;
	}
	
	public boolean isPossuiMaisUmItem() {
		return possuiMaisUmItem;
	}
	public void setPossuiMaisUmItem(boolean possuiMaisUmItem) {
		this.possuiMaisUmItem = possuiMaisUmItem;
	}
	public List<ItemProduto> getItensProduto() {
		return itensProduto;
	}
	public void setItensProduto(List<ItemProduto> itensProduto) {
		this.itensProduto = itensProduto;
	}
	public double getComprimento() {
		return comprimento;
	}
	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		this.largura = largura;
	}
	public boolean isDisponivelEstoque() {
		return disponivelEstoque;
	}
	public void setDisponivelEstoque(boolean disponivelEstoque) {
		this.disponivelEstoque = disponivelEstoque;
	}
	public String getCorPredominante() {
		return corPredominante;
	}
	public void setCorPredominante(String corPredominante) {
		this.corPredominante = corPredominante;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public boolean isAplicarDesconto() {
		return aplicarDesconto;
	}
	public void setAplicarDesconto(boolean aplicarDesconto) {
		this.aplicarDesconto = aplicarDesconto;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	public boolean isDestaque() {
		return destaque;
	}
	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}
	public double getMediaAvaliacao() {
		return mediaAvaliacao;
	}
	public void setMediaAvaliacao(double mediaAvaliacao) {
		this.mediaAvaliacao = mediaAvaliacao;
	}
	public int getQuantidadeAvaliacao() {
		return quantidadeAvaliacao;
	}
	public void setQuantidadeAvaliacao(int quantidadeAvaliacao) {
		this.quantidadeAvaliacao = quantidadeAvaliacao;
	}
	
}
