<!DOCTYPE html>
<meta charset="UTF-8">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<spring:url var="action" value="" />
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
<ol class="breadcrumb">
	<li><a href="#">Estoque</a></li>
	<li><a href="#">${produto.categoria.descricao}</a></li>
	<li><a href="#">${produto.subcategoria.descricao}</a></li>
	<li class="active">${produto.descricao}</li>
</ol>

<div class="media">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Produto Estoque - </h3>
		</div>
		
	 	<div class="panel-body">
	    	<div class="media-left media-middle">
			<a href="#"> 
				<img class="media-object" src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=medio"/>" />
			</a>
			</div>
			<div class="media-body">
				<h4 class="media-heading">${produto.descricao}</h4>
				${produto.informacoes}
				<br/>
				<h3>
					<fmt:formatNumber value="${produto.valor}" type="currency"/>
				</h3>
			</div>
	  	</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Pe&ccedil;as</h3>
		</div>
	 	<div class="panel-body">
 			
	 			<div class="table-responsive" id="myctrl" ng-app='appCategoria' ng-controller="myCtrlCategoria">
				  <!-- Default panel contents -->
				  	<table  class="table table-hover table-striped">
				  		<thead>
				  			<tr>
				  				<th>Nome</th>
				  				<th>Tipo Medida</th>
				  				<th>QTD Estoque</th>
				  			</tr>
				  		</thead>
				  		<tbody>
				  			<c:forEach var="itemProduto" items="${produto.itensProduto}" >
				  				<tr>
				  					<td>${itemProduto.nome}</td>
				  					<td>${itemProduto.tipoMedida.nome}</td>
				  					<td align="right">${itemProduto.quantidadeEstoque}</td>		
				  				</tr>
				  			</c:forEach>
				  	</table>
				</div>
		</div>		
	</div>
</div>
