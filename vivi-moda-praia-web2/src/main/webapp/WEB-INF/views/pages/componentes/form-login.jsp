<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="conteudo">
	<br/><br/><br/>
	<h3>Login</h3>
	<c:set var="paginaCorrente" value="${pageContext.request.servletPath}"/>
	<spring:url var="action" value="/efetuar-login?paginaDestino=${formLogin.paginaDestino}&paginaErro=${paginaCorrente}" />
	<c:url var="home" value="/" scope="request" />
	<input id="home" type="hidden" value="${home}"/>
	<form:form method="post" id="form" class="form-horizontal" modelAttribute="formLogin" action="${action}">
	  <form:hidden path="paginaDestino"/>
	  <form:hidden path="paginaAtual"/>		
	  <spring:bind path="email">
		  <div class="form-group ${status.error?'has-error':''}">
		    <label for="email" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <form:input path="email" class="form-control"  placeholder="E-mail"/>
		      <c:if test="${status.error}">
		    	<label class="control-label" for="email"><form:errors path="email"/></label>
	    	</c:if>	
		    </div>
		  </div>
	  </spring:bind>
	  <spring:bind path="senha">
		  <div class="form-group ${status.error?'has-error':''}">
		    <label for="senha" class="col-sm-2 control-label">Senha</label>
		    <div class="col-sm-10">
		       <form:password path="senha"  class="form-control"  placeholder="Senha" />	
			   <c:if test="${status.error}">
		    	 <label class="control-label" for="email"><form:errors path="senha"/></label>
		       </c:if>
		    </div>
		  </div>
	  </spring:bind>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <div class="checkbox">
	          <label><input type="checkbox" id="remember-me" name="remember-me"> Lembrar-me</label>  
	          <label>
	         	<a href="<c:url value="/cadastro-cliente" />">Ainda não sou cadastrado</a>
	          </label>
		      <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	      </div>
	    </div>
	  </div>
	  <div class="col-sm-offset-2 col-sm-10">
	  	 <c:if test="${param.error != null}">
	         <div class="alert alert-danger" role="alert">
				<strong>
					<spring:message code="email.ou.senha.invalido" />		
				</strong>
			</div>
	     </c:if>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Entrar</button>
	    </div>
	  </div>
	</form:form>
</div>
<script>
/*
	$("#form").submit(function(event){
		
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		
		var $home = $('#home').attr('value');
		event.preventDefault();
		// setup some local variables
		var $form = $("#form");
		
		// let's select and cache all the fields
		// serialize the data in the form
		var formData = new FormData($(this)[0]);
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
		    	paginaDestino = $(returndata).find('#paginaDestino')[0];
		    	if(paginaDestino != 'undefined' && paginaDestino.value != ''){
			    	window.location.href = paginaDestino.value;
		    	}else{
		    		$("#conteudo").html(returndata);	
		    	}
		    }
	    });
	});	
	*/
</script>