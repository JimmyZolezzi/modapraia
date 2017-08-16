<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<spring:url var="action" value="/realizar/entrada/produto/estoque" />
<form:form id="form"  method="post" action="${action}"  modelAttribute="formEntradaEstoque">
	
	<div id="formulario">
		<div class="form-group">
			<form:hidden path="idProduto"/>
			<form:hidden path="idItemEstoque"/>
			<label for="itemProdutoEstoque.tipoMedida">Item: </label>
			${formEntradaEstoque.nomeItem}
		</div>
		<div class="form-group">
			<input type="hidden" id="idItemEstoque" value="${itemProdutoEstoque.idItemEstoque }"> 
			<label for="itemProdutoEstoque.tipoMedida">Tipo Medida: </label>
			${formEntradaEstoque.itemProdutoEstoque.tipoMedida}
		</div>
		<spring:bind path="itemProdutoEstoque.tamanho">
			<div id="linkNovaMedida" class="" >
				<c:if test="${not empty itemProduto.mapItemProdutoEstoque  }">
					<a onclick="novaMedidaNumerica()" >Nova medida</a><br/>
				</c:if>
			</div>
			<div id="linkListaMedidas"  class="none">
				<a onclick="mostrarListaMedidas()">Lista de Medidas</a><br/>
			</div>
			
			<div class="form-group ${status.error?'has-error':''} ">
				
				<label for="itemProdutoEstoque.tamanho">Tamanho</label>
				
				<c:if test="${empty itemProduto.mapItemProdutoEstoque}">
					<form:input path="itemProdutoEstoque.tamanho" type="text" class="form-control" id="tamanho" placeholder="Tamanho" />
				</c:if>
				
				<input id="htmlTamanho" type="hidden" value="" /> 
				<div id="tamanhoCampo">

				</div>
				<c:if test="${not empty itemProduto.mapItemProdutoEstoque}">
					<div id="divTamanho">
						<form:select id="tamanho" class="form-control" path="itemProdutoEstoque.tamanho" onchange="mudarTamanhoSelecionado();">
							<form:option value="">Selecionar</form:option>
							<c:forEach var="item" items="${itemProduto.mapItemProdutoEstoque}">
								<form:option value="${item.value.tamanho}">${item.value.tamanho}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</c:if>
			</div>
		</spring:bind>
		<c:if test="${not empty itemProduto.mapItemProdutoEstoque}">
			<div class="form-group">
				<label id="labelQuantiadeEstoque" for="itemProdutoEstoque.tipoMedida">
					<c:if test="${formEntradaEstoque != null && formEntradaEstoque.itemProdutoEstoque != null && formEntradaEstoque.itemProdutoEstoque.quantidade !=0}">
						Quantidade Estoque: 
					</c:if>
				</label>
				<div id="quantidadeEstoquePecaSelecionada">
					<c:if test="${formEntradaEstoque != null && formEntradaEstoque.itemProdutoEstoque != null && formEntradaEstoque.itemProdutoEstoque.quantidade !=0}">
						${formEntradaEstoque.itemProdutoEstoque.quantidade}
					</c:if>
				</div>
			</div>
		</c:if>
		<div id="divEntrada" style="${empty itemProduto.mapItemProdutoEstoque || formEntradaEstoque.itemProdutoEstoque != null && formEntradaEstoque.itemProdutoEstoque.tamanho != null && formEntradaEstoque.itemProdutoEstoque.tamanho ne '' ?'':'display: none'}">
			<spring:bind path="itemProdutoEstoque.quantidade">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="itemProdutoEstoque.quantidade">QTD entrada</label>
					<form:input path="quantidadeEntrada" type="text" class="form-control" id="quantidadeEntrada" placeholder="QTD Entrada" />
					<label class="control-label" for="quantidadeEntrada">
					<form:errors path="quantidadeEntrada"/></label>	
				</div>
			</spring:bind>
			<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-floppy-disk"> Entrada Estoque</span>
			</button>
		</div>			
		<br/><br/>
		<div class="alert ${css}">
		  ${msg}
		</div>
	</div>		 		
</form:form>
<script>


