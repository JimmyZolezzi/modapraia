package test;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import moda.praia.modulo.correios.webservices.CResultado;
import moda.praia.modulo.correios.webservices.CServico;
import moda.praia.modulo.correios.webservices.CalcPrecoPrazoWSLocator;
import moda.praia.modulo.correios.webservices.CalcPrecoPrazoWSSoap;
import moda.praia.modulo.correios.webservices.CalcPrecoPrazoWSSoapStub;
import moda.praia.modulo.correios.webservices.CodigosServicos;

public class WebServiceCorreioTest {
	
	public static void main(String[] args) throws ServiceException, RemoteException {
		
		//Parametros
		String nCdEmpresa = "";
		String sDsSenha = "";
		String nCdServico = CodigosServicos.SEDEX_VAREJO;
		String sCepOrigem = "02989010";
		String sCepDestino = "01311300";
		String nVlPeso = "0.2";
		int nCdFormato = 1;
		BigDecimal nVlComprimento = new BigDecimal(27);
		BigDecimal nVlAltura = new BigDecimal(9);
		BigDecimal nVlLargura = new BigDecimal(18);
		BigDecimal nVlDiametro = new BigDecimal(0);
		String sCdMaoPropria = "N";
		BigDecimal nVlValorDeclarado = new BigDecimal("4000");
		String sCdAvisoRecebimento = "S";
		
		CalcPrecoPrazoWSLocator locator = new CalcPrecoPrazoWSLocator();

		CalcPrecoPrazoWSSoapStub stub = (CalcPrecoPrazoWSSoapStub) locator.getPort(CalcPrecoPrazoWSSoap.class);
		CResultado resultado = stub.calcPrecoPrazo(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
		System.out.println(resultado.getServicos()[0].getMsgErro());
		System.out.println(resultado.getServicos()[0].getValor());
		System.out.println(resultado.getServicos()[0].getPrazoEntrega());
	}

}
