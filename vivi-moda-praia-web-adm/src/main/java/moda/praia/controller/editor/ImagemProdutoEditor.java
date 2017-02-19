package moda.praia.controller.editor;

import java.beans.PropertyEditorSupport;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.ImagemProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImagemProdutoEditor extends PropertyEditorSupport {

	@Autowired
	ProdutoBusiness produtoBusiness;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		
		ImagemProduto imagemProduto = null;
		if(id != null && !id.equals("") && id.matches("[0-9]*")){
			long idImagemProduto = Long.parseLong(id);
			
			imagemProduto = produtoBusiness.pesquisaImagemProdutoPorID(idImagemProduto);
			if(imagemProduto !=null){
				this.setValue(imagemProduto);
			}
		}
	}
}
