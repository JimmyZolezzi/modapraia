package moda.praia.controller.form;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.multipart.MultipartFile;

import moda.praia.modulo.produtos.bean.Produto;

public class FormProduto extends Produto {

	private MultipartFile foto1;
	private MultipartFile foto2;
	private int quantidade;

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

	public void zerarValores() {

		this.setId(0);
		this.setDescricao(null);
		this.setInformacoes(null);
		this.setCategoria(null);
		this.setSubcategoria(null);
		this.setEstoques(null);
		this.setTamanhoLetra(null);
		this.setTamanhoNumerico(0);
		this.setImagemProduto1(null);
		this.setImagemProduto2(null);
		this.setValor(null);

	}

	public FormProduto() {

	}

	public FormProduto(Produto produto) {
		this.setId(produto.getId());
		this.setDescricao(produto.getDescricao());
		this.setCategoria(produto.getCategoria());
		this.setSubcategoria(produto.getSubcategoria());
		this.setEstoques(produto.getEstoques());
		this.setTamanhoLetra(produto.getTamanhoLetra());
		this.setTamanhoNumerico(produto.getTamanhoNumerico());
		this.setImagemProduto1(produto.getImagemProduto1());
		this.setImagemProduto2(produto.getImagemProduto2());
		this.setValor(produto.getValor());

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

	
	public final static Produto valueOf(Produto produto) {

		Produto produtoNovo = new Produto();
		myCopyProperties(produto, produtoNovo);
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

}
