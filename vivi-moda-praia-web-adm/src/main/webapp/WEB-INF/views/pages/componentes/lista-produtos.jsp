<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="app" ng-app='appProduto' ng-controller="myCtrlProduto">
<div id="listaProdutos">
	<c:url var="firstUrl" value="/lista/produtos/pages/1" />
	<c:url var="lastUrl" value="/lista/produtos/pages/${produtoPage.totalPages}" />
	<c:url var="prevUrl" value="/lista/produtos/pages/${currentIndex - 1}" />
	<c:url var="nextUrl" value="/lista/produtos/pages/${currentIndex + 1}" />
	
	<nav aria-label="...">
	  <ul class="pagination">
	    <c:choose>
	    	<c:when test="${currentIndex == 1}">
			    <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
	    	</c:when>
    	 	<c:otherwise>
			    <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
    	    </c:otherwise>
    	 </c:choose>
    	    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
	            <c:url var="pageUrl" value="/lista/produtos/pages/${i}" />
	            <c:choose>
	                <c:when test="${i == currentIndex}">
	                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
	                </c:when>
	                <c:otherwise>
	                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	        <c:choose>
	            <c:when test="${currentIndex == produtoPage.totalPages}">
	                <li class="disabled"><a href="#">&gt;</a></li>
	                <li class="disabled"><a href="#">&gt;&gt;</a></li>
	            </c:when>
	            <c:otherwise>
	                <li><a href="${nextUrl}">&gt;</a></li>
	                <li><a href="${lastUrl}">&gt;&gt;</a></li>
	            </c:otherwise>
	        </c:choose>
	  </ul>
	</nav>
	
	<div id="listaProdutos">
		<div class="table-responsive">
		  <!-- Default panel contents -->
		  	<table  class="table table-hover table-striped">
		  		<thead>
		  			<tr>
		  				<th>Imagem</th>
		  				<th>Descri&ccedil;&atilde;o</th>
		  				<th>Categoria</th>
		  				<th>...</th>
		  			</tr>
		  		</thead>
		  		<tbody>
			  		 <c:forEach var="produto" items="${produtoPage.content}">
					  <tr>
					     <td>
					     	<img src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=pequeno"/>" />
					     </td>
					     <td>${produto.descricao}</td>
					     <td>${produto.categoria.descricao}</td>
					      <td align="right">
						     	<a href="<c:url value="/detalhe-produto?idProduto=${produto.id}" />" class="btn btn-primary">Info</a>
							     	<button type="button" class="btn btn-warning" onclick="produtoAlteracao(${produto.id})">Alterar</button>
						     	 	<button type="button" class="btn btn-danger" ng-click="removerProduto(produto.id)">
								    	<span class="glyphicon glyphicon-floppy-remove"> </span>
								    </button>
					      </td>
					  </tr>
			  		 </c:forEach>
		  		 </tbody>	
		  	</table>
		</div>
	</div>
</div>
</div>
<script>
var app = angular.module('appProduto', []);

app.controller('myCtrlProduto', function($scope, $http) {
	
	var $home = $('#home').attr('value');
	
	$scope.pesquisaOnChangeCategoria = function(idCategoria){
		var config = {
		    params: {
		        categoria: idCategoria
		    }
		};
	
		$http.get($home + "pesquisa/categoria/onChange",config).then(function(response){
			$scope.subcategorias = response.data;
		});
	};
	
	
	$scope.removerProduto = function(id){
		var config = {
			    params: {
			        idProduto: id
			    }
			};
		$http.get($home + "produto/remover", config)
        .then(function (response) {
        	alert(response.data.status);
        	$scope.pesquisaAngular();
        });
	};
	
	$scope.carregarProdutoAlteracao = function(id){
		produtoAlteracao(id);
	
		
	};
	
	$scope.pesquisaAngular = function() {
		$http.get($home + "produto/pesquisa").then(function(response) {
			$scope.produtos = response.data;
		});

	};
	$scope.pesquisaAngular();


	
	

});
</script>