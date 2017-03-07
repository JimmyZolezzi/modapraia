<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form  method="post" action=""  modelAttribute="formEntradaEstoque">
	<div id="formulario">
		<div class="form-group">
			<label for="itemProdutoEstoque.tipoMedida">Item: </label>
			${formEntradaEstoque.nomeItem}
		</div>
		<div class="form-group">
			<label for="itemProdutoEstoque.tipoMedida">Tipo Medida: </label>
			${formEntradaEstoque.itemProdutoEstoque.tipoMedida}
		</div>
		<spring:bind path="itemProdutoEstoque.tamanho">
			<div class="form-group ${status.error?'has-error':''} ">
				<label for="itemProdutoEstoque.tamanho">Tamanho</label>
				<c:if test="${not empty itemProduto.itensEstoque}">
					<form:select id="tamanhoNumero" path="itemProdutoEstoque.tamanho">
						<form:options itemLabel="tamanho" itemValue="id" items="${itemProduto.itensEstoque}"/>
					</form:select>
				</c:if>
				<c:if test="${formEntradaEstoque.itemProdutoEstoque.tipoMedida eq 'LETRA'}">
					<form:select id="tamanhoLetra" path="itemProdutoEstoque.tamanho" onchange="selecionarItemProdutoEstoqueNumero(${itemProduto.id})">
						<form:option value="">selecionar</form:option>
						<form:options items="${tamanhosLetra}"/>
					</form:select>
				</c:if>
				<c:if test="${empty itemProduto.itensEstoque && formEntradaEstoque.itemProdutoEstoque.tipoMedida ne 'LETRA'}">
					<form:input path="itemProdutoEstoque.tamanho" type="text" class="form-control" id="tamanho" placeholder="Tamanho" />
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


function selecionarItemProdutoEstoqueNumero(idItemProduto){
	var url = "selecionar/item/produto/estoque";
	var tamanhoLetra = document.getElementById("tamanhoLetra").value;
	var tamanho = tamanhoLetra;
	var data = "idItemProduto=" +  idItemProduto +"&tamanho=" + tamanho;
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
