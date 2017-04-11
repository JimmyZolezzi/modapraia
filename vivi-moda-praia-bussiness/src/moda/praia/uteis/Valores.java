package moda.praia.uteis;

import java.text.NumberFormat;

public class Valores {

	public static String formataMoeda(double vlr) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(vlr);
	}
	
	public static String desformataMoeda(String valor){
		
		valor = valor.replace("R$", "");
		valor = valor.replace(".", "");
		valor = valor.replaceAll(",", ".");
		return valor;
		
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
