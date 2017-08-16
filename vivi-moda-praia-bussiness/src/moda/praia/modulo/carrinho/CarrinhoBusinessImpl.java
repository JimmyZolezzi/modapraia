package moda.praia.modulo.carrinho;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.ProdutoBusinessImpl;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.uteis.Valores;
import web.services.correios.CResultado;
import web.services.correios.CalcPrecoPrazoWSSoap;

@Service
@Scope(value="session",proxyMode = ScopedProxyMode.INTERFACES)
public class CarrinhoBusinessImpl implements CarrinhoBusiness {
	
	private final Logger log = Logger.getLogger(ProdutoBusinessImpl.class);

	private Map<String, ProdutoPedido> mapProdutoPedidos = new HashMap<>(); 
	private Pedido pedido;
	private String cep;
	
	@Override
	public void colocarProdutoCarrinho(Produto produto, int quantidade) {
		
		if(produto != null && produto.getValor() != null && quantidade !=0){
			ProdutoPedido produtoPedido = new ProdutoPedido();
			produtoPedido.setProduto(produto);
			produtoPedido.setQuantidade(quantidade);
			produtoPedido.setValorUnitario(produto.getValor());
			BigDecimal valorTotal = BigDecimal.ZERO;
			valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
			produtoPedido.setValorTotal(valorTotal);
			mapProdutoPedidos.put(String.valueOf(produto.getId()), produtoPedido);
		}
	}

	private void preencherPedido (){
		pedido = new Pedido();
		
		Set<String> chavesProdutoPedido = mapProdutoPedidos.keySet();
		List<ProdutoPedido> listaProdutoPedido = new ArrayList<ProdutoPedido>();
		BigDecimal totalProdutos = new BigDecimal(0);
		for (String chave : chavesProdutoPedido) {
			ProdutoPedido produtoPedido = mapProdutoPedidos.get(chave);
			listaProdutoPedido.add(produtoPedido);
			totalProdutos = totalProdutos.add(produtoPedido.getValorTotal());
		}
		pedido.setProdutosPedido(listaProdutoPedido);
		pedido.setValorProdutos(totalProdutos);
		pedido.setValorTotal(totalProdutos);
	}
	
	
	@Override
	public Pedido obterPedidoCarrinho() {

		if(pedido == null){
			pedido = new Pedido();
		}
	
		return pedido;
	}

	@Override
	public boolean excluirProdutoCarrinho(Produto produto) {

		mapProdutoPedidos.remove(produto.getId());
		preencherPedido();
		
		return true;
	}

	
	@Override
	public void colocarProdutoCarrinho(ProdutoPedido produtoPedido) {
		
		if(produtoPedido != null && produtoPedido.getProduto() != null && produtoPedido.getQuantidade() !=0){
			Produto produto = produtoPedido.getProduto();
			
			
			if(!produto.isAplicarDesconto()){
				produtoPedido.setValorUnitario(produto.getValor());
				BigDecimal valorTotalProduto = produto.getValor().multiply(new BigDecimal(produtoPedido.getQuantidade()));
				produtoPedido.setValorTotal(valorTotalProduto);
			}else{
				produtoPedido.setValorUnitario(produto.getDescontoValor());
				BigDecimal valorTotalProduto = produto.getDescontoValor().multiply(new BigDecimal(produtoPedido.getQuantidade()));
				produtoPedido.setValorTotal(valorTotalProduto);
			}
			
			String chave = obterChaveProdutoPedido(produtoPedido);
			produtoPedido.setChave(chave);
			
			ProdutoPedido produtoPedidoArmazenado = mapProdutoPedidos.get(chave);
			if(produtoPedidoArmazenado != null){
				double quantidadeArmazenada = produtoPedidoArmazenado.getQuantidade();
				double quantidadeProdutoPedido = produtoPedido.getQuantidade();
				quantidadeArmazenada = quantidadeArmazenada + quantidadeProdutoPedido;
				
				BigDecimal totalProdutoPedido = produtoPedidoArmazenado.getValorUnitario().multiply(new BigDecimal(quantidadeArmazenada));
				produtoPedido.setQuantidade(quantidadeArmazenada);
				produtoPedido.setValorTotal(totalProdutoPedido);
				
			}
			
			mapProdutoPedidos.put(chave, produtoPedido);
		}
		
		preencherPedido();
		
	}
	
