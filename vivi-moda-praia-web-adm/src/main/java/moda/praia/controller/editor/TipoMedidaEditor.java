package moda.praia.controller.editor;

import java.beans.PropertyEditorSupport;

import moda.praia.modulo.produtos.bean.TipoMedida;

import org.springframework.stereotype.Component;

@Component
public class TipoMedidaEditor extends PropertyEditorSupport {
	
	
	@Override
	public void setAsText(String valor) throws IllegalArgumentException {
		
		if(TipoMedida.LETRA.getNome().equals(valor)){
			this.setValue(TipoMedida.LETRA);
		}
		if(TipoMedida.NUMERICA.getNome().equals(valor)){
			this.setValue(TipoMedida.NUMERICA);
		}
		
	}

}
