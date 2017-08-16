<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<h3>Dados Pessoais</h3>
<c:url var="home" value="/" scope="request" />	
<input id="home" type="hidden" value="${home}"/>
<span id="divDadosCliente"></span>
<div class="panel panel-default" >
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Dados do Cliente</strong>
			<div style="float:right;">
				<a href="#divDadosCliente" onclick="carregarDadosCliente(${cliente.id})"><i class="glyphicon glyphicon-pencil"></i></a>
			</div>
		</h3>
	</div>
	<div class="panel-body">
		<div id="dadosCliente">
			<input type="hidden" id="idCliente" value="${cliente.id}" />
			<div class="form-group">
			  <label>Nome Completo </label>
			  ${cliente.nome}
			</div>
			<div class="form-group">
			  <label>RG </label>
				${cliente.rg}
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
</div>


<div class="panel panel-default" id="divMeuEndereco">
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Meu Endereço</strong>
			<div style="float:right;">
				<a href="#divMeuEndereco" onclick="carregarMeuEndereco(${cliente.id})"><i class="glyphicon glyphicon-pencil"></i></a>
			</div>
		</h3>
	</div>
	<div class="panel-body">
		<div id="meuEndereco">
			<div class="form-group">
			  <label>CEP </label>
			  ${cliente.enderecoCliente.cep}
			</div>
			<div class="form-group">
			  <label>Logradouro </label>
			  ${cliente.enderecoCliente.endereco}
			</div>
			<div class="form-group">
			  <label>Numero </label>
			  ${cliente.enderecoCliente.numero}
			</div>
			<div class="form-group">
			  <label>Estado </label>
			  ${cliente.enderecoCliente.estado}
			</div>
			<div class="form-group">
			  <label>Cidade </label>
			  ${cliente.enderecoCliente.cidade}
			</div>
			<div class="form-group">
			  <label>Bairro </label>
			  ${cliente.enderecoCliente.bairro}
			</div>
		</div>
 	</div>
</div>
<div class="panel panel-default" id="divEnderecoEntrega">
	<div class="panel-heading">
		<h3 class="panel-title" ><strong>Endereço de Entrega</strong>
			<div style="float:right;">
				<a href="#divEnderecoEntrega" onclick="carregarEnderecoEntrega(${cliente.id})"><i class="glyphicon glyphicon-pencil"></i></a>
			</div>
		</h3>
	</div>
	<div class="panel-body">
		<div id="enderecoEntrega">
		  <div class="form-group">
		    <label>CEP </label>
		    ${cliente.enderecoEntrega.cep}
		  </div>
		  <div class="form-group">
		    <label>Logradouro </label>
		    ${cliente.enderecoEntrega.endereco}
		  </div>
		  <div class="form-group">
		    <label>Numero </label>
		    ${cliente.enderecoEntrega.numero}
		  </div>
		  <div class="form-group">
		    <label>Estado </label>
		    ${cliente.enderecoEntrega.estado}
		  </div>
		  <div class="form-group">
		    <label>Cidade </label>
		    ${cliente.enderecoEntrega.cidade}
		  </div>
		  <div class="form-group">
		    <label>Bairro </label>
		    ${cliente.enderecoEntrega.bairro}
		  </div>
		</div>
 	</div>
</div>
<script>
function carregarDadosCliente(idCliente){
	var home = $('#home').attr('value');
	var headers = {};
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	headers[csrfHeader] = csrfToken;
	var parameters = 'idCliente=' +  idCliente;
		$.ajax({
			url : home + 'carregar/dados/cliente',
			type : 'GET',
			headers : headers,
			data : parameters,
			async : true,
			success : function(returnData) {
				$("#dadosCliente").html(returnData);
			}
	
		});
		
	
}

function carregarMeuEndereco(idCliente){

	var home = $('#home').attr('value');
	var headers = {};
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	headers[csrfHeader] = csrfToken;
	var parameters = 'idCliente=' +  idCliente;
	$.ajax({
		url : home + 'carregar/meu/endereco',
		type : 'GET',
		headers : headers,
		data : parameters,
		async : true,
		success : function(returnData) {
			$("#meuEndereco").html(returnData);
		}

	});
}
function carregarEnderecoEntrega(idCliente){

	var home = $('#home').attr('value');
	var headers = {};
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	headers[csrfHeader] = csrfToken;
	var parameters = 'idCliente=' +  idCliente;
	$.ajax({
		url : home + 'carregar/endereco/entrega',
		type : 'GET',
		headers : headers,
		data : parameters,
		async : true,
		success : function(returnData) {
			$("#enderecoEntrega").html(returnData);
		}

	});
}
/*

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
	var formData = new FormData($form);
	// fire off the request to /action
	var action = $form.attr('action');

	$.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
	    headers : headers,
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	  	processData: false,
	    success: function (returndata) {
	    	$("#dadosCliente").html(returnData);
			var nomeCompleto = document.getElementById('nomeCompleto').value;
			var rg = document.getElementById('rg').value;
			var cpf = document.getElementById('cpf').value;
			var dataNascimento = document.getElementById('dataNascimento').value;
			var telefone  = document.getElementById('telefone').value;
			var celular = document.getElementById('celular').value;
			var email = document.getElementById('email').value;
			
			("#divNomeCompleto").html(nomeCompleto);
			("#divRg").html(rg);
			("#divCpf").html(cpf);
			("#divDataNascimento").html(dataNascimento);
			("#divTelefone").html(telefone);
			("#divCelular").html(celular);
			("#divEmail").html(email);
	    	
	    	scroll(0,0);
	    }
    });
});



*/
</script>
</html>