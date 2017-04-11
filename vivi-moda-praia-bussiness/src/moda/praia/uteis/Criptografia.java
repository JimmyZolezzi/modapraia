package moda.praia.uteis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;


public class Criptografia {
	
	private static final Logger log = Logger.getLogger(Criptografia.class);
	
	public static String criptografaSenha(String valor){
		
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(valor.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String senha = hexString.toString();
			return senha;
		
		} catch (NoSuchAlgorithmException e) {
			log.error("Erro de criptografia: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error("Erro de criptografia: " + e.getMessage());
		}
		
		return null;
		
	}


}