	private String obterChaveProdutoPedido(ProdutoPedido produtoPedido){
		
		StringBuilder sbChave = new StringBuilder();
		
		if(produtoPedido != null && produtoPedido.getProduto() != null && produtoPedido.getQuantidade() !=0){
			sbChave.append(produtoPedido.getProduto().getId());
			List<ItemPedidoTamanho> listaTamanhoPedido  = produtoPedido.getItensPedidoTamanho();
			for (ItemPedidoTamanho itemPedidoTamanho : listaTamanhoPedido) {
				sbChave.append(itemPedidoTamanho.getTamanho());
			}
			
		}
		
		return sbChave.toString();
		
	}

	@Override
	public void mudarQuantidadeProdutoPedido(String chave, int quantidade) {

		ProdutoPedido produtoPedido = mapProdutoPedidos.get(chave);
		produtoPedido.setQuantidade(quantidade);
		
		BigDecimal total = new BigDecimal(0);
		total = total.add(produtoPedido.getValorUnitario()).multiply(new BigDecimal(quantidade));
		produtoPedido.setValorTotal(total);
		preencherPedido();
		//calcularFretePedido(cep);
		
	}

	@Override
	public void excluirProdutoPedidoCarrinho(String chave) {

		mapProdutoPedidos.remove(chave);
		preencherPedido();
		calcularFretePedido(cep);
	}

	@Override
	public BigDecimal calcularFretePedido(String cep) {
		this.cep = cep;
		
		Pedido pedido = obterPedidoCarrinho();
		//obter pacote ou pacotes de entrega
		//List<Pacote> pacotes = null;
		
		URL url;
		try {
			url = new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?WSDL");
			
			   //1st argument service URI, refer to wsdl document above
	        QName qname = new QName("http://tempuri.org/", "CalcPrecoPrazoWS");

	        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
	        CalcPrecoPrazoWSSoap calcPrecoPrazoWSSoap = service.getPort(CalcPrecoPrazoWSSoap.class);
	        
	    	
			//Parametros
			String nCdEmpresa = "";
			String sDsSenha = "";
			String nCdServico = "04014";
			String sCepOrigem = "02989010";
			String sCepDestino = "01311300";
			String nVlPeso = "0.2";
			int nCdFormato = 1;
			BigDecimal nVlComprimento = new BigDecimal(27);
			BigDecimal nVlAltura = new BigDecimal(9);
			BigDecimal nVlLargura = new BigDecimal(18);
			BigDecimal nVlDiametro = new BigDecimal(0);
			String sCdMaoPropria = "N";
			BigDecimal nVlValorDeclarado = pedido.getValorProdutos();
			String sCdAvisoRecebimento = "S";
	        
	        CResultado cresultado = calcPrecoPrazoWSSoap.calcPrecoPrazo(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
	        if(cresultado != null && cresultado.getServicos() !=null && cresultado.getServicos().getCServico() !=null && cresultado.getServicos().getCServico().size()>=1){
	        	
	        	String valorFreteString =  cresultado.getServicos().getCServico().get(0).getValor();
	        	valorFreteString = Valores.desformataMoeda(valorFreteString);
	        	
	        	BigDecimal valorFrete = new BigDecimal(valorFreteString);
	        	if(pedido != null){
	        		pedido.setValorFrete(valorFrete);
	        	}
	        	BigDecimal valorTotal = new BigDecimal(0);
	        	valorTotal = valorTotal.add(valorFrete).add(pedido.getValorProdutos());
	        	pedido.setValorTotal(valorTotal);
	        	return valorFrete;
	        	
	        }
			
		} catch (MalformedURLException e) {
			
			log.error(e.getMessage());
		}
        
		return null;
	}

}
