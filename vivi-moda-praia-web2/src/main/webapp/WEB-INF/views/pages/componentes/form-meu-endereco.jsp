<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<spring:url var="actionUrl" value="${action}" />
<form:form method="post" id="${idFormulario}" modelAttribute="formEnderecoCliente" action="${actionUrl}">
	<input type ="hidden" id="actionUrl" value="${action}" />
	<form:hidden path="idCliente"/>
	<c:if test="${temErro != null}">
		<input id="temErro" type="hidden" value="${temErro}" />
	</c:if>
  <spring:bind path="endereco.cep">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.cep">CEP</label>
	    <span id="spanCEP">
	    	<form:input class="form-control"  path="endereco.cep" onblur="buscarEnderecoPorCEP();"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.cep"><form:errors path="endereco.cep"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.endereco">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.endereco">Endereço</label>
	    <span id="spanEndereco">
	    	<form:input class="form-control"  path="endereco.endereco"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.endereco"><form:errors path="endereco.endereco"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.numero">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.numero">Numero</label>
	    <span id="spanNumero">
		    <form:input class="form-control"  path="endereco.numero"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.numero"><form:errors path="endereco.numero"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.cidade">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.cidade">Cidade</label>
	    <span id="spanCidade">
		    <form:input class="form-control"  path="endereco.cidade"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.cidade"><form:errors path="endereco.cidade"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.estado">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.estado">Estado</label>
		<span id="spanEstado">
		  <form:input class="form-control"  path="endereco.estado"/>
		</span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.cidade"><form:errors path="endereco.estado"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.bairro">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.estado">Bairro</label>
	    <span id="spanBairro">
		    <form:input class="form-control"  path="endereco.bairro"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.bairro"><form:errors path="endereco.bairro"/></label>
	    </c:if>
	  </div>
  </spring:bind>
	<c:if test="${msg == null}">
		<button type="submit"  class="btn btn-default">Atualizar</button>
	</c:if>
	<c:if test="${msg != null}">
		<div class="alert ${css }" role="alert">
			<strong>
				${msg }	
			</strong>
		</div>
	</c:if>
</form:form>
<script>
function buscarEnderecoPorCEP(){
	alert('entrou na busca');
	var cep = document.getElementById('endereco.cep').value;
	var idCliente = document.getElementById('idCliente').value;
	var parametros = 'cep=' + cep;
	var $home = $('#home').attr('value');
	$.ajax({
	    url: $home + '/buscar/endereco/por/cep',
	    type: 'GET',
	    data: parametros,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (endereco) {
	    	var campoEndereco = document.getElementById('endereco.endereco');
	    	var campoCidade = document.getElementById('endereco.cidade');
	    	var campoEstado = document.getElementById('endereco.estado');
	    	var campoBairro = document.getElementById('endereco.bairro');
	    	
	    	//Endereco
	    	if(endereco.endereco != null){
		    	campoEndereco.value = endereco.endereco;
	    	}
	    	//Cidade
	    	if(endereco.cidade != null){
	    		campoCidade.value = endereco.cidade;
	    	}
	    	//Estado
	    	if(endereco.estado != null){
	    		campoEstado.value = endereco.estado;
	    	}
	    	//Bairro
	    	if(endereco.bairro != null){
	    		campoBairro.value = endereco.bairro;
	    	}
	    	
	    }
	});
}

$("#formMeuEndereco").submit(function(event) {
	
	var actionUrl = document.getElementById('actionUrl').value;
	
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#formMeuEndereco");
	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData($form[0]);
	// fire off the request to /action
	var action = $form.attr('action');
	
	var cep = document.getElementById('endereco.cep').value;
	var endereco = document.getElementById('endereco.endereco').value;
	var numero = document.getElementById('endereco.numero').value;
	var cidade  = document.getElementById('endereco.cidade').value;
	var estado = document.getElementById('endereco.estado').value;
	var bairro = document.getElementById('endereco.bairro').value;
	
	$.ajax({
	    url: action,
	    type: 'POST',
	    headers : headers,
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	  	processData: false,
	    success: function (returnData) {
	    	var temErro = $(returnData).find("#temErro");
	    	if(temErro !=null && temErro.length > 0){
	    		temErro = temErro[0].value;
	    	}
	    	
	    	$("#meuEndereco").html(returnData);
	    	
	    	if(temErro == null ||  temErro != 'true'){
				$("#spanCEP").html(cep);
				$("#spanEndereco").html(endereco);
				$("#spanNumero").html(numero);
				$("#spanCidade").html(cidade);
				$("#spanEstado").html(estado);
				$("#spanBairro").html(bairro);
	    	}
	    	
	    //	scroll(0,0);
	    }
    });
});
$("#formEnderecoEntrega").submit(function(event) {
	
	var actionUrl = document.getElementById('actionUrl').value;
	
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#formEnderecoEntrega");
	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData($form[0]);
	// fire off the request to /action
	var action = $form.attr('action');
	
	var cep = document.getElementById('endereco.cep').value;
	var endereco = document.getElementById('endereco.endereco').value;
	var numero = document.getElementById('endereco.numero').value;
	var cidade  = document.getElementById('endereco.cidade').value;
	var estado = document.getElementById('endereco.estado').value;
	var bairro = document.getElementById('endereco.bairro').value;
	
	$.ajax({
	    url: action,
	    type: 'POST',
	    headers : headers,
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	  	processData: false,
	    success: function (returnData) {
	    	var temErro = $(returnData).find("#temErro");
	    	if(temErro !=null && temErro.length > 0){
	    		temErro = temErro[0].value;
	    	}
	    	
	    	$("#enderecoEntrega").html(returnData);
    		if(temErro == null ||  temErro != 'true'){
				$("#spanCEP").html(cep);
				$("#spanEndereco").html(endereco);
				$("#spanNumero").html(numero);
				$("#spanCidade").html(cidade);
				$("#spanEstado").html(estado);
				$("#spanBairro").html(bairro);
	    	}
	    	
	    //	scroll(0,0);
	    }
    });
});
</script>