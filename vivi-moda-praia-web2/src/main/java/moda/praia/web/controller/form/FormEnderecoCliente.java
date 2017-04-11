package moda.praia.web.controller.form;

import moda.praia.modulo.endereco.Endereco;

public class FormEnderecoCliente {
	
	private long idCliente;
	private Endereco endereco;
	private String resultado;
	private String mensagem;
	

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public FormEnderecoCliente (){
		endereco = new Endereco();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
