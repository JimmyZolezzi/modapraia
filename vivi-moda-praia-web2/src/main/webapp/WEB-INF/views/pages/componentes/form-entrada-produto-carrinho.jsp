<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<form:form action="" method="post" modelAttribute="formProdutoPedido">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Quantidade</strong></h3>
		</div>
		<div class="panel-body">
			<input id="quantidade" name="quantidade" type="number" class="form-control text-center" min="1" value="1">
		</div>
	</div>	
	<c:forEach var="produtoPedido" items="${formProdutoPedido.listaProdutoPedido}">
		<div class="panel panel-default">		
			<div class="panel-heading">
				<h3 class="panel-title" ><strong>Medidas</strong></h3>
			</div>
			<div class="panel-body">
				<c:forEach var="itemPedidoTamanho" items="${produtoPedido.itensPedidoTamanho}">
					<div class="form-group">
						<label>Tamanho  ${itemPedidoTamanho.nome}: </label>
						<form:select path="${itemPedidoTamanho.tamanho}">
							<form:options itemLabel="tamanho" itemValue="tamanho" items="${itemPedidoTamanho.itemProduto.itensEstoque}"/>
						</form:select>
					</div>	
				</c:forEach>
			</div>		
		</div>	
	</c:forEach>	 	
	<button type="button" class="btn btn-primary glyphicon glyphicon-shopping-cart" ng-click="adicionarProdutoCarrinho(${produto.id},1)">Adicionar</button>
</form:form>
</html>
