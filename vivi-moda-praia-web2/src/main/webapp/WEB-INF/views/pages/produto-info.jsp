<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<spring:url var="action" value="/info-produto/add/foto" />
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
	<ol class="breadcrumb caminhoPao">
		<li><a href="#">${produto.categoria.descricao}</a></li>
		<li><a href="#">${produto.subcategoria.descricao}</a></li>
		<li class="active">${produto.descricao}</li>
	</ol>

	<div class="media">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title" ><strong>${produto.descricao}</strong></h3>
			</div>
			<div style="padding-left: 1.5em;" class="descricaoProduto">
				<c:if test="${!produto.aplicarDesconto}">
					<h2>
						<fmt:formatNumber value="${produto.valor}" type="currency"/>
					</h2>
				</c:if>
				<c:if test="${produto.aplicarDesconto}">
                	
                	<h2>
                  		<fmt:formatNumber value="${produto.descontoValor}" type="currency"/>
                 	</h2>
                 	<strike>
                		<span class="valorProdutoSemDesconto">
                  			<fmt:formatNumber value="${produto.valor}" type="currency"/>
            			</span>
                	</strike>
               		<span class="percentualDesconto">
               			<strong>
                		 	&nbsp;(<fmt:formatNumber  value="${produto.descontoPercentual}"/>% de desconto)
               			</strong>
               		</span>
                 	
               	</c:if>
				
				<br/>
				<br/> 	
					${produto.informacoes}
				<br/>
		    </div>
		 	<div class="panel-body">
				<div class="row">
					<div class="col-md-6" >
						<div>
							<div >
								<center>
									<div id="divFotoProduto">
					  		 			<img class="thumbnail media-object" id="fotoSelecionada" data-zoom-image="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=normal" />"  width="90%" height="100%" src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=normal" />"  />
										<script>
											$("#fotoSelecionada").elevateZoom();
										</script>
									</div>
								<div style="width: auto;">
									<div class="scroller scroller-left"><i class="glyphicon glyphicon-chevron-left"></i></div>
					 				<div class="scroller scroller-right"><i class="glyphicon glyphicon-chevron-right"></i></div>
									<div class="wrapper" id="navImagem">
										<ul class="nav nav-tabs list" >
												<c:if test="${produto.imagemProduto1 != null}">
													<li>
														<a href="" onclick="mudarFoto(${produto.imagemProduto1.id})"  style="padding-bottom: 0 !important;padding-top: 0" >
															<img width="100%" height="100%" src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=pequeno"/>"   />
														</a>
													</li>
												</c:if>
												<c:if test="${produto.imagemProduto2 != null}">
													<li>
														<a href="" onclick="mudarFoto(${produto.imagemProduto2.id})"  style="padding-bottom: 0 !important;padding-top: 0" >
															<img id="fotoSelecionada" width="100%" height="100%" src="<c:url value="/image?id=${produto.imagemProduto2.id}&tamanhoImagem=pequeno"/>" />
														</a>
													</li>
												</c:if>	
											<c:forEach var="imagemProduto" items="${produto.imagensProduto}">
												<li>
													<a href="" onclick="mudarFoto(${imagemProduto.id})"  style="padding-bottom: 0 !important;padding-top: 0" >
														<img width="100%" height="100%" src="<c:url value="/image?id=${imagemProduto.id}&tamanhoImagem=pequeno"/>" />
													</a>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
								</center>
							</div>
							<div class="container">
									
							</div>
						</div>
						<br/>
				  	</div>
				  	<div class="col-md-6">
				  		<div id="formularioProdutoPedido">
					  		<jsp:include page="componentes/form-entrada-produto-carrinho.jsp" />
				  		</div>
					</div>
				</div>
				
		 </div>
	</div>
	</div>
	<div  class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Avalia&ccedil;&otilde;es</strong>
			</h3>
		</div>
	 	<div id="divAvaliacao" class="panel-body">
	 		
			<jsp:include page="componentes/comp-avaliacoes.jsp" />
	  	</div>
	</div>
	<div class="panel panel-default">
				
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Tags</strong></h3>
		</div>
	 	<div class="panel-body">

	  	</div>
	</div>
<script>

var app2 = angular.module('appSunVibes', []);
app2.controller('myCtrlAvaliacao', function($scope, $http) {
	$scope.teste = function() {
		
		alert('teste');
	}
	$scope.teste();
	/*
	$scope.carregarMaisComentarios = function(idProduto) {
		alert('entrou no metodo');
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var nextUrl = document.getElementById('nextUrl').value;
		var $home = $('#home').attr('value');
		var headers = {};
		headers[csrfHeader] = csrfToken;
		
		var config = {
			    params: {
			    	idProduto: idProduto
			    },
			    headers: headers
		};
		$http.get(nextUrl,config).then(function(response) {
			$scope.avaliacoes = response.data;
		});
		
			
	};
	*/
	
	//$scope.carregarMaisComentarios(146);
	
});
function carregarFormularioAvaliacao(idProduto){
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	
	var $home = $('#home').attr('value');
	var parametros = 'idProduto=' + idProduto;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	$.ajax({
	    url: $home + '/carregar-form-avaliacao',
	    type: 'GET',
	    headers: headers,
	    data: parametros,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (returndata) {
	    	$("#divAvaliacao").html($(returndata));
	    }
	  });		
	
}

function carregarAvaliacoes(idProduto){
	
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	
	var $home = $('#home').attr('value');
	var parametros = 'idProduto=' + idProduto;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	$.ajax({
	    url: $home + '/carregar-comentarios',
	    type: 'GET',
	    headers: headers,
	    data: parametros,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (returndata) {
	    	$("#divAvaliacao").html($(returndata));
	    }
	  });	
}
$(document).ready(function() {
	carregarAvaliacoes(${produto.id});
});
</script>
<script src="<c:url value="/js/site/zoomImagem.js"  />"></script>
<script src="<c:url value="/js/site/scroolThumbsImage.js"  />"></script>
