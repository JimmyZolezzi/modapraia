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
			<label for="itemProdutoEstoque.tipoMedida">Tipo Medida: </label>
			${formEntradaEstoque.itemProdutoEstoque.tipoMedida}
		</div>
		<spring:bind path="itemProdutoEstoque.tamanho">
			<div id="linkNovaMedida" class="" >
				<a onclick="novaMedidaNumerica()" >Nova medida</a><br/>
			</div>
			<div id="linkListaMedidas"  class="none">
				<a onclick="mostrarListaMedidas()">Lista de Medidas</a><br/>
			</div>
			<div class="form-group ${status.error?'has-error':''} ">
				
				<label for="itemProdutoEstoque.tamanho">Tamanho</label>
				
				<c:if test="${empty itemProduto.itensEstoque && formEntradaEstoque.itemProdutoEstoque.tipoMedida ne 'LETRA'}">
					<form:input path="itemProdutoEstoque.tamanho" type="text" class="form-control" id="tamanho" placeholder="Tamanho" />
				</c:if>
				<input id="htmlTamanhoNumerico" type="hidden" value="" /> 
				<div id="tamanhoNumericoCampo">

				</div>
				<div id="tamanhoNumerico">
					<c:if test="${not empty itemProduto.itensEstoque && formEntradaEstoque.itemProdutoEstoque.tipoMedida ne 'LETRA'}">
						<form:select id="tamanhoNumero" path="itemProdutoEstoque.tamanho">
							<form:options itemLabel="tamanho" itemValue="tamanho" items="${itemProduto.itensEstoque}"/>
						</form:select>
					</c:if>
				</div>
				
				<c:if test="${formEntradaEstoque.itemProdutoEstoque.tipoMedida eq 'LETRA'}">
					<form:select id="tamanhoLetra" path="itemProdutoEstoque.tamanho" onchange="selecionarItemProdutoEstoqueNumero(${itemProduto.id}, ${idProduto})">
						<form:option value="">selecionar</form:option>
						<form:options items="${tamanhosLetra}"/>
					</form:select>
				</c:if>
				
				<label class="control-label" for="itemProdutoEstoque.tamanho">
				<form:errors path="itemProdutoEstoque.tamanho"/></label>	
			</div>
		</spring:bind>
		<div class="form-group">
			<label for="itemProdutoEstoque.tipoMedida">Quantidade Estoque: </label>
			${formEntradaEstoque.itemProdutoEstoque.quantidade}
		</div>			
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
		<br/><br/>
		<div class="alert ${css}">
		  ${msg}
		</div>
	</div>		 		
</form:form>
<script>


function selecionarItemProdutoEstoqueNumero(idItemProduto,idProduto){
	var url = "selecionar/item/produto/estoque";
	var tamanhoLetra = document.getElementById("tamanhoLetra").value;
	var tamanho = tamanhoLetra;
	var data = "idItemProduto=" +  idItemProduto + "&idProduto=" + idProduto + "&tamanho=" + tamanho;
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
	$.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
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

function atualizarPecasSelecionadas(idProduto){
	
	var url = "/atualizar/pecas/estoque";
	var data = "idProduto=" + idProduto;
	var $home = $('#home').attr('value');
	$.ajax({
	    url: $home + url,
	    type: 'GET',
	    data: data,
	    async: true,
	    success: function (returndata) {
	    	$("#pecas").html(returndata);
	    }
  	});
}
function novaMedidaNumerica(){
	
	var htmlTamanhoNumericoCampo = '<input name="itemProdutoEstoque.tamanho" type="text" class="form-control" id="tamanho" placeholder="Tamanho" />';
	var tamanhoNumericoCampo = document.getElementById("tamanhoNumericoCampo");
	var tamanhoNumerico = document.getElementById("tamanhoNumerico");
	var htmlTamanhoNumericoHidden = document.getElementById("htmlTamanhoNumerico");
	
	$(tamanhoNumericoCampo).html(htmlTamanhoNumericoCampo);
	htmlTamanhoNumericoHidden.value = tamanhoNumerico.innerHTML;
	$(tamanhoNumerico).html('');
	
	var linkNovaMedida = document.getElementById("linkNovaMedida");
	var linkListaMedidas = document.getElementById("linkListaMedidas");
	linkNovaMedida.classList.add("none");
	linkListaMedidas.classList.remove("none");
}
function mostrarListaMedidas(){
	
	var tamanhoNumericoCampo = document.getElementById("tamanhoNumericoCampo");
	var tamanhoNumerico = document.getElementById("tamanhoNumerico");
	var htmlTamanhoNumericoHidden = document.getElementById("htmlTamanhoNumerico");
	$(tamanhoNumerico).html(htmlTamanhoNumericoHidden.value);
	$(tamanhoNumericoCampo).html('');
	
	var linkNovaMedida = document.getElementById("linkNovaMedida");
	var linkListaMedidas = document.getElementById("linkListaMedidas");
	
	linkNovaMedida.classList.remove("none");
	linkListaMedidas.classList.add("none");
	
}


</script>
