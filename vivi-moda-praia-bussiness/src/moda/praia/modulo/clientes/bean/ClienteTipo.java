package moda.praia.modulo.clientes.bean;

import java.io.Serializable;

public enum ClienteTipo implements Serializable {
	
	FISICO("FISICO"),
	JURIDICO("JURIDICO");

	private ClienteTipo (String tipoCliente){
		this.tipoCliente = tipoCliente;
	}
	
	String tipoCliente;
	
	public String getTipoCliente() {
		return tipoCliente;
	}

}
