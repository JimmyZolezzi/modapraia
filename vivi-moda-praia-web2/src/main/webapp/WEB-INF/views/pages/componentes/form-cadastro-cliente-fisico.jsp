<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="cadastro">

<h3>Cadastro Cliente - Físico</h3>
<spring:url var="action" value="/cadastrar/cliente" />
<c:url var="home" value="/" scope="request" />	
<input id="home" type="hidden" value="${home}"/>
<form:form method="post" id="formCadastroCliente" modelAttribute="formCliente" action="${action}" onsubmit="onSubmitFormCadastroCliente();">

  <spring:bind path="cliente.nome">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.nome">Nome Completo</label>
	    <form:input class="form-control"  path="cliente.nome"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="cliente.nome"><form:errors path="cliente.nome"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="cliente.rg">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.rg">RG </label>
	    <form:input class="form-control"  path="cliente.rg"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="cliente.rg"><form:errors path="cliente.rg"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="cliente.cpfCnpj">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.cpfCnpj">CPF </label>
	    <form:input class="form-control"  path="cliente.cpfCnpj"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="cliente.cpfCnpj"><form:errors path="cliente.cpfCnpj"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="cliente.dataNascimento">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.dataNascimento">Data de Nascimento</label>
	    <form:input path="cliente.dataNascimento" class="form-control datepicker" data-date-format="dd/mm/yyyy"  placeholder="dd/MM/aaaa"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="dataNascimento"><form:errors path="cliente.dataNascimento"/></label>
	    </c:if>
	  </div>	
  </spring:bind>
  <spring:bind path="cliente.telefone">
  	 <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.telefone">Telefone</label>
	    <input type="text" class="form-control" name="cliente.telefone" id="cliente.telefone" placeholder="Telefone" value="${formCliente.cliente.telefone}"/>
	    <c:if test="${status.error}">
		    <label class="control-label" for="cliente.telefone"><form:errors path="cliente.telefone"/></label>
	    </c:if>
	  </div>	
  </spring:bind>
  <spring:bind path="cliente.celular">
  	 <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.celular">Celular</label>
	    <input type="text" class="form-control" name="cliente.celular" id="cliente.celular" value="${formCliente.cliente.celular}" placeholder="celular">
	    <c:if test="${status.error}">
		    <label class="control-label" for="cliente.celular"><form:errors path="cliente.celular"/></label>
	    </c:if>
	  </div>	
  </spring:bind>
  <spring:bind path="cliente.email">
	  <div class="form-group ${status.error?'has-error':''} ">
	    <label for="cliente.email">E-mail</label>
	    <input type="email" class="form-control" name="cliente.email" id="cliente.email" value="${formCliente.cliente.email}" placeholder="E-mail">
	    <c:if test="${status.error}">
		    <label class="control-label" for="cliente.email"><form:errors path="cliente.email"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="cliente.senha">
	  <div class="form-group ${status.error?'has-error':''}">
	    <label for="cliente.senha">Senha</label>
	    <input type="password" class="form-control" id="cliente.senha" name="cliente.senha" placeholder="Senha">
	    <c:if test="${status.error}">
	    	<label class="control-label" for="cliente.senha"><form:errors path="cliente.senha"/></label>
	    </c:if>
	  </div>
  </spring:bind>
  <spring:bind path="confirmacaoSenha">
	  <div class="form-group ${status.error?'has-error':''}">
	    <label for="confirmacaoSenha">Confirmação de Senha</label>
	    <input type="password" class="form-control" id="confirmacaoSenha" name="confirmacaoSenha" placeholder="Confirmação de Senha">
	    <c:if test="${status.error}">
	    	<label class="control-label" for="confirmacaoSenha"><form:errors path="confirmacaoSenha"/></label>
	    </c:if>
	  </div>
   </spring:bind>
   <c:if test="${formCliente.resultado eq 'erro_cadastro' }">
		<div class="alert alert-danger" role="alert">
			<strong>
				<spring:message code="mensagem.erro.cadastro.cliente" />		
			</strong>
		</div>
   </c:if>
   <c:if test="${formCliente.resultado eq 'erro_ja_cadastrado' }">
		<div class="alert alert-danger" role="alert">
			<strong>
				<spring:message code="mensagem.cliente.ja.cadastrado" />		
			</strong>
		</div>
   </c:if>
   <button type="submit" class="btn btn-default">Próximo</button>
</form:form>
<script src="<c:url value="/js/site/cadastroCliente.js" />"></script>
</div>

