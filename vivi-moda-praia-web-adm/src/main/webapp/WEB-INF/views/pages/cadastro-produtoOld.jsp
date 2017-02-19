<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<spring:url var="action" value="/produto/add" />
<c:url var="home" value="/" scope="request" />	
<input id="home" type="hidden" value="${home}"/>
<!-- Custom CSS -->
	<div ng-app='appProduto' ng-controller="myCtrlProduto">
	<h3>Cadastro de Produtos</h3>
	<form:form method="post" id="form" modelAttribute="produtoForm" action="${action}" enctype="multipart/form-data">
		<div id="formulario">
			<spring:bind path="descricao">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="descricao">Descri&ccedil;&atilde;o</label>
					<form:input  path="descricao" type="text" class="form-control" id="descricao" placeholder="Descrição"   />
					<label class="control-label" for="descricao"><form:errors path="descricao"/></label>
				</div>
			</spring:bind>
			<spring:bind path="categoria">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="categoria">Categoria</label>
		               <form:select path="categoria" id="categoria" class="form-control" ng-model="categoria" ng-change="pesquisaOnChangeCategoria(categoria)">
		               	<option  value="">selecionar</option>
		       			<form:options items="${categorias}" itemValue="id" itemLabel="descricao"/>
		   			</form:select>
		   			<label class="control-label" for="categoria"><form:errors path="categoria"/></label>
				</div>
			</spring:bind>
			<spring:bind path="subcategoria">	
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="subcategoria">Subcategoria</label>
		               <form:select path="subcategoria" id="subcategoria" class="form-control"  >
		               	<form:option  value="0">Selecionar</form:option>
			        			<option ng-repeat="subcategoria in subcategorias" value="{{subcategoria.id}}" >{{subcategoria.descricao}}</option>
		       			<form:options items="${subcategorias}" itemValue="id" itemLabel="descricao"/>
		   			</form:select>
		   			<label class="control-label" for="subcategoria"><form:errors path="subcategoria"/></label>
				</div>
			</spring:bind>
			<!-- 
			<spring:bind path="tipoMedida">	
				<div class="form-group ${status.error?'has-error':''} ">
		            <label>Tipo de Medida</label>
		            <div>
		            	<c:forEach var="medida" items="${medidas}">
		            		<form:radiobutton path="tipoMedida" value="${medida.nome}" label="${medida.nome}" cssStyle="margin-right: 10px;font-weight:200 !important"/>
		            	</c:forEach>
		            </div>
		            <label class="control-label" for="tipoMedida"><form:errors path="tipoMedida"/></label>
		        </div>
	        </spring:bind>
			 -->
			<div class="form-group ">
				<label>Foto 1</label> 
				<input type="file" name="foto1" id="foto1" />
			</div>
			<div class="form-group  ">
				<label>Foto 2</label> 
				<input type="file" name="foto2" id="foto2" />
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
			<button type="submit" class="btn btn-primary">Cadastrar</button>
			
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
			//	pesquisar();
				console.log("SUCCESS: ", data);
			},
			error : function(e) {
				console.log(e);
			},
		});

		// prevent default posting of form
		event.preventDefault();
	});
	
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
				$http.get($home + "subcategoria/pesquisa").then(function(response) {
					$scope.subcategorias = response.data;
					angular.forEach($scope.subcategorias, function(value, key) {
						value.categoria = JSON.parse(value.categoria);
					});
				});
	
			};			
		});
	
	</script>
</html>