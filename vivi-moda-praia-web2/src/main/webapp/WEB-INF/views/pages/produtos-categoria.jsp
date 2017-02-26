<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Jumbotron Header -->
	<div>
		
		<form>
        <header class="jumbotron hero-spacer">
            <h1>A Warm Welcome!</h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsa, ipsam, eligendi, in quo sunt possimus non incidunt odit vero aliquid similique quaerat nam nobis illo aspernatur vitae fugiat numquam repellat.</p>
            <p><a class="btn btn-primary btn-large">Call to action!</a>
            </p>
        </header>
		<input id="home" type="hidden" value="${home}"/>
		<c:url var="home" value="/" scope="request" />
        <hr>

        <!-- Title -->
        <div class="row">
            <div class="col-lg-12">
                <h2>${categoria.descricao}</h2>
            </div>
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
	                        <h4>
	                        	<fmt:formatNumber value="${produto.valor}" type="currency"/>
	                        </h4>
	                        <p>detalhes do produto</p>
							<p>
	                        	
							</p>				                        
	                        <p>
	                        	<input id="qtd_${produto.id}" type="number" class="form-control text-center" value="1" min="1" style="width:100%;text-align: center;" >
	                            <button type="button" class="btn btn-primary glyphicon glyphicon-shopping-cart" ng-click="adicionarProdutoCarrinho(${produto.id},1)"></button>
	                            <a href="<c:url value="/info-produto?idProduto=${produto.id}" />" class="btn btn-default">+ info</a>
	                        </p>
	                    </div>
	                </div>
	            </div>
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
       