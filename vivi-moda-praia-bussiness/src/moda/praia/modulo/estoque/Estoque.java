package moda.praia.modulo.estoque;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import moda.praia.modulo.produtos.bean.TamanhoLetra;
import moda.praia.modulo.produtos.bean.TipoMedida;
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Estoque {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Enumerated(EnumType.STRING)
	private TipoMedida tipoMedida;
	private double quantidade;
	@Enumerated(EnumType.STRING)
	private TamanhoLetra tamanhoLetra;
	private int tamanhoNumero;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TipoMedida getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(TipoMedida tipoMedida) {
		this.tipoMedida = tipoMedida;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public TamanhoLetra getTamanhoLetra() {
		return tamanhoLetra;
	}
	public void setTamanhoLetra(TamanhoLetra tamanhoLetra) {
		this.tamanhoLetra = tamanhoLetra;
	}
	public int getTamanhoNumero() {
		return tamanhoNumero;
	}
	public void setTamanhoNumero(int tamanhoNumero) {
		this.tamanhoNumero = tamanhoNumero;
	}
	

}
