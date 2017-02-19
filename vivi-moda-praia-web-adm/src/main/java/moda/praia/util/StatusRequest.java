package moda.praia.util;

import moda.praia.modulo.jsonview.Views;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;

@JsonRootName(value = "statusRequest")
public class StatusRequest {

	@JsonView(Views.Public.class)
	private String status;

	
	public StatusRequest(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public final static String SUCESSO = "sucesso";
	public final static String ERRO = "erro";

}
