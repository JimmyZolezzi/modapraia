<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<spring:url var="action" value="/produto/add" />
<c:url var="home" value="/" scope="request" />	
<c:url var="idCategoriaSelecionada" value="{{idCategoriaSelecionada}}" scope="request" />
<input id="home" type="hidden" value="${home}"/>

<!-- Custom CSS -->
	<div ng-app='appProduto' ng-controller="myCtrlProduto">
	<form:form method="post" id="form" modelAttribute="produtoForm" action="${action}" enctype="multipart/form-data">
	<div id="formulario">
	<h2>
		<c:if test="${btnHabilitadoCadastrar}">
			<a href="<c:url value="/cadastro-produto" />">Produto Novo</a>
		</c:if>
	</h2>
	<h3>Cadastro de Produtos</h3>
			<form:hidden path="id" />
			<spring:bind path="descricao">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="descricao">Descri&ccedil;&atilde;o</label>
					<form:input  path="descricao" type="text"  class="form-control" id="descricao" placeholder="Descrição" value="${produtoForm.descricao}" />
					<label class="control-label" for="descricao"><form:errors path="descricao"/></label>
				</div>
			</spring:bind>
			<spring:bind path="informacoes">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="informacoes">Informa&ccedil;&otilde;es</label>
					<form:textarea path="informacoes" class="form-control" rows="5" />
					<label class="control-label" for="informacoes"><form:errors path="informacoes"/></label>
				</div>
			</spring:bind>
			<spring:bind path="categoria">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="categoria">Categoria</label>
	                <form:select path="categoria"  id="categoria" class="form-control" ng-init="categoria='${idCategoriaSelecionada!=idCategoriaSelecionada?idCategoriaSelecionada:''}${produtoForm.categoria.id}'"  
	                 ng-model="categoria"   ng-change="pesquisaOnChangeCategoria(categoria)">
	        			<option value=''>selecionar</option>
	        			<form:options  items="${categorias}" itemLabel="descricao" itemValue="id"  />
	   				</form:select>
		   			<label class="control-label" for="categoria"><form:errors path="categoria"/></label>
				</div>
			</spring:bind>
			
			<spring:bind path="subcategoria">	
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="subcategoria">Subcategoria</label>
		               <form:select path="subcategoria" id="subcategoria" ng-init="subcategoria='${produtoForm.subcategoria.id}'"  ng-model="subcategoria" class="form-control"  >
		               	<form:option  value="">Selecionar</form:option>
			        		<option ng-repeat="subcategoria in subcategorias" value="{{subcategoria.id}}" >{{subcategoria.descricao}}</option>
		       			<form:options items="${subcategorias}" itemValue="id" itemLabel="descricao"/>
		   			</form:select>
		   			<label class="control-label" for="subcategoria"><form:errors path="subcategoria"/></label>
				</div>
			</spring:bind>
			<spring:bind path="tipoMedida">	
				<div class="form-group ${status.error?'has-error':''} ">
		            <label>Tipo de Medida</label>
		            <div>
		            	<form:radiobuttons path="tipoMedida" items="${medidas}" itemLabel="nome" itemValue="nome" cssStyle="margin-right: 10px;font-weight:200 !important"/>
		            	
		            </div>
		            <label class="control-label" for="tipoMedida"><form:errors path="tipoMedida"/></label>
		        </div>
	        </spring:bind>
			<div class="form-group ">
				<label>Foto 1</label>
				<c:if test="${produtoForm !=null && produtoForm.imagemProduto1 != null && produtoForm.imagemProduto1.id !=0}">
					<img src='<c:url value="/image?id=${produtoForm.imagemProduto1.id}&tamanhoImagem=medio"/>'/>
				</c:if>
				<c:if test="${produtoForm ==null || produtoForm.imagemProduto1 == null || produtoForm.imagemProduto1.id ==0}">
					<form:input path="foto1" type="file" name="foto1" id="foto1"/> 
				</c:if>
			</div>
			<div class="form-group  ">
				<label>Foto 2</label>
				<c:if test="${produtoForm !=null && produtoForm.imagemProduto2 != null && produtoForm.imagemProduto2.id !=0}">
					<img src="//image?id=${produtoForm.imagemProduto2.id}&tamanhoImagem=medio"/>
				</c:if>
				<c:if test="${produtoForm ==null || produtoForm.imagemProduto2 == null || produtoForm.imagemProduto2.id ==0}">
					<form:input type="file" path="foto2" id="foto2"/>  
				</c:if>
			</div>
			<spring:bind path="valor">	
				<div class="form-group ${status.error?'has-error':''} ">
					<label class="sr-only" for="valor">Valor (em
						reais)</label>
					<div class="input-group">
						<div class="input-group-addon">R$</div>
						<form:input path="valor" type="text" class="form-control"
							id="exampleInputAmount" placeholder="Valor" />
						<div class="input-group-addon">.00</div>
					</div>
					<label class="control-label" for="valor"><form:errors path="valor"/></label>
				</div>
			</spring:bind>	
			<c:if test="${not empty css}">
				<br/>
				<div class="alert ${css}">
				 	${msg}
				</div>
			</c:if>
		</div>	

			<button id="botaoFormulario" type="submit" class="btn btn-primary">Cadastrar</button>
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
					     	
					     	<img src="<c:url value="//image?id={{produto.imagemProduto1.id}}&tamanhoImagem=pequeno"/>" />
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
				
	</form:form>
	</div>
	<script type="text/javascript">
	$("#form").submit(function() {
		
		
		// setup some local variables
		var $form = $("#form");
		
		// let's select and cache all the fields
		// serialize the data in the form
		var formData = new FormData($form);
		// fire off the request to /action
		request = $.ajax({
			url : $form.attr('action'),
			type : "post",
			data : formData,
			success : function(data) {
				$("#formulario").html($(data).find("#formulario"));
				//pesquisar();
				console.log("SUCCESS: ", data);
			},
			error : function(e) {
				console.log(e);
			},
		});
		// prevent default posting of form
		event.preventDefault();
	});
	
	function produtoAlteracao(idProduto){
		window.scrollTo(0, 0);
		var $form = $("#form");
		var $home = $('#home').attr('value');
		// let's select and cache all the fields
		// serialize the data in the form
		var formData = new FormData($form);
		// fire off the request to /action
		
	
		request = $.ajax({
			url : $home + "produto/carregar/alteracao",
			type : "get",
			data :  {idProduto:idProduto},
			success : function(data) {
				$("#formulario").html($(data).find("#formulario"));
			},
			error : function(e) {
				console.log(e);
			}
		});
		
		$("#form").attr('action', $home + "/produto/alterar");
		$("#botaoFormulario").html('Alterar');
	}
	
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
			$scope.pesquisaAngular = function() {
				$http.get($home + "produto/pesquisa").then(function(response) {
					$scope.produtos = response.data;
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
				/*
				var config = {
					    params: {
					        idProduto: id
					    }
					};
				
				$http.get($home + "produto/carregar/alteracao", config).then(function (response) {
					$scope.formProduto = response.data;
		        	$scope.idCategoriaSelecionada = response.data.categoria.id;
		        	$scope.categoriaSelecionada = response.data.categoria;
		        	$scope.pesquisaAngular();
		        });*/
			};
			
			
			$scope.pesquisaAngular();
		});
		
		function pesquisar() {
			angular.element($("#myCtrlProduto")).scope().pesquisaAngular();
		};
	</script>
</html>