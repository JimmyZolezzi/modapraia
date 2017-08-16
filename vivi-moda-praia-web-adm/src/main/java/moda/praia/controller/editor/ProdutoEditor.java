package moda.praia.controller.editor;

import java.beans.PropertyEditorSupport;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoEditor  extends PropertyEditorSupport {

	@Autowired
	ProdutoBusiness produtoBusiness;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		
		Produto produto = null;
		if(id != null && !id.equals("") && id.matches("[0-9].*")){
			long idProduto = Long.parseLong(id);
			
			produto = produtoBusiness.pesquisarProduto(idProduto);
			if(produto !=null){
				this.setValue(produto);
			}
		}
	}
}
