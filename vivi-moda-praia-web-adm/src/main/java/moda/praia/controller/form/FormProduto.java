package moda.praia.controller.form;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.multipart.MultipartFile;

import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.uteis.Valores;

public class FormProduto extends Produto {

	private MultipartFile foto1;
	private MultipartFile foto2;
	private int quantidade;
	private String valorStr;
	private String pesoStr;
	private String larguraStr;
	private String alturaStr;
	private String comprimentoStr;
	private String descontoPercentualStr;
	private boolean terDesconto;
	private boolean terDestaque;
	
	public MultipartFile getFoto1() {
		return foto1;
	}

	public void setFoto1(MultipartFile foto1) {
		this.foto1 = foto1;
	}

	public MultipartFile getFoto2() {
		return foto2;
	}

	public void setFoto2(MultipartFile foto2) {
		this.foto2 = foto2;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getValorStr() {
		return valorStr;
	}

	public void setValorStr(String valorStr) {
		this.valorStr = valorStr;
	}

	public String getPesoStr() {
		return pesoStr;
	}

	public void setPesoStr(String pesoStr) {
		this.pesoStr = pesoStr;
	}

	public String getLarguraStr() {
		return larguraStr;
	}

	public void setLarguraStr(String larguraStr) {
		this.larguraStr = larguraStr;
	}

	public String getAlturaStr() {
		return alturaStr;
	}

	public void setAlturaStr(String alturaStr) {
		this.alturaStr = alturaStr;
	}

	public String getComprimentoStr() {
		return comprimentoStr;
	}

	public void setComprimentoStr(String comprimentoStr) {
		this.comprimentoStr = comprimentoStr;
	}

	public String getDescontoPercentualStr() {
		return descontoPercentualStr;
	}

	public void setDescontoPercentualStr(String descontoPercentualStr) {
		this.descontoPercentualStr = descontoPercentualStr;
	}

	public void zerarValores() {

		this.setId(0);
		this.setDescricao(null);
		this.setInformacoes(null);
		this.setCategoria(null);
		this.setSubcategoria(null);
		this.setTamanhoLetra(null);
		this.setTamanhoNumerico(0);
		this.setImagemProduto1(null);
		this.setImagemProduto2(null);
		this.setValor(null);

	}

	public FormProduto() {

	}

	public void addProduto(Produto produto){
		
		if(produto != null){
			myCopyProperties(produto, this);
			//Valor
			if(produto.getValor() == null || produto.getValor().doubleValue() == 0){
				this.setValorStr("");
			}else{
				this.setValorStr(Valores.formataMoedaSemCifrao(produto.getValor()));
			}
			//Percentual de desconto
			if(produto.getDescontoPercentual() == null || produto.getDescontoPercentual().doubleValue() == 0){
				this.setDescontoPercentualStr("");
			}else{
				this.setDescontoPercentualStr(Valores.formataMoedaSemCifrao(produto.getDescontoPercentual()));
			}
			//Altura
			if(produto.getAltura() == 0){
				this.setAlturaStr("");
			}else{
				this.setAlturaStr(Valores.formataMoedaSemCifrao(produto.getAltura()));
			}
			//Largura
			if(produto.getLargura() == 0){
				this.setLarguraStr("");
			}else{
				this.setLarguraStr(Valores.formataMoedaSemCifrao(produto.getLargura()));
			}
			//Comprimento
			if(produto.getComprimento() == 0){
				this.setComprimentoStr("");
			}else{
				this.setComprimentoStr(Valores.formataMoedaSemCifrao(produto.getComprimento()));
			}
			//Peso
			if(produto.getPeso() == 0){
				this.setPesoStr("");
			}else{
				this.setPesoStr(Valores.formataMoedaSemCifrao(produto.getPeso()));
			}
			//Itens Produto
			if(produto.getItensProduto() != null){
				this.setQuantidade(produto.getItensProduto().size());
			}
			this.setTerDesconto(produto.isAplicarDesconto());
			this.setTerDestaque(produto.isDestaque());
		}
		
	}
	public FormProduto(Produto produto) {
		this.addProduto(produto);
		
	}

	// then use Spring BeanUtils to copy and ignore null
	private static void myCopyProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}

	
	public final static Produto valueOf(FormProduto formProduto) {

		Produto produtoNovo = new Produto();
		myCopyProperties(formProduto, produtoNovo);
		if(formProduto != null){
			String pesoStr = Valores.desformataMoeda(formProduto.pesoStr);
			String larguraStr = Valores.desformataMoeda(formProduto.larguraStr);
			String alturaStr = Valores.desformataMoeda(formProduto.alturaStr);
			String comprimentoStr = Valores.desformataMoeda(formProduto.comprimentoStr);
			String valorStr = Valores.desformataMoeda(formProduto.valorStr);
			String percentualDesconto = Valores.desformataMoeda(formProduto.descontoPercentualStr);
			produtoNovo.setAplicarDesconto(formProduto.isTerDesconto());
			produtoNovo.setDestaque(formProduto.isTerDesconto());
			//peso
			if(Valores.isDouble(pesoStr)){
				double peso = Double.valueOf(pesoStr);
				produtoNovo.setPeso(peso);
			}
			//largura
			if(Valores.isDouble(larguraStr)){
				double largura = Double.valueOf(larguraStr);
				produtoNovo.setLargura(largura);
			}
			//altura
			if(Valores.isDouble(alturaStr)){
				double altura = Double.valueOf(alturaStr);
				produtoNovo.setAltura(altura);
			}
			//comprimento
			if(Valores.isDouble(comprimentoStr)){
				double comprimento = Double.valueOf(comprimentoStr);
				produtoNovo.setComprimento(comprimento);
			}
			//Valor
			if(Valores.isDouble(valorStr)){
				BigDecimal valorBigDecimal = new BigDecimal(valorStr);
				produtoNovo.setValor(valorBigDecimal);
			}
			if(Valores.isDouble(percentualDesconto)){
				BigDecimal valorPercentualDesconto = new BigDecimal(percentualDesconto);
				produtoNovo.setDescontoPercentual(valorPercentualDesconto);
				
			}
			
		}
		/*
		if (produto.getId() != 0) {
			produtoNovo.setId(produto.getId());
		}
		if (produto.getDescricao() != null) {
			produtoNovo.setDescricao(produto.getDescricao());
		}
		if (produto.getInformacoes() != null) {
			produtoNovo.setInformacoes(produto.getInformacoes());
		}
		if (produto.getCategoria() != null) {
			produtoNovo.setCategoria(produto.getCategoria());
		}
		if (produto.getSubcategoria() != null) {
			produtoNovo.setSubcategoria(produto.getSubcategoria());
		}
		if (produto.getEstoques() != null) {
			produtoNovo.setEstoques(produto.getEstoques());
		}
		if (produto.getTipoMedida() != null) {
			produtoNovo.setTipoMedida(produto.getTipoMedida());
		}
		if (produto.getTamanhoLetra() != null) {
			produtoNovo.setTamanhoLetra(produto.getTamanhoLetra());
		}
		if (produto.getTamanhoNumerico() != 0) {
			produtoNovo.setTamanhoNumerico(produto.getTamanhoNumerico());
		}
		if (produto.getImagemProduto1() != null) {
			produtoNovo.setImagemProduto1(produto.getImagemProduto1());
		}
		if (produto.getImagemProduto2() != null) {
			produtoNovo.setImagemProduto2(produto.getImagemProduto2());
		}
		if (produto.getValor() != null) {
			produtoNovo.setValor(produto.getValor());
		}
			*/
		return produtoNovo;

	}

	public boolean isTerDesconto() {
		return terDesconto;
	}

	public void setTerDesconto(boolean terDesconto) {
		this.terDesconto = terDesconto;
	}

	public boolean isTerDestaque() {
		return terDestaque;
	}

	public void setTerDestaque(boolean terDestaque) {
		this.terDestaque = terDestaque;
	}

}
