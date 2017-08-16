<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<spring:url var="action" value="/atualizar/dados/cliente" />
<c:url var="home" value="/" scope="request" />	
<input id="home" type="hidden" value="${home}"/>
<form:form method="post" id="formDadosCliente" modelAttribute="formDadosCliente" action="${action}">
	<c:if test="${temErro != null}">
		<input id="temErro" type="hidden" value="${temErro}" />
	</c:if>
	<form:hidden path="idCliente"/>
	 <spring:bind path="nomeCompleto">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="nomeCompleto">Nome Completo</label>
	    <span id="spanNomeCompleto">
		    <form:input class="form-control"  path="nomeCompleto"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="nomeCompleto"><form:errors path="nomeCompleto"/></label>
	    </c:if>
	  </div>
	 </spring:bind>
	 <spring:bind path="rg">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="rg">RG </label>
	    <span id="spanRG">
	    	<form:input class="form-control"  path="rg"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="rg"><form:errors path="rg"/></label>
	    </c:if>
	  </div>
	 </spring:bind>
	 <spring:bind path="cpf">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cpf">CPF </label>
	    <span id="spanCPF">
	    	<form:input class="form-control"  path="cpf"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="cpf"><form:errors path="cpf"/></label>
	    </c:if>
	  </div>
	 </spring:bind>
	 <spring:bind path="dataNascimento">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="dataNascimento">Data de Nascimento</label>
	    <span id="spanDataNascimento">
	    	<form:input path="dataNascimento" class="form-control datepicker" data-date-format="dd/mm/yyyy" placeholder="dd/MM/aaaa"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="dataNascimento"><form:errors path="dataNascimento"/></label>
	    </c:if>
	  </div>	
	 </spring:bind>
	 <spring:bind path="telefone">
	 	 <div class="form-group ${status.error?'has-error':''} ">
	    <label for="telefone">Telefone</label>
	    <span id="spanTelefone">
	    	<input type="text" class="form-control" name="telefone" id="telefone" placeholder="Telefone" value="${formDadosCliente.telefone}"/>
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="telefone"><form:errors path="telefone"/></label>
	    </c:if>
	  </div>	
	 </spring:bind>
	 <spring:bind path="celular">
	 	 <div class="form-group ${status.error?'has-error':''} ">
	    <label for="celular">Celular</label>
	    <span id="spanCelular">
		    <input type="text" class="form-control" name="celular" id="celular" value="${formDadosCliente.celular}" placeholder="celular">
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="celular"><form:errors path="celular"/></label>
	    </c:if>
	  </div>	
	 </spring:bind>
	 <spring:bind path="email">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="email">E-mail</label>
	    <span id="spanEmail">
		    <input type="email" class="form-control" name="email" id="email" value="${formDadosCliente.email}" placeholder="E-mail">
	    </span>
	    <c:if test="${status.error}">
		    <label class="control-label" for="email"><form:errors path="email"/></label>
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
$("#formDadosCliente").submit(function(event) {
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#formDadosCliente");
	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData($form[0]);
	// fire off the request to /action
	var action = $form.attr('action');
	
	var nomeCompleto = document.getElementById('nomeCompleto').value;
	var rg = document.getElementById('rg').value;
	var cpf = document.getElementById('cpf').value;
	var dataNascimento = document.getElementById('dataNascimento').value;
	var telefone  = document.getElementById('telefone').value;
	var celular = document.getElementById('celular').value;
	var email = document.getElementById('email').value;
	
	$.ajax({
	    url: $form.attr('action'),
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
	    	$("#dadosCliente").html(returnData);
	    	if(temErro == null ||  temErro != 'true'){
				$("#spanNomeCompleto").html(nomeCompleto);
				$("#spanRG").html(rg);
				$("#spanCPF").html(cpf);
				$("#spanDataNascimento").html(dataNascimento);
				$("#spanTelefone").html(telefone);
				$("#spanCelular").html(celular);
				$("#spanEmail").html(email);
	    	}
	    	scroll(0,0);
	    }
    });
});

</script>
