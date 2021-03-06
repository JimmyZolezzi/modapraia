<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h3>
	<spring:message  code="${pedido.statusPedido}" />
</h3>
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
								${varQuantidadde}
							</td>
							<td data-th="Subtotal" class="text-center"><fmt:formatNumber value="${produtoPedido.valorTotal}" type="currency"/></td>
							<td class="actions" data-th="">
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
			<strong>Frete: <span id="valorFrete"><fmt:formatNumber value="${pedido.valorFrete}" type="currency"/></span></strong>
		</div>
		<div class="valorTotal">
			<strong>Total: <span id="valorTotal"><fmt:formatNumber value="${pedido.valorTotal}" type="currency"/></span></strong>
		</div>
		</div>
	</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Dados do Cliente</strong></h3>
	</div>
	<div class="panel-body">
	  <div class="form-group">
	    <label>Nome Completo </label>
	    ${pedido.cliente.nome}
	  </div>
	  <div class="form-group">
	    <label>RG </label>
	  	${pedido.cliente.rg}
	  </div>
	  <div class="form-group ">
	    <label>CPF </label>
	    ${pedido.cliente.cpfCnpj}
	  </div>
	  <div class="form-group">
	    <label>Data de Nascimento</label>
	    <fmt:formatDate value="${pedido.cliente.dataNascimento}" pattern="dd/MM/yyyy" />
	  </div>	
  	 <div class="form-group">
	    <label>Telefone</label>
	     ${pedido.cliente.telefone}
	  </div>	
  	 <div class="form-group">
	    <label>Celular</label>
	    ${pedido.cliente.celular}
	  </div>	
	  <div class="form-group">
	    <label>E-mail</label>
	    ${pedido.cliente.email}
	  </div>

 	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Endereço de Entrega</strong></h3>
	</div>
	<div class="panel-body">
	  <div class="form-group">
	    <label>CEP </label>
	    ${pedido.enderecoEntrega.cep}
	  </div>
	  <div class="form-group">
	    <label>Logradouro </label>
	    ${pedido.enderecoEntrega.endereco}
	  </div>
	  <div class="form-group">
	    <label>Numero </label>
	    ${pedido.enderecoEntrega.numero}
	  </div>
	  <div class="form-group">
	    <label>Estado </label>
	    ${pedido.enderecoEntrega.estado}
	  </div>
	  <div class="form-group">
	    <label>Cidade </label>
	    ${pedido.enderecoEntrega.cidade}
	  </div>
	  <div class="form-group">
	    <label>Bairro </label>
	    ${pedido.enderecoEntrega.bairro}
	  </div>
 	</div>
</div>