function selecionarItemProdutoEstoqueNumero(idItemProduto,idProduto){
	
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var home = document.getElementById('home').getAttribute('value');
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	var url = "selecionar/item/produto/estoque";
	var tamanhoLetra = document.getElementById("tamanhoLetra").value;
	var tamanho = tamanhoLetra;
	var data = "idItemProduto=" +  idItemProduto + "&idProduto=" + idProduto + "&tamanho=" + tamanho;
	var $home = $('#home').attr('value');
	$.ajax({
	    url: $home + url,
	    type: 'GET',
	    headers: headers,
	    data: data,
	    async: true,
	    success: function (returndata) {
	    	$("#pecaSelecionado").html(returndata);
	    }
  	});
	
}
$("#form").submit(function(event) {
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#form");
	var formData = new FormData($(this)[0]);
	var idProduto = document.getElementById('idProduto').value;
	// let's select and cache all the fields
	// serialize the data in the form
	// fire off the request to /action
	
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var home = document.getElementById('home').getAttribute('value');
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
	    headers: headers,
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	  	processData: false,
	    success: function (returndata) {
	    	$("#pecaSelecionado").html(returndata);	
	    	atualizarPecasSelecionadas(idProduto);
	    }
    });
});

function mudarTamanhoSelecionado(){
	var tamanho = document.getElementById('tamanho').value;
	//verifica tamanho numero nao existe
	var idItemEstoque = document.getElementById('idItemEstoque').value;
	var url = "/atualizar/quantidadeEstoque/produtoSelecionado";
	var data = "tamanho=" + tamanho +  '&idItemProduto=' + idItemEstoque;
	var $home = $('#home').attr('value');
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
	    url: $home + url,
	    type: 'GET',
	    headers: headers,
	    data: data,
	    async: true,
	    success: function (returndata) {
	    	if(returndata != null && returndata != '0'){
	    		$("#labelQuantiadeEstoque").html('Quantidade Estoque: ');
	    		$("#quantidadeEstoquePecaSelecionada").html(returndata);
	    		$("#divEntrada").attr('style','');
	    		
	    	}else{
	    		$("#labelQuantiadeEstoque").html('');
	    		$("#quantidadeEstoquePecaSelecionada").html('');
	    		$("#divEntrada").attr('style','display:none');
	    		
	    	}
	    }
  	});
	
}


function atualizarPecasSelecionadas(idProduto){
	
	var url = "/atualizar/pecas/estoque";
	var data = "idProduto=" + idProduto;
	var $home = $('#home').attr('value');
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var home = document.getElementById('home').getAttribute('value');
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
	    url: $home + url,
	    type: 'GET',
	    headers: headers,
	    data: data,
	    async: true,
	    success: function (returndata) {
	    	$("#pecas").html(returndata);
	    }
  	});
}
function novaMedidaNumerica(){
	
	var htmlTamanhoCampo = '<input name="itemProdutoEstoque.tamanho" type="text" class="form-control" id="tamanho" placeholder="Tamanho" />';
	var tamanhoCampo = document.getElementById("tamanhoCampo");
	var divTamanho  = document.getElementById("divTamanho");
	var htmlTamanhoHidden = document.getElementById("htmlTamanho");
	
	$(tamanhoCampo).html(htmlTamanhoCampo);
	htmlTamanhoHidden.value = divTamanho.innerHTML;
	$(divTamanho).html('');
	
	var linkNovaMedida = document.getElementById("linkNovaMedida");
	var linkListaMedidas = document.getElementById("linkListaMedidas");
	linkNovaMedida.classList.add("none");
	linkListaMedidas.classList.remove("none");
	$("#divEntrada").attr('style','');
	$("#labelQuantiadeEstoque").html('');
	$("#quantidadeEstoquePecaSelecionada").html('');
}
function mostrarListaMedidas(){
	
	var tamanhoCampo = document.getElementById("tamanhoCampo");
	var divTamanho = document.getElementById("divTamanho");
	var htmlTamanhoHidden = document.getElementById("htmlTamanho");
	$(divTamanho).html(htmlTamanhoHidden.value);
	$(tamanhoCampo).html('');
	
	var linkNovaMedida = document.getElementById("linkNovaMedida");
	var linkListaMedidas = document.getElementById("linkListaMedidas");
	
	linkNovaMedida.classList.remove("none");
	linkListaMedidas.classList.add("none");
	$("#divEntrada").attr('style','display:none');
	$("#labelQuantiadeEstoque").html('');
	$("#quantidadeEstoquePecaSelecionada").html('');
	
}


</script>
