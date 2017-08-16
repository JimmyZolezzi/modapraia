<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<spring:url var="action" value="/produtoPedido/add/carrinho" />

<form:form id="form" action="${action}" method="post" modelAttribute="formProdutoPedido">
		<sec:csrfInput />
		<form:hidden path="idProduto"/>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title" ><strong>Quantidade</strong></h3>
			</div>
			<div class="panel-body">
				
				<spring:bind path="quantidade">
					<form:input path="quantidade" type="number" cssStyle="text-align:right;" onchange="addProdutoPedido(${produto.id})" class="form-control text-center" min="1" />
				</spring:bind>
				<label class="control-label" for="quantidade"><form:errors path="quantidade"/></label>
			</div>
		</div>	
		<c:forEach var="produtoPedido" items="${formProdutoPedido.listaProdutoPedido}" varStatus="statusProdutoPedido">
			<div class="panel panel-default">		
				<div class="panel-heading">
					<h3 class="panel-title" ><strong>Medidas</strong></h3>
				</div>
				<div class="panel-body">
				
					<c:forEach var="itemPedidoTamanho" items="${produtoPedido.itensPedidoTamanho}" varStatus="statusItemPedidoTamanho">
						<form:hidden path="listaProdutoPedido[${statusProdutoPedido.index }].quantidade"/>
						
						<spring:bind path="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].tamanho">
							<div class="form-group ${status.error?'has-error':''} " >
								<label>Tamanho  ${itemPedidoTamanho.nome}: </label>
								<form:hidden path="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].itemProduto.id"/>
								<form:hidden path="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].itemProduto.nome"/>
								<form:hidden path="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].nome"/>
								<form:select class="form-control text-center" path="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].tamanho">
									<form:option value="">selecionar</form:option>
									<c:forEach var="item" items="${itemPedidoTamanho.itemProduto.mapItemProdutoEstoque}">
										<c:if test="${item.value.quantidade - item.value.quantidadeReservada > 0}">
											<form:option value="${item.value.tamanho }">${item.value.tamanho }</form:option>
										</c:if>
									</c:forEach>
								</form:select>
								<label class="control-label" for="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].tamanho"><form:errors path="listaProdutoPedido[${statusProdutoPedido.index }].itensPedidoTamanho[${statusItemPedidoTamanho.index}].tamanho"/></label>	
							</div>
						</spring:bind>
					</c:forEach>
				</div>		
			</div>	
		</c:forEach>
		<button type="submit" class="btn btn-primary glyphicon glyphicon-shopping-cart">Adicionar</button>
</form:form>
<script type="text/javascript">

	function mudarFoto(idFotoSelecionada){
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		
		var $home = $('#home').attr('value');
		var parametros = 'idFoto=' + idFotoSelecionada		
		$.ajax({
		    url: $home + '/mudar/foto',
		    type: 'GET',
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#divFotoProduto").html($(returndata));
		    }
		  });		
		
	}
	
	function removerFoto(idProduto, idImagem){
		
		
		
		var parametros = 'idProduto=' + idProduto + '&idImagemProduto=' + idImagem;
		var $home = $('#home').attr('value');
		$.ajax({
		    url: $home + 'info-produto/remover/foto',
		    type: 'GET',
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#imagensProduto").html($(returndata).find("#imagensProduto"));
		    	$("#formulario").html($(returndata).find("#formulario"));
		    }
		  });
		
	}

	$("#form").submit(function(event){
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		
		var $home = $('#home').attr('value');
		event.preventDefault();
		// setup some local variables
		var $form = $("#form");
		
		// let's select and cache all the fields
		// serialize the data in the form
		var formData = new FormData($(this)[0]);
		// fire off the request to /action
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$.ajax({
		    url: $form.attr('action'),
		    type: 'POST',
		    headers: headers,
		    data: formData,
		    async: true,
		    cache: false,
		    contentType: false,
		  	processData: false,
		    success: function (returndata) {
		    	pagina = $(returndata).filter('#pagina')[0];
		    	if(typeof pagina != 'undefined' && pagina.value == 'paginaPedido'){
			    	$("#conteudo").html(returndata);	
		    		
		    	}else{
		    		$("#formularioProdutoPedido").html(returndata);	
		    	}
		    	$("div.zoomContainer").remove();
		    	atualizarCarrinho();
		    	scroll(0,0);
		    }
	    });
	});
	
	

	function addProdutoPedido(idProduto){
		
		var quantidade = document.getElementById('quantidade').value;
		
		var parametros = 'idProduto=' + idProduto + '&quantidade=' + quantidade;
		var $home = $('#home').attr('value');
		$.ajax({
		    url: $home + 'add/produto/pedido',
		    type: 'GET',
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("div.zoomContainer").remove();
		    	$("#formularioProdutoPedido").html($(returndata));
		    	scroll(0,0);
		    }
		  });
		
	}


</script>
</html>
