<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
				  <tr ng-repeat="produto in produtos">
				     <td>
				     	<img src="<c:url value="/image?id={{produto.imagemProduto1.id}}&tamanhoImagem=pequeno"/>" />
				     </td>
				     <td>{{produto.descricao}}</td>
				     <td>{{produto.categoria.descricao}}</td>
				     <td align="right">
				     	<a href="<c:url value="/info-produto?idProduto={{produto.id}}" />" class="btn btn-primary">Info</a>
				     	<button type="button" class="btn btn-warning" ng-click="carregarProdutoAlteracao(produto.id)">Alterar</button>
			     	 	<button type="button" class="btn btn-danger" ng-click="removerProduto(produto.id)">
					    	<span class="glyphicon glyphicon-floppy-remove"> </span>
					    </button>
				     </td>
				  </tr>
		  	</table>
		</div>
	</div>
</html>