package moda.praia.controller.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import moda.praia.modulo.produtos.bean.Subcategoria;
@JsonRootName(value = "formSubcategoria")
public class FormSubcategorias {
	
	@JsonProperty(value="subcategorias")
	private List<Subcategoria> subcategorias;
	@JsonProperty(value="idSubcategoriaSelecionada")
	private int idCategoriaSelecionada;
	
	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public int getIdCategoriaSelecionada() {
		return idCategoriaSelecionada;
	}

	public void setIdCategoriaSelecionada(int idCategoriaSelecionada) {
		this.idCategoriaSelecionada = idCategoriaSelecionada;
	}

	

}
