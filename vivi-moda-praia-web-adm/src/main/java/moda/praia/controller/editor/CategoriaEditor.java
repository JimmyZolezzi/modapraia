package moda.praia.controller.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Categoria;

@Component
public class CategoriaEditor  extends PropertyEditorSupport{

	@Autowired
	ProdutoBusiness produtoBusiness;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		
		Categoria categoria = null;
		if(id != null && !id.equals("") && id.matches("[0-9]*")){
			int idCategoria = Integer.parseInt(id);
			
			categoria = produtoBusiness.pesquisarCategoria(idCategoria);
			if(categoria !=null){
				this.setValue(categoria);
			}
		}
	}
	

}
