<!DOCTYPE html>
<meta charset="UTF-8">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

	<div id="pecas">
		<jsp:include page="componentes/pecas-estoque.jsp"/>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" >Peça Selecionada</h3>
		</div>
	 	<div id="pecaSelecionado" class="panel-body">
	 	</div>
	</div>	 	
</div>
<script>
function selecionarItemProduto(idItemProduto,idProduto){
	var url = "carregar/form/entrada/estoque";
	var data = 'idItemProduto=' + idItemProduto + '&idProduto=' + idProduto;
	var $home = $('#home').attr('value');
	
	$.ajax({
	    url: $home + url,
	    type: 'GET',
	    data: data,
	    async: true,
	    success: function (returndata) {
	    	$("#pecaSelecionado").html(returndata);
	    }
  	});
	
}
</script>
