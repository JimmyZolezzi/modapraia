package moda.praia.uteis;

import java.util.ArrayList;
import java.util.List;


public class EmailEnvio {

/*
	private String hostname;
	private int smtpPort;
	private String emailProvedor;
	private String nomeProvedor;
	private boolean ssl;
	private String senha;
	private String status;
	
	public void sendEmail(String emailDestinatario, String nomeDestinario,String titulo, String mensagem, String comoCopia) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		// Utilize o hostname do seu provedor de email
		System.out.println("alterando hostname...");
		email.setHostName(hostname);
		// Quando a porta utilizada n�o � a padr�o (gmail = 465)
		email.setSmtpPort(smtpPort);
		// Adicione os destinat�rios
		email.addTo(emailDestinatario, nomeDestinario);
		// Configure o seu email do qual enviar�
		email.setFrom(emailProvedor, nomeProvedor);
		// Adicione um assunto
		email.setSubject(titulo);
		// Adicione a mensagem do email
		email.setMsg(mensagem);
		email.setHtmlMsg(mensagem);
		
		if(comoCopia!=null && !comoCopia.trim().equals("")){
			List<InternetAddress> comoCopiaEnderecos = new ArrayList<>();
			InternetAddress endereco = new InternetAddress();
			endereco.setAddress(comoCopia);
			comoCopiaEnderecos.add(endereco);
			email.setCc(comoCopiaEnderecos);
		}
		
		// Para autenticar no servidor � necess�rio chamar os dois m�todos
		// abaixo
		email.setSSL(ssl);
		email.setAuthentication(emailProvedor, senha);
		System.out.println("Enviando...");
		status = "enviando...";
		System.out.println();
		email.send();
		System.out.println("E-mail enviado.");
		status = "Email enviado!";
	}

	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getEmailProvedor() {
		return emailProvedor;
	}

	public void setEmailProvedor(String emailProvedor) {
		this.emailProvedor = emailProvedor;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeProvedor() {
		return nomeProvedor;
	}

	public void setNomeProvedor(String nomeProvedor) {
		this.nomeProvedor = nomeProvedor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
	*/
}
