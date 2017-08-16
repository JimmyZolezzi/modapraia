package moda.praia.uteis;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Valores {

	public static String formataMoeda(double vlr) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(vlr);
	}
	
	public static String formataMoedaSemCifrao(BigDecimal bigDecimal) {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String valor = nf.format(bigDecimal);
		valor = valor.replaceAll("R\\$ ", "");
		valor = valor.replaceAll("\\$ ", "");
		return valor;
	}
	
	public static String formataMoedaSemCifrao(Double valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String valorStr = nf.format(valor);
		valorStr = valorStr.replaceAll("R\\$ ", "");
		valorStr = valorStr.replaceAll("\\$ ", "");
		return valorStr;
	}
	
	
	public static String desformataMoeda(String valor){
		
		valor = valor.replace("R$", "");
		valor = valor.replace(".", "");
		valor = valor.replaceAll(",", ".");
		return valor;
		
	}
	
	
	public static boolean isValor(String valor){
		if(valor != null && valor.matches("\\d+(\\.\\d+)?")){
			return true;
		}
		return false;
	}
	
	public static boolean isLong(String numero){
		
		try{
			Long.parseLong(numero);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	
	
	public static boolean isDouble(String numero){
		
		try{
			Double.parseDouble(numero);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
}
