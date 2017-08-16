<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<h3>Carrinho de Compras</h3>
	
	<c:if test="${pedido.produtosPedido.size() == 0}">
		O seu carrinho de compras está vazio.
	</c:if>
	<c:if test="${pedido.produtosPedido.size() != 0}">
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Frete</strong></h3>
		</div>
	 	<div class="panel-body">
	 		 <div class="form-group">
			      <input type="text" class="form-control" name="inputCEP" id="inputCEP" onchange="calcularFretePedidoCarrinho()" placeholder="CEP">
			  </div>
	  	</div>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Produto(s)</strong></h3>
		</div>
	 	<div class="panel-body">
	 		 <table style="border: 0"  class="table table-hover table-condensed table-responsive">
				<thead>
					<tr>
						<th style="width:50%">Produto</th>
						<th style="width:10%"></th>
						<th style="width:10%"></th>
						<th style="width:10%"></th>
						<th style="width:10%">Valor</th>
						<th style="width:8%">QTD</th>
						<th style="width:22%" class="text-center">Subtotal</th>
						<th style="width:10%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="produtoPedido" items="${pedido.produtosPedido}">
						<tr>
							<td data-th="Product" colspan="4">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="<c:url  value="/image?id=${produtoPedido.produto.imagemProduto1.id}&tamanhoImagem=pequeno" />"alt=""  class="img-responsive"></div>
									<div class="col-sm-10">
										<h4 class="nomargin">${produtoPedido.produto.descricao}</h4>
										<p>
											<strong>Tamanho(s)</strong><br/>&nbsp;&nbsp;&nbsp;&nbsp;
											<c:forEach var="itemPedidoTamanho" items="${produtoPedido.itensPedidoTamanho}"  varStatus="statusItemPedidoTamanho">
												<strong>${itemPedidoTamanho.nome }:&nbsp;  </strong> ${itemPedidoTamanho.tamanho} 
												<c:if test="${statusItemPedidoTamanho.index < produtoPedido.itensPedidoTamanho.size() -1}">,</c:if>
											</c:forEach>
										</p>
									</div>
								</div>
							</td>
							<td data-th="Price"><fmt:formatNumber value="${produtoPedido.valorUnitario}" type="currency"/></td>
							<fmt:formatNumber pattern="#" value="${produtoPedido.quantidade}" var="varQuantidadde"  />
							<td data-th="Quantity">
								<input id="qtd_${produtoPedido.chave}" style="width: 40px;" type="number" value="${varQuantidadde}" min="1" onchange="mudarQuantidade('${produtoPedido.chave}')" >
							</td>
							<td data-th="Subtotal" class="text-center"><fmt:formatNumber value="${produtoPedido.valorTotal}" type="currency"/></td>
							<td class="actions" data-th="">
								<button class="btn btn-danger btn-sm" onclick="excluirProdutoPedido('${produtoPedido.chave}')"><i class="glyphicon glyphicon-trash"></i></button>								
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td class="valorTotal" colspan="4"></td>
						<td colspan="3" class="text-right valorTotal"></td>
						<td></td>
					</tr>
				</tfoot>
		</table>
		<div style="text-align: right;">
		${pedido.enderecoEntrega.endereco}
			<strong>Frete: <fmt:formatNumber value="${pedido.valorFrete}" type="currency"/></strong>
		</div>
		<div class="valorTotal">
			<strong>Total: <fmt:formatNumber value="${pedido.valorTotal}" type="currency"/></strong>
		</div>
		</div>
	</div>
	</c:if><br/>
	<a href="<c:url value="/home" />" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continuar Comprando</a>
	<c:if test="${pedido.produtosPedido.size() != 0}">
		<a href="<c:url value="/confirmar-dados" />" class="btn btn-success ">Confirmar Dados<i class="fa fa-angle-right"></i></a>
	</c:if>
	<br/><br/>