<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form:form method="post" id="form" modelAttribute="formEnderecoCliente" action="${action}">
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
</form:form>
