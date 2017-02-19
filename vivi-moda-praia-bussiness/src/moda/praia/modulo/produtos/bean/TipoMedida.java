package moda.praia.modulo.produtos.bean;

public enum TipoMedida {
	
	NUMERICA("Numerica"),
	LETRA("Letra");
	
	private final String nome;
	
	private TipoMedida(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
