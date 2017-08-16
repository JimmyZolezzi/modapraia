<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<spring:url var="action" value="/avaliar/produto" />
<c:url var="home" value="/" scope="request" />	
<input id="home" type="hidden" value="${home}"/>
<form:form method="post" id="formAvaliacaoProduto" modelAttribute="formComentarioAvaliacao" action="${action}">
	<a  class="btn btn-default" onclick="carregarAvaliacoes(${idProduto})">Avaliações</a> 
	<h3>Avaliação de Produto</h3>
	<form:hidden path="avaliacao.produto.id"/>
	<form:hidden path="avaliacao.cliente.id"/>
	<div class="form-group ">
		<label>Apelido</label>
		<form:hidden path="avaliacao.apelido"/>
		${formComentarioAvaliacao.avaliacao.apelido}	
	</div>
	<div class="form-group  ">
		<label>Produto Avaliado</label>
		${formComentarioAvaliacao.avaliacao.produto.descricao}	
	</div>
	<spring:bind path="avaliacao.titulo">
		<div class="form-group ${status.error?'has-error':''}">
			<label>Título</label>
			<form:input path="avaliacao.titulo" class="form-control" />
			<c:if test="${status.error}">
			    <label class="control-label" for="titulo"><form:errors path="avaliacao.titulo"/></label>
		    </c:if>
		</div>
	</spring:bind>
	<spring:bind path="avaliacao.nota">
		<div class="form-group ${status.error?'has-error':''}">
			<label>Avaliação</label>
			<div class="form-group">
				<form:radiobutton path="avaliacao.nota" value="5" />Excelente
				<form:radiobutton path="avaliacao.nota" value="4" />Bom
				<form:radiobutton path="avaliacao.nota" value="3" />Regular
				<form:radiobutton path="avaliacao.nota" value="2" />Ruim
				<form:radiobutton path="avaliacao.nota" value="1" />Péssimo
			</div>
			<c:if test="${status.error}">
				<br/>
			    <label class="control-label" for="avaliacao.nota"><form:errors path="avaliacao.nota"/></label>
		    </c:if>
		</div>
	</spring:bind>
	<spring:bind path="avaliacao.comentario">
		<div class="form-group ${status.error?'has-error':''}">
			<label>Comentário</label>
			<form:textarea path="avaliacao.comentario" rows="5" class="form-control" />
			<c:if test="${status.error}">
			    <label class="control-label" for="avaliacao.comentario"><form:errors path="avaliacao.comentario"/></label>
		    </c:if>
		</div>
	</spring:bind>
	<button type="submit"  class="btn btn-default">Comentar</button>
</form:form>
<script>
$("#formAvaliacaoProduto").submit(
	function (event){
		
		event.preventDefault();
		
		
		var headers = {};
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		headers[csrfHeader] = csrfToken;
		
		var action = $('#formAvaliacaoProduto').attr('action');
		var formData = new FormData($("#formAvaliacaoProduto")[0]);
		$.ajax({
			url : action,
			type : 'POST',
			headers : headers,
			data : formData,
			async : true,
			cache : false,
			contentType : false,
			processData : false,
			success : function(returnData) {
				$("#divAvaliacao").html(returnData)
			}

		});
		
	}
	
);

</script>