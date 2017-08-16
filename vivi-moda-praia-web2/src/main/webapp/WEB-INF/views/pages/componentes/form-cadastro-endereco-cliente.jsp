<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<div id="cadastro">
<input type="hidden" id="pagina" value="enderecoCliente" /> 
<spring:url var="action" value="/finaliza/cadastramento/cliente" />
<c:url var="home" value="/" scope="request" />	
<input id="home" type="hidden" value="${home}"/>
	<h3>Cadastro de Endereço</h3>
<form:form method="post" id="formCadastroEndereco" modelAttribute="formEnderecoCliente" action="${action}">

<form:hidden path="idCliente"/>
  <spring:bind path="endereco.cep">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.cep">CEP</label>
	    <form:input class="form-control"  path="endereco.cep" onchange="buscarEnderecoPorCEP();"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.cep"><form:errors path="endereco.cep"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.endereco">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.endereco">Endereço</label>
	    <form:input class="form-control"  path="endereco.endereco"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.endereco"><form:errors path="endereco.endereco"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.numero">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.numero">Numero</label>
	    <form:input class="form-control"  path="endereco.numero"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.numero"><form:errors path="endereco.numero"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.cidade">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.cidade">Cidade</label>
	    <form:input class="form-control"  path="endereco.cidade"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.cidade"><form:errors path="endereco.cidade"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.estado">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.estado">Estado</label>
	    <form:input class="form-control"  path="endereco.estado"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.cidade"><form:errors path="endereco.estado"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="endereco.bairro">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="endereco.estado">Bairro</label>
	    <form:input class="form-control"  path="endereco.bairro"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="endereco.bairro"><form:errors path="endereco.bairro"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <c:if test="${formEnderecoCliente eq 'erro-atualizar-endereco' }">
  	<div class="alert alert-danger" role="alert">
		<strong>
			<spring:message code="erro.atualizar.endereco" />		
		</strong>
	</div>
  </c:if>
  <a class="btn btn-default" onclick="voltarCadastroCliente();">Voltar<i class="fa fa-angle-right"></i></a>
  <button type="submit" class="btn btn-primary">Finalizar</button>
</form:form>
<script type="text/javascript">
$("#formCadastroEndereco").submit(function(event){
	
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#formCadastroEndereco");
	
	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData($(this)[0]);
	
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var headers = {};
	headers[csrfHeader] = csrfToken;
	// fire off the request to /action
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
	    	pagina = $(returndata).filter('#pagina')[0];
	    	
	    	if(pagina != 'undefined' ||  pagina.value == 'paginaDadosClienteCadastrado'){
		    	$("#conteudo").html(returndata);	
	    		
	    	}else{
	    		$("#cadastro").html(returndata);	
	    		$(window).scrollTop(0);
	    		
	    	}
    	}
	});
});
function buscarEnderecoPorCEP(){
	
	var cep = document.getElementById('endereco.cep').value;
	var idCliente = document.getElementById('idCliente').value;
	var parametros = 'cep=' + cep + '&idCliente=' + idCliente;
	var $home = $('#home').attr('value');
	$.ajax({
	    url: $home + '/busca/endereco/cep',
	    type: 'GET',
	    data: parametros,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (returndata) {
	    	$("#cadastro").html(returndata);
	    }
	  });
	
}
</script>
</div>
