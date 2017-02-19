package moda.praia.controller.form;

import org.springframework.web.multipart.MultipartFile;

public class FormImagemProduto {

	private MultipartFile foto;
	private long idProduto;
	
	public MultipartFile getFoto() {
		return foto;
	}
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
