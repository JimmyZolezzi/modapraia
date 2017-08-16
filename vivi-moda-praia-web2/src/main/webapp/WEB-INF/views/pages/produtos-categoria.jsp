<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ol class="breadcrumb caminhoPao">
	<c:if test="${categoria !=null}">
		<li ${subcategoria == null?'class="active"':'' }>${categoria.descricao}</li>
	</c:if>
	<c:if test="${subcategoria !=null}">
		<li class="active">${subcategoria.descricao}</li>
	</c:if>
</ol>

<!-- Jumbotron Header -->
	<div>
		
		<form>
		<input id="home" type="hidden" value="${home}"/>
		<c:url var="home" value="/" scope="request" />
        <hr>

        <!-- Title -->
        <div class="row">
        	<br/>
           
        </div>
        <!-- /.row -->
		<style type="text/css">
			.none{
				display: none !important;
			}
		
		</style>
        <!-- Page Features -->
        <div class="row text-center" >
			<c:forEach var="produto" items="${produtosCategoria}">
				<c:if test="${produto.disponivelEstoque }">
					<div class="col-md-3 col-sm-6 hero-feature">
		                <div class="thumbnail"  ${produto.imagemProduto2!=null? 'onmouseover="trocarFotoPara2('.concat(produto.id).concat(')"').concat(' onmouseout="trocarFotoPara1(').concat(produto.id).concat(')"'):'' }>
		                	<c:if test="${produto.imagemProduto1 != null}">
			                    <img id="img_1_${produto.id}" src="<c:url  value="//image?id=${produto.imagemProduto1.id}&tamanhoImagem=normal"/>" alt="">
		                	</c:if>
		                	<c:if test="${produto.imagemProduto2 != null}">
		                    	<img id="img_2_${produto.id}" src="<c:url value="//image?id=${produto.imagemProduto2.id}&tamanhoImagem=normal"/>" alt="" class="none">
		                    </c:if>
		                    <div class="caption">
		                        <h3>${produto.descricao}</h3>
		                        <p>${produto.informacoes}</p>
									<c:if test="${produto.quantidadeAvaliacao > 0}">
			                        	<span class="avaliacoes-mini">
										<strong>
										<span class="nota">
										<fmt:formatNumber var="mediaAvalicao"  value="${produto.mediaAvaliacao }" maxFractionDigits="1" />
										${mediaAvalicao }	
										</span>&nbsp;&nbsp;
										<span class="estrelas">
											<span class="glyphicon glyphicon-star"></span>
											<span class="glyphicon glyphicon-star${produto.mediaAvaliacao < 1.5 ? '-empty':'' }"></span>
											<span class="glyphicon glyphicon-star${produto.mediaAvaliacao < 2.5 ? '-empty':'' }"></span>
											<span class="glyphicon glyphicon-star${produto.mediaAvaliacao < 3.5 ? '-empty':'' }"></span>
											<span class="glyphicon  glyphicon-star${produto.mediaAvaliacao < 4.5 ? '-empty':'' }"></span>
										</span>
										<span class="quantidade-avaliacoes" aria-hidden="true">(${produto.quantidadeAvaliacao })</span>
										</strong>
										</span>
									</c:if>
										<br/>
		                        	<c:if test="${!produto.aplicarDesconto}">
		                        		<h4>
			                        		<fmt:formatNumber value="${produto.valor}" type="currency"/>
		                        		</h4>
		                        	</c:if>
		                        	<c:if test="${produto.aplicarDesconto}">
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
			                        	<h4>
				                        	<fmt:formatNumber value="${produto.descontoValor}" type="currency"/>
			                        	</h4>
		                        	</c:if>
		                        <p>
		                            <a href="<c:url value="/produto/${produto.id}" />" class="btn btn-default">Comprar</a>
		                        </p>
		                    </div>
		                </div>
		            </div>
				</c:if>
			</c:forEach>
        </div>
        </form>
	</div>
	
<script>
	
	function trocarFotoPara2(idProduto){
		var imagem1 = document.getElementById("img_1_" + idProduto);
		var imagem2 = document.getElementById("img_2_" + idProduto);
		$(imagem1).addClass('none');
		$(imagem2).removeClass('none');
		
	}
	function trocarFotoPara1(idProduto){
		var imagem1 = document.getElementById("img_1_" + idProduto);
		var imagem2 = document.getElementById("img_2_" + idProduto)
		
		$(imagem1).removeClass('none');
		$(imagem2).addClass('none');
		
	}

	var app = angular.module('appProduto', []);
		app.controller('myCtrlProduto', function($scope, $http) {
		
			var $home = $('#home').attr('value');

			
	});


</script>
       