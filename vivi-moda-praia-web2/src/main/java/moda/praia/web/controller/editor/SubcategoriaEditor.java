package moda.praia.web.controller.editor;

import java.beans.PropertyEditorSupport;

import moda.praia.modulo.produtos.ProdutoBusiness;
import moda.praia.modulo.produtos.bean.Subcategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubcategoriaEditor extends PropertyEditorSupport {
	
	@Autowired
	ProdutoBusiness produtoBusiness;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		
		Subcategoria subcategoria = null;
		if(id != null && !id.equals("")  && id.matches("[0-9]*")){
			int idSubCategoria = Integer.parseInt(id);
			
			subcategoria = produtoBusiness.pesquisarSubcategoria(idSubCategoria);
			if(subcategoria !=null){
				this.setValue(subcategoria);
			}
		}
	}
	

}
