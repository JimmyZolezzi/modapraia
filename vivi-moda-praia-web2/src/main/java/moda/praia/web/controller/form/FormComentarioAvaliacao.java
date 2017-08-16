package moda.praia.web.controller.form;

import moda.praia.modulo.avaliacao.bean.Avaliacao;

public class FormComentarioAvaliacao {
	
	public FormComentarioAvaliacao(Avaliacao avaliacao){
		this.avaliacao = avaliacao;
	}
	
	public FormComentarioAvaliacao(){
	}
	
	private Avaliacao avaliacao;
	private String mensagem;
	
	
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	

}
