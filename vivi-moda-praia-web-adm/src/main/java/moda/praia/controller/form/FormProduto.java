package moda.praia.controller.form;

import org.springframework.web.multipart.MultipartFile;

import moda.praia.modulo.produtos.bean.Produto;

public class FormProduto extends Produto {
	
	private MultipartFile foto1;
	private MultipartFile foto2;
	
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
	
	public void zerarValores(){
	
		this.setId(0);
		this.setDescricao(null);
		this.setInformacoes(null);
		this.setCategoria(null);
		this.setSubcategoria(null);
		this.setEstoques(null);
		this.setTipoMedida(null);
		this.setTamanhoLetra(null);
		this.setTamanhoNumerico(0);
		this.setImagemProduto1(null);
		this.setImagemProduto2(null);
		this.setValor(null);

	}
	
	public FormProduto(){
		
	}
	
	public FormProduto(Produto produto){
		this.setId(produto.getId());
		this.setDescricao(produto.getDescricao());
		this.setCategoria(produto.getCategoria());
		this.setSubcategoria(produto.getSubcategoria());
		this.setEstoques(produto.getEstoques());
		this.setTipoMedida(produto.getTipoMedida());
		this.setTamanhoLetra(produto.getTamanhoLetra());
		this.setTamanhoNumerico(produto.getTamanhoNumerico());
		this.setImagemProduto1(produto.getImagemProduto1());
		this.setImagemProduto2(produto.getImagemProduto2());
		this.setValor(produto.getValor());
		
	}

	public final static Produto valueOf(Produto produto){
		Produto produtoNovo = new Produto();
		if(produto.getId()!=0){
			produtoNovo.setId(produto.getId());
		}
		if(produto.getDescricao()!=null){
			produtoNovo.setDescricao(produto.getDescricao());
		}
		if(produto.getInformacoes()!=null){
			produtoNovo.setInformacoes(produto.getInformacoes());
		}
		if(produto.getCategoria()!=null){
			produtoNovo.setCategoria(produto.getCategoria());
		}
		if(produto.getSubcategoria()!=null){
			produtoNovo.setSubcategoria(produto.getSubcategoria());
		}
		if(produto.getEstoques()!=null){
			produtoNovo.setEstoques(produto.getEstoques());
		}
		if(produto.getTipoMedida()!=null){
			produtoNovo.setTipoMedida(produto.getTipoMedida());
		}
		if(produto.getTamanhoLetra()!=null){
			produtoNovo.setTamanhoLetra(produto.getTamanhoLetra());
		}
		if(produto.getTamanhoNumerico()!=0){
			produtoNovo.setTamanhoNumerico(produto.getTamanhoNumerico());
		}
		if(produto.getImagemProduto1()!=null){
			produtoNovo.setImagemProduto1(produto.getImagemProduto1());
		}
		if(produto.getImagemProduto2()!=null){
			produtoNovo.setImagemProduto2(produto.getImagemProduto2());
		}
		if(produto.getValor()!=null){
			produtoNovo.setValor(produto.getValor());
		}

		return produtoNovo;
		
	}
	

}
