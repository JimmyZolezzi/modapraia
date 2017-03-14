<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title" >Pe&ccedil;as</h3>
	</div>
 	<div class="panel-body">
			
 			<div class="table-responsive">
			  <!-- Default panel contents -->
			  	<table  class="table table-hover table-striped">
			  		<thead>
			  			<tr>
			  				<th>Nome</th>
			  				<th>Tipo Medida</th>
			  				<th>QTD Estoque</th>
			  				<th>...</th>
			  			</tr>
			  		</thead>
			  		<tbody>
			  			<c:forEach var="itemProduto" items="${produto.itensProduto}" >
			  				<tr>
			  					<td>${itemProduto.nome}</td>
			  					<td>${itemProduto.tipoMedida.nome}</td>
			  					<td>
			  						<c:forEach var="itemEstoque" items="${itemProduto.itensEstoque}">
			  							<strong>Tamanho: </strong>
			  							${itemEstoque.tamanho}
			  							<strong>Quantidade: </strong>
			  							${itemEstoque.quantidade}<br/>
			  						</c:forEach>
			  					</td>
			  					<td>
			  						<a onclick="selecionarItemProduto(${itemProduto.id},${produto.id})" class="btn btn-primary">Selecionar</a>
			  					</td>
			  				</tr>
			  			</c:forEach>
			  		</tbody>
			  	</table>
			</div>
	</div>		
</div>