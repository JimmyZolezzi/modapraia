/**
 * CalcPrecoPrazoWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package moda.praia.modulo.correios.webservices;

public interface CalcPrecoPrazoWSSoap extends java.rmi.Remote {
    public moda.praia.modulo.correios.webservices.CResultado calcPrecoPrazo(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrecoPrazoData(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento, java.lang.String sDtCalculo) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrecoPrazoRestricao(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento, java.lang.String sDtCalculo) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPreco(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrecoData(java.lang.String nCdEmpresa, java.lang.String sDsSenha, java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String nVlPeso, int nCdFormato, java.math.BigDecimal nVlComprimento, java.math.BigDecimal nVlAltura, java.math.BigDecimal nVlLargura, java.math.BigDecimal nVlDiametro, java.lang.String sCdMaoPropria, java.math.BigDecimal nVlValorDeclarado, java.lang.String sCdAvisoRecebimento, java.lang.String sDtCalculo) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrazo(java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrazoData(java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String sDtCalculo) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrazoRestricao(java.lang.String nCdServico, java.lang.String sCepOrigem, java.lang.String sCepDestino, java.lang.String sDtCalculo) throws java.rmi.RemoteException;
    public moda.praia.modulo.correios.webservices.CResultado calcPrecoFAC(java.lang.String nCdServico, java.lang.String nVlPeso, java.lang.String strDataCalculo) throws java.rmi.RemoteException;
}
