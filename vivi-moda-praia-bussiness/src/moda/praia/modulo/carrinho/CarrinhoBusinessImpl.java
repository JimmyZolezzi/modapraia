package moda.praia.modulo.carrinho;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import moda.praia.modulo.correios.bean.Pacote;
import moda.praia.modulo.correios.webservices.CResultado;
import moda.praia.modulo.correios.webservices.CServico;
import moda.praia.modulo.correios.webservices.CalcPrecoPrazoWSLocator;
import moda.praia.modulo.correios.webservices.CalcPrecoPrazoWSSoap;
import moda.praia.modulo.correios.webservices.CalcPrecoPrazoWSSoapStub;
import moda.praia.modulo.correios.webservices.CodigosServicos;
import moda.praia.modulo.pedido.bean.ItemPedidoTamanho;
import moda.praia.modulo.pedido.bean.Pedido;
import moda.praia.modulo.pedido.bean.ProdutoPedido;
import moda.praia.modulo.produtos.ProdutoBusinessImpl;
import moda.praia.modulo.produtos.bean.Produto;
import moda.praia.uteis.Valores;

@Service
@Scope(value="session",proxyMode = ScopedProxyMode.INTERFACES)
public class CarrinhoBusinessImpl implements CarrinhoBusiness {
	
	private final Logger log = Logger.getLogger(ProdutoBusinessImpl.class);

	private Map<String, ProdutoPedido> mapProdutoPedidos = new HashMap<>(); 
	private Pedido pedido;
	
	@Override
	public void colocarProdutoCarrinho(Produto produto, int quantidade) {
		
		if(produto != null && produto.getValor() != null && quantidade !=0){
			ProdutoPedido produtoPedido = new ProdutoPedido();
			produtoPedido.setProduto(produto);
			produtoPedido.setQuantidade(quantidade);
			produtoPedido.setValorUnitario(produto.getValor());
			BigDecimal valorTotal = BigDecimal.ZERO;
			valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
			produtoPedido.setValorToral(valorTotal);
			mapProdutoPedidos.put(String.valueOf(produto.getId()), produtoPedido);
		}
	}

	@Override
	public Pedido obterPedidoCarrinho() {
		
		pedido = new Pedido();
		
		Set<String> chavesProdutoPedido = mapProdutoPedidos.keySet();
		List<ProdutoPedido> listaProdutoPedido = new ArrayList<ProdutoPedido>();
		BigDecimal totalProdutos = new BigDecimal(0);
		for (String chave : chavesProdutoPedido) {
			ProdutoPedido produtoPedido = mapProdutoPedidos.get(chave);
			listaProdutoPedido.add(produtoPedido);
			totalProdutos = totalProdutos.add(produtoPedido.getValorToral());
		}
		pedido.setProdutosPedido(listaProdutoPedido);
		pedido.setValorProdutos(totalProdutos);
		return pedido;
	}

	@Override
	public boolean excluirProdutoCarrinho(Produto produto) {

		mapProdutoPedidos.remove(produto.getId());
		return true;
	}

	
	@Override
	public void colocarProdutoCarrinho(ProdutoPedido produtoPedido) {
		
		if(produtoPedido != null && produtoPedido.getProduto() != null && produtoPedido.getQuantidade() !=0){
			String chave = obterChaveProdutoPedido(produtoPedido);
			produtoPedido.setChave(chave);
			mapProdutoPedidos.put(chave, produtoPedido);
		}
		
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
		produtoPedido.setValorToral(total);
		
		
	}

	@Override
	public void excluirProdutoPedidoCarrinho(String chave) {

		mapProdutoPedidos.remove(chave);
	}

	@Override
	public BigDecimal calcularFretePedido(String cep) {
		
		Pedido pedido = obterPedidoCarrinho();
		//obter pacote ou pacotes de entrega
		List<Pacote> pacotes = null;
		
		
		//Parametros
		String nCdEmpresa = "";
		String sDsSenha = "";
		String nCdServico = CodigosServicos.SEDEX_VAREJO;
		String sCepOrigem = "01311300";
		String sCepDestino = cep;
		String nVlPeso = "0.2";
		int nCdFormato = 1;
		BigDecimal nVlComprimento = new BigDecimal(27);
		BigDecimal nVlAltura = new BigDecimal(9);
		BigDecimal nVlLargura = new BigDecimal(18);
		BigDecimal nVlDiametro = new BigDecimal(0);
		String sCdMaoPropria = "N";
		BigDecimal nVlValorDeclarado = pedido.getValorProdutos();
		String sCdAvisoRecebimento = "S";
		
		CalcPrecoPrazoWSLocator locator = new CalcPrecoPrazoWSLocator();
		try {
			
			CalcPrecoPrazoWSSoapStub stub = (CalcPrecoPrazoWSSoapStub) locator.getPort(CalcPrecoPrazoWSSoap.class);
			CResultado resultado;
			resultado = stub.calcPrecoPrazo(nCdEmpresa, sDsSenha, nCdServico, sCepOrigem, sCepDestino, nVlPeso, nCdFormato, nVlComprimento, nVlAltura, nVlLargura, nVlDiametro, sCdMaoPropria, nVlValorDeclarado, sCdAvisoRecebimento);
			if(resultado.getServicos() != null && resultado.getServicos().length >=1){
				CServico servico = resultado.getServicos()[0];
				if(servico != null && (servico.getErro()==null || servico.getErro().equals(""))){
					String valorFreteString = servico.getValor();
					BigDecimal valorFrete = new BigDecimal(valorFreteString);
					pedido.setValorFrete(valorFrete);
					return valorFrete;
				}
			}
			
	
		} catch (RemoteException e) {
		
			log.error(e.getMessage());
		
		} catch (ServiceException e) {
	
			log.error(e.getMessage());
		}
		
		
		return null;
	}

}
