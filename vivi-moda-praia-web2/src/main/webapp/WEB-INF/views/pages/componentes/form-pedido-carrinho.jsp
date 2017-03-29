<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
	<h3>Carrinho de Compras</h3>
	
<table id="cart" class="table table-hover table-condensed table-responsive">
				<thead>
		<tr>
			<th style="width:50%">Produto</th>
			<th style="width:10%">Valor</th>
			<th style="width:8%">Quantidade</th>
			<th style="width:22%" class="text-center">Subtotal</th>
			<th style="width:10%"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="produtoPedido" items="${pedido.produtosPedido}">
			<tr>
				<td data-th="Product">
					<div class="row">
						<div class="col-sm-2 hidden-xs"><img src="<c:url  value="/image?id=${produtoPedido.produto.imagemProduto1.id}&tamanhoImagem=pequeno" />"alt=""  class="img-responsive"></div>
						<div class="col-sm-10">
							<h4 class="nomargin">${produtoPedido.produto.descricao}</h4>
							<p>
								<c:forEach var="itemPedidoTamanho" items="${produtoPedido.itensPedidoTamanho}" varStatus="statusItemPedidoTamanho">
									${itemPedidoTamanho.nome } - Tamanho ${itemPedidoTamanho.tamanho} <br/>
								</c:forEach>
							
							
							</p>
						</div>
					</div>
				</td>
				<td data-th="Price"><fmt:formatNumber value="${produtoPedido.valorUnitario}" type="currency"/></td>
				<fmt:formatNumber pattern="#" value="${produtoPedido.quantidade}" var="varQuantidadde"  />
				<td data-th="Quantity">
					<input id="qtd_${produtoPedido.chave}" type="number" value="${varQuantidadde}" min="1" onchange="mudarQuantidade('${produtoPedido.chave}')" >
				</td>
				<td data-th="Subtotal" class="text-center"><fmt:formatNumber value="${produtoPedido.valorToral}" type="currency"/></td>
				<td class="actions" data-th="">
					<button class="btn btn-danger btn-sm" onclick="excluirProdutoPedido('${produtoPedido.chave}')"><i class="glyphicon glyphicon-trash"></i></button>								
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr class="visible-xs">
			<td class="text-center"><strong>Total <fmt:formatNumber value="${pedido.valorProdutos}" type="currency"/></strong></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/home" />" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continuar Comprando</a></td>
			<td colspan="2" class="hidden-xs"></td>
			<td class="hidden-xs text-center"><strong>Total <fmt:formatNumber value="${pedido.valorProdutos}" type="currency"/></strong></td>
			<td><a href="pagina/finalizar/pedido" class="btn btn-success btn-block">Finalizar Compra <i class="fa fa-angle-right"></i></a></td>
		</tr>
	</tfoot>
</table>
</div>
