<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<input type="hidden" id="pagina" value="paginaDadosClienteCadastrado" /> 
<div class="alert alert-success" role="alert">
	<strong>
		<spring:message code="cliente.cadastrado.sucesso" />
	</strong>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Dados do Cliente</strong></h3>
	</div>
	<div class="panel-body">
	  <div class="form-group">
	    <label>Nome Completo </label>
	    ${cliente.nome}
	  </div>
	  <div class="form-group">
	    <label>RG </label>
	  	${cliente.nome}
	  </div>
	  <div class="form-group ">
	    <label>CPF </label>
	    ${cliente.cpfCnpj}
	  </div>
	  <div class="form-group">
	    <label>Data de Nascimento</label>
	    <fmt:formatDate value="${cliente.dataNascimento}" pattern="dd/MM/yyyy" />
	  </div>	
  	 <div class="form-group">
	    <label>Telefone</label>
	     ${cliente.telefone}
	  </div>	
  	 <div class="form-group">
	    <label>Celular</label>
	    ${cliente.celular}
	  </div>	
	  <div class="form-group">
	    <label>E-mail</label>
	    ${cliente.email}
	  </div>

 	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Endereço</strong></h3>
		
	</div>
	<div class="panel-body">
	  <div class="form-group">
	    <label>CEP</label>
	    ${cliente.enderecoCliente.cep}
	  </div>
	  <div class="form-group">
	    <label>Logradouro</label>
	    ${cliente.enderecoCliente.endereco}
	  </div>
	  <div class="form-group">
	    <label>Numero</label>
	    ${cliente.enderecoCliente.numero}
	  </div>
	  <div class="form-group">
	    <label>Cidade</label>
	    ${cliente.enderecoCliente.cidade}
	  </div>
	  <div class="form-group">
	    <label>Estado</label>
	    ${cliente.enderecoCliente.estado}
	  </div>
	  <div class="form-group">
	    <label>Bairro</label>
	    ${cliente.enderecoCliente.bairro}
	  </div>
 	</div>
</div>
</html>