package moda.praia.modulo.produtos.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import moda.praia.modulo.jsonview.Views;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonRootName(value = "imagemProduto")
@JsonInclude(value=Include.NON_NULL)
public class ImagemProduto implements Serializable{
	
	
	private static final long serialVersionUID = 3256188455811818001L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value = "id")
	private long id;
	
	@Lob
    @Column(nullable=false, columnDefinition="mediumblob")
    @JsonIgnore
	private byte[] imagem;
	@Column(nullable=true, columnDefinition="mediumblob")
	@JsonIgnore
	private byte[] imagemMedia;
	@Column(nullable=true, columnDefinition="mediumblob")
	@JsonIgnore
	private byte[] imagemMediaPequena;
	@Column(nullable=true, columnDefinition="mediumblob")
	@JsonIgnore
	private byte[] imagemPequena;
	@JsonProperty(value = "nomeImagem")
	private String nomeImagem;
	@JsonProperty(value = "contentType")
	private String contentType;
	
	public byte[] getImagemMedia() {
		return imagemMedia;
	}
	public void setImagemMedia(byte[] imagemMedia) {
		this.imagemMedia = imagemMedia;
	}
	public byte[]
			getImagemPequena() {
		return imagemPequena;
	}
	public void setImagemPequena(byte[] imagemPequena) {
		this.imagemPequena = imagemPequena;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public String getNomeImagem() {
		return nomeImagem;
	}
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getImagemMediaPequena() {
		return imagemMediaPequena;
	}
	public void setImagemMediaPequena(byte[] imagemMediaPequena) {
		this.imagemMediaPequena = imagemMediaPequena;
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
		ImagemProduto other = (ImagemProduto) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
