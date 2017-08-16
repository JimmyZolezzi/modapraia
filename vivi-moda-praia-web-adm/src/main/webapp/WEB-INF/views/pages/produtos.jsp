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
	<div id="app" ng-app='appProduto' ng-controller="myCtrlProduto">
	<form:form method="post" id="form" modelAttribute="produtoForm" action="${action}" enctype="multipart/form-data">
		<input id="actionOld" type="hidden" value="" />
	<h2>
		<a href="#" id="habilitarProduto" onclick="habilitarCadastroProduto()" />Produto Novo</a>
		<a href="#" id="habilitarVisualizacaoProdutos" onclick="habilitarVisualizacaoProduto()" class="none" />Visualiar Produtos</a>
		<h2>
			<c:if test="${btnHabilitadoCadastrar}">
				<a href="<c:url value="/cadastro-produto" />">Produto Novo</a>
			</c:if>
		</h2>
	</h2>
	<h3>Produtos</h3>
	<div id="blocoCadastroProduto" class="none">
		<div id="formulario">
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
		                <form:select path="categoria"  id="categoria" class="form-control"  onchange="categoriaOnChange2()">
		        			<option value=''>selecionar</option>
		        			<form:options  items="${categorias}" itemLabel="descricao" itemValue="id"  />
		   				</form:select>
			   			<label class="control-label" for="categoria"><form:errors path="categoria"/></label>
					</div>
				</spring:bind>
				<spring:bind path="subcategoria">	
					<div class="form-group ${status.error?'has-error':''} ">
						<label for="subcategoria">Subcategoria</label>
			                <form:select path="subcategoria" id="subcategoria"  class="form-control"  >
			               		<form:option  value="">Selecionar</form:option>
				       			<c:forEach items="${subcategorias}" var="subcategoria">
			       					<option value="${subcategoria.id}"  ${subcategoria.id==produtoForm.subcategoria.id?'selected':'' } >${subcategoria.descricao}</option>
				       			</c:forEach>
			   			 	</form:select>
			   			<label class="control-label" for="subcategoria"><form:errors path="subcategoria"/></label>
					</div>
				</spring:bind>
				<div class="panel panel-default" id="pecas">
					<div class="panel-heading">
						<h3 class="panel-title" ><strong>Quantidade de pe&ccedil;as</strong></h3>
					</div>
				 	<div class="panel-body">
						<div class="form-group">
							<label>Quantidade de pe&ccedil;as</label>
							<form:input id="quantidadeItemProduto" path="quantidade"  type="number" class="text-left camposFormulario" min="1" max="5" onchange="mudarQuantidadeItemProduto()" />
								<c:forEach var="itemProduto" items="${produtoForm.itensProduto}" varStatus="status">
									<div class="form-group">
										<label>Nome da pe&ccedil;a</label>
										<input type="text"  name="itensProduto[${status.index}].nome" value="${itemProduto.nome}"/>
									</div>
									<div class="form-group">
										<label>Medida</label>
										<form:select path="itensProduto[${status.index}].tipoMedida">
											<form:options   items="${medida}" />
										</form:select>
									</div>
								</c:forEach>
						</div>	
				  	</div>	
				</div>
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
						<img src="<c:url value="/image?id=${produtoForm.imagemProduto2.id}&tamanhoImagem=medio"/>"/>
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
	</div>	
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
	</form:form>
	</div>
	<script type="text/javascript">
	
		function mudarQuantidadeItemProduto(){
			var $form = $("#form");
			var $home = $('#home').attr('value');
			var url = $home + "produto/addItem";
			var actionAtual = $form.attr('action');	
			$("#actionOld").attr('value',actionAtual);
			$form.attr('action',url);
			$("#form").submit();
			
		}
	
		function habilitarCadastroProduto(){
			
			var blocoCadsatroProduto =  document.getElementById("blocoCadastroProduto");
			var habilitarVisualizacaoProdutos = document.getElementById("habilitarVisualizacaoProdutos");
			var habilitarProduto = document.getElementById("habilitarProduto");
			var listaProduto = document.getElementById("listaProdutos");
			
			blocoCadsatroProduto.classList.remove("none");
			habilitarVisualizacaoProdutos.classList.remove("none");
			habilitarProduto.classList.add("none");
			listaProdutos.classList.add("none");
		}
		
		function habilitarVisualizacaoProduto(){
			
			var blocoCadsatroProduto =  document.getElementById("blocoCadastroProduto");
			var habilitarVisualizacaoProdutos = document.getElementById("habilitarVisualizacaoProdutos");
			var habilitarProduto = document.getElementById("habilitarProduto");
			var listaProduto = document.getElementById("listaProdutos");
			
			blocoCadsatroProduto.classList.add("none");
			habilitarVisualizacaoProdutos.classList.add("none");
			habilitarProduto.classList.remove("none");
			listaProdutos.classList.remove("none");
			
		}
		
		function categoriaOnChange2(){
			var idCategoria = document.getElementById("categoria").value;
			var url = "pesquisa/categoria/onChange";
			var data = 'categoria=' + idCategoria;
			var $home = $('#home').attr('value');
			$.ajax({
			    url: $home + url,
			    type: 'GET',
			    data: data,
			    async: true,
			    cache: false,
			    contentType: false,
			  	processData: false,
			    success: function (data) {
			    	var categorias = data;
			    	var tamanho = data.length;
			    	var opcoesSelect = "";
			    	opcoesSelect += "<option value=''>selecionar</option>";
			    	for (var i = 0; i < tamanho; i++){
			    		var subcategoria = categorias[i];
			    		opcoesSelect += "<option value='" + subcategoria.id + "'>" + subcategoria.descricao + "</option>";
			    					    	
			    	}
			    	$("#subcategoria").html(opcoesSelect);
			    }	
		    });
			
		}
		
		$("#form").submit(function(event) {
			var $home = $('#home').attr('value');
			event.preventDefault();
			// setup some local variables
			var $form = $("#form");
			
			// let's select and cache all the fields
			// serialize the data in the form
			var formData = new FormData($(this)[0]);
			// fire off the request to /action
			$.ajax({
			    url: $form.attr('action'),
			    type: 'POST',
			    data: formData,
			    async: true,
			    cache: false,
			    contentType: false,
			  	processData: false,
			    success: function (returndata) {
			    	var action = $form.attr('action');
			    	if(!action.match(".*addItem")){
				    	$("#formulario").html($(returndata).find("#formulario"));
				    	$("#form").attr('action', $home + "/produto/add");
						$("#botaoFormulario").html('Cadastrar');
			    	}else{
			    		var actionOld = $("#actionOld").attr('value');
			    		$("#form").attr('action', actionOld);
			    		$("#pecas").html($(returndata).find("#pecas"));
			    	}
			    }
		    });
		});
		function produtoAlteracao(idProduto){
			
			habilitarCadastroProduto();
			
			window.scrollTo(0, 0);
			var $form = $("#form");
			var $home = $('#home').attr('value');
			// let's select and cache all the fields
			// serialize the data in the form
			var formData = new FormData($form);
			// fire off the request to /action
			
			$.ajax({
			    url: $home + "produto/carregar/alteracao",
			    type: 'GET',
			    data: {idProduto:idProduto},
			    async: true,
			    success: function (returndata) {
			    	$("#formulario").html($(returndata).find("#formulario"));
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
				
					
				};
				
				
				$scope.pesquisaAngular();
			});
			
			function pesquisar() {
				angular.element($("#myCtrlProduto")).scope().pesquisaAngular();
			};
	</script>
</html>