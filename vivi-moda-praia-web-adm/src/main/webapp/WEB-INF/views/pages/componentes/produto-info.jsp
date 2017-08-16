<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<spring:url var="action" value="/info-produto/add/foto" />
<form:form method="post" name="form" id="form" modelAttribute="formImagemProduto" action="${action}" enctype="multipart/form-data">
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
<c:if test="${msg != null}">
	<div class="alert alert-success">
	 	${msg}
	</div>
</c:if>

<div class="media">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Informa&ccedil;&otilde;es</h3>
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
				<h4>Desconto 
					<strong>
						<fmt:formatNumber  value="${produto.descontoPercentual}"/>%
					</strong>
				</h4>
				<strong>
					<fmt:formatNumber value="${produto.descontoValor}" type="currency"/>
				</strong>
			</div>
	  	</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Pe&ccedil;as</h3>
		</div>
	 	<div class="panel-body">
 			<c:forEach var="itemProduto" items="${produto.itensProduto}" >
	 			<div class="form-group ">
	 				${itemProduto.nome} - ${itemProduto.tipoMedida.nome}
				</div>
 			</c:forEach>	
		</div>		
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Fotos</h3>
		</div>
	 	<div class="panel-body">
	 		<div class="form-group ">
	 			
		 			<div id="formulario">
		 				<form:hidden path="idProduto" />
			 			<label>Nova Foto</label>
			 			<form:input type="file" path="foto"/>
				 		<br/>
				 		<button id="addFoto" type="submit" class="btn btn-primary">Adicionar</button>
				 		<c:if test="${not empty css}">
							<br/><br/>
							<div class="alert ${css}">
							 	${msg}
							</div>
						</c:if>
		 			</div>
	 		</div>
	 		
			<div class="row">
				<c:if test="${produto.imagemProduto1 !=null}">
					<div class="col-lg-3 col-md-4 col-xs-6 thumb">
						<a href="#" class="thumbnail">
							<img class="media-object" src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=medio"/>" alt="Foto 1"  /> 
						</a>
					</div>
				</c:if>
				<c:if test="${produto.imagemProduto2 !=null}">
					<div class="col-lg-3 col-md-4 col-xs-6 thumb">
						<a  class="thumbnail">
							<img class="media-object" src="<c:url value="/image?id=${produto.imagemProduto2.id}&tamanhoImagem=medio"/>" alt="Foto 2"  /> 
						</a>
					</div>
				</c:if>
				<div id="imagensProduto">
					<c:forEach var="imagemProduto" items="${produto.imagensProduto}">
						<div class="col-lg-3 col-md-4 col-xs-6 thumb">
							<a class="thumbnail link">
								<div align="right">
									<button type="button" class="btn btn-danger" title="Remover" onclick="removerFoto(${produto.id}, ${imagemProduto.id})">
							    		<span>x</span>
							    	</button>
								</div>
								<img class="media-object" src="<c:url value="/image?id=${imagemProduto.id}&tamanhoImagem=medio"/>" /> 
							</a>
						</div>		
					</c:forEach>
				</div>
			</div>
	  	</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Estoque</h3>
		</div>
	 	<div class="panel-body">
	    	<div class="table-responsive">
			  <!-- Default panel contents -->
			  	<table  class="table table-hover table-striped">
			  		<thead>
			  			<tr>
			  				<th>Nome</th>
			  				<th>Tipo Medida</th>
			  				<th width="60%">QTD Estoque</th>
			  			</tr>
			  		</thead>
			  		<tbody>
			  			<c:forEach var="itemProduto" items="${produto.itensProduto}" >
			  				<tr>
			  					<td>${itemProduto.nome}</td>
			  					<td>${itemProduto.tipoMedida.nome}</td>
			  					<td>
			  						<c:forEach var="item" items="${itemProduto.mapItemProdutoEstoque}">
			  							<strong>Tamanho </strong>
			  							${item.value.tamanho} >>
			  							<strong>Quantidade: </strong>
			  							${item.value.quantidade} |
			  							<strong>Reservado: </strong>
			  							${item.value.quantidadeReservada} |
		  								<strong>Disponível: </strong> 
			  							${item.value.quantidade - item.value.quantidadeReservada}<br/>
			  						<br/>
			  						</c:forEach>
			  					</td>
			  				</tr>
			  			</c:forEach>
			  		</tbody>
			  	</table>
			</div>
	  	</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Avalia&ccedil;&otilde;es</h3>
		</div>
	 	<div class="panel-body">
	    	Tamanho P: 10;<br/>
	    	Tamanho M: 20;<br/>
	    	Tamanho G: 30;<br/>
	  	</div>
	</div>
</div>
</form:form>
<script type="text/javascript">

	$("#form").submit(function(event){
		
		var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
		var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
		var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	 
	  //disable the default form submission
	  event.preventDefault();
	 
	  //grab all form data  
	 	var formData = new FormData($(this)[0]);
	  	var $form = $("#form");
		var headers = {};
		headers[csrfHeader] = csrfToken;
	  $.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
	    headers:headers,
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