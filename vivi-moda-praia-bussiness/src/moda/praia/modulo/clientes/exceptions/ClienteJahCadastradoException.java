package moda.praia.modulo.clientes.exceptions;

public class ClienteJahCadastradoException extends Exception {
	private static final long serialVersionUID = 2162668595376196092L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return MENSAGEM;
	}

	private static final String MENSAGEM = "Cliente ja cadastrado";

}
