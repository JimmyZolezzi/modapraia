package moda.praia.uteis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Validacoes {
	
	
	public static boolean validarNumeroTelefone(String numero){
		
		if(numero !=null && (numero.length() == 10 || numero.length() == 12)){
			
			if(numero.matches("[0-9].*") && !numero.matches("((^|, )(0*|1*|2*|3*|4*|5*|6*|7*|8*|9*))")){
				return true;
			}
			
		}
		
		return false;
	}

	public static boolean validarNumeroCelular(String numero){
		
		if(numero !=null && (numero.length() == 11 || numero.length() == 13)){
			if(numero.matches("[0-9].*") && !numero.matches("((^|, )(0*|1*|2*|3*|4*|5*|6*|7*|8*|9*))")){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean validaEmail(String email){
		
		if(email != null && email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Valida a senha
	 * 
	 * Minimo 8 dígitos
	 * Máximo 15 digitos
	 *	
	 * com pelo menos 1 letra
	 * com pelo menos 1 numero
	 * Pelo menos uma letra maiscula
	 * Pelo menos uma letra minuscula
	 * 
	 * @param senha
	 * @return
	 */
	public static boolean validaSenha(String senha){
		senha = senha.trim();
		
		if(senha != null && senha.length() >=8 && senha.length() <= 15){

			if(senha.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")){
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		
		String numero = "11111111112";
		
		System.out.println("Celular: " + Validacoes.validarNumeroTelefone(numero));
		
		String email = "11111_2.teste@gmail.com";
		System.out.println("Email: " + Validacoes.validaEmail(email));
		
		String telefone = "39432495";
		System.out.println("Telefone: " + Validacoes.validarNumeroTelefone(telefone));
		
		String senha = "oOO@11111";
		System.out.println("Senha: " + Validacoes.validaSenha(senha));
		System.out.println("Senha Criptografada: " + Criptografia.criptografaSenha(senha));
		
	}
}
