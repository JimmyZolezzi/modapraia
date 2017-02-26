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
<br/>
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
				<h2>
					<fmt:formatNumber value="${produto.valor}" type="currency"/>
				</h2>
				<br/> 	
					${produto.informacoes}
				<br/>
		    </div>
		 	<div class="panel-body">
				<div class="row">
					<div class="col-md-6" >
						<div>
				  		 	<img class="thumbnail media-object" id="fotoSelecionada" data-toggle="magnify"  width="100%" height="100%" src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=normal" />"  />
						
						</div>
						<div class="row ">
							<div class="container">
								<div class="scroller scroller-left"><i class="glyphicon glyphicon-chevron-left"></i></div>
				 				<div class="scroller scroller-right"><i class="glyphicon glyphicon-chevron-right"></i></div>
								<div class="wrapper">
								<ul class="nav nav-tabs list" id="myTab">
								<c:forEach var="imagemProduto" items="${produto.imagensProduto}">
									<li>
										<img width="100%" height="100%" src="<c:url value="/image?id=${imagemProduto.id}&tamanhoImagem=pequeno"/>" onclick="mudarFoto(${imagemProduto.id})"  />
									</li>
								</c:forEach>
								</ul>
								</div>	
							</div>
						</div>
				  	</div>
				  	<div class="col-md-6">
					  	<div class="form-group">
							<label>Quantidade</label>
							<input id="qtd_${produto.id}" type="number" class="form-control text-center" value="1">
						</div>
						<div class="form-group">
							<label>Tamanho</label>
							<input id="qtd_${produto.id}" type="text" class="form-control text-center" value="P">
						</div>
	               	 	<button type="button" class="btn btn-primary glyphicon glyphicon-shopping-cart" ng-click="adicionarProdutoCarrinho(${produto.id},1)">Adicionar</button>
					</div>
				</div>
				
		 </div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Avalia&ccedil;&otilde;es</strong></h3>
		</div>
	 	<div class="panel-body">

	  	</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Tags</strong></h3>
		</div>
	 	<div class="panel-body">

	  	</div>
	</div>
<script type="text/javascript">
/*
	$("#form").submit(function(event){
	 
	  //disable the default form submission
	  event.preventDefault();
	 
	  //grab all form data  
	  var formData = new FormData($(this)[0]);
	  var $form = $("#form");
	  $.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (returndata) {
	    	$("#imagensProduto").html($(returndata).find("#imagensProduto"));
	    	$("#formulario").html($(returndata).find("#formulario"));
	    }
	  });
	 
	});
	*/
	function mudarFoto(idFotoSelecionada){
		var $home = $('#home').attr('value');
		var imagemSelecionada = document.getElementById('fotoSelecionada');
		var url = 'image?id=' + idFotoSelecionada + '&tamanhoImagem=normal';
		$(imagemSelecionada).attr('src',$home + url);
		
		magnificar();
	}
	
	function removerFoto(idProduto, idImagem){
		
		var parametros = 'idProduto=' + idProduto + '&idImagemProduto=' + idImagem;
		var $home = $('#home').attr('value');
		$.ajax({
		    url: $home + 'info-produto/remover/foto',
		    type: 'GET',
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#imagensProduto").html($(returndata).find("#imagensProduto"));
		    	$("#formulario").html($(returndata).find("#formulario"));
		    }
		  });
		
	}
	
	/*
	
	$("#form").submit(function(event) {
		
		event.preventDefault();
		// setup some local variables
		var $form = $("#form");
		var formData = new FormData($form);
		
		//var serializedData = formData.serialize();
		// let's select and cache all the fields
		// serialize the data in the form
		// fire off the request to /action
		request = $.ajax({
			url : $form.attr('action'),
			type : "post",
			data : formData,
			async: false,
			cache: false,
			processData: false,
			success : function(data) {

				$("#formulario").html($(data).find("#formulario"));
				console.log("SUCCESS: ", data);
			},
			error : function(e) {
				console.log(e);
			},
		});
		
		return false;		
	});
	*/
</script>