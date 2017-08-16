<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url var="totalPaginas" value="/carregar-comentarios/pages/${page.totalPages}" />
<c:url var="nextUrl" value="/carregar-comentarios/pages/${currentIndex + 1}" />
<input type="hidden" id="nextUrl" value="${nextUrl}" />
<input type="hidden" id="totalPaginas" value="${page.totalPages}" />
<div class="avaliacoes">
	<c:if test="${msg != null}">
		<div class="alert ${css }" role="alert">
			<strong>
				${msg }	
			</strong>
		</div>
	</c:if>
		<span class="avaliacoes">
			<c:if test="${produto.quantidadeAvaliacao == 0}">
				Sem avaliações
			</c:if>
			<c:if test="${produto.quantidadeAvaliacao > 0}">
				<strong>
				<span class="nota">
				<fmt:formatNumber var="mediaAvalicao"  value="${produto.mediaAvaliacao }" maxFractionDigits="1" />
				${mediaAvalicao }	
				</span>&nbsp;&nbsp;
				<span class="estrelas">
					<span class="glyphicon glyphicon-star"></span>
					<span class="glyphicon glyphicon-star${produto.mediaAvaliacao < 1.5 ? '-empty':'' }"></span>
					<span class="glyphicon glyphicon-star${produto.mediaAvaliacao < 2.5 ? '-empty':'' }"></span>
					<span class="glyphicon glyphicon-star${produto.mediaAvaliacao < 3.5 ? '-empty':'' }"></span>
					<span class="glyphicon  glyphicon-star${produto.mediaAvaliacao < 4.5 ? '-empty':'' }"></span>
				</span>
				<span class="quantidade-avaliacoes" aria-hidden="true">(${produto.quantidadeAvaliacao })</span>
				</strong>
			</c:if>
			<sec:authorize access="hasRole('CLIENTE')">
				<a onclick="carregarFormularioAvaliacao(${idProduto});" class="btn btn-default glyphicon">Avaliar</a>
			</sec:authorize>
		</span>
	<hr>
	<div id="avaliacoes-comentarios">
		<c:forEach var="avaliacao" items="${page.content }">
			<span class="titulo-comentario">${avaliacao.titulo}</span>&nbsp;&nbsp;
			<br/>
			<span class="estrelas">
				<span class="glyphicon glyphicon-star${avaliacao.nota<1?'-empty':'' }" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star${avaliacao.nota<2?'-empty':'' }" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star${avaliacao.nota<3?'-empty':'' }" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star${avaliacao.nota<4?'-empty':'' }" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-star${avaliacao.nota<5?'-empty':'' }" aria-hidden="true"></span>
			</span>
			<br/><br/>
			<div class="comentario">
				${avaliacao.comentario}
				<br/><br/>
				<strong>
					${avaliacao.apelido}
				</strong>
			</div>
			<hr>
		</c:forEach>
	</div>
	<div id="divBtnComentario">
		<c:if test="${page.totalPages >= currentIndex + 1}">
			<a onclick="carregarMaisComentarios(${idProduto})" class="btn btn-default glyphicon">+ Comentários</a>
		</c:if>
	</div>
</div>
<script>
	function carregarMaisComentarios(idProduto){
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var nextUrl = document.getElementById('nextUrl').value;
		var $home = $('#home').attr('value');
		var parametros = 'idProduto=' + idProduto;
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$.ajax({
		    url: nextUrl,
		    type: 'GET',
		    headers: headers,
		    data: parametros,
		    contentType: 'application/json',
		    async: true,
		    cache: false,
		    success: function (avaliacoes) {
		    	$("#avaliacoes-comentarios").append(avaliacoes);
		    	var proximaPagina = $('#nextUrl').attr('value');
		    	var paginaAtual = proximaPagina.replace($home + 'carregar-comentarios/pages/', '');
		    	paginaAtual = parseInt(paginaAtual);
		    	proximaPagina = paginaAtual + 1;
		    	$('#nextUrl').attr('value', proximaPagina);
		    	var totalPaginas = parseInt($('#totalPaginas').attr('value'));
		    	if(proximaPagina > totalPaginas){
		    		$("#divBtnComentario").html('');
		    	}
		    }
		  });
	}
	

</script>
<style>
.half {
    position:relative;
}
.font{
	font-size:6em;
}
.half:after {
    content:'';
    position:absolute;
    z-index:1;
    content: "\e007";
    background:white;
    width: 50%;
    height: 100%;
    left: 46%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.half-star:after {
	content:'';
    position:absolute;
    z-index:1;
    background:white;
    width: 50%;
    height: 100%;
    left: 46%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.half-star-empty:after {
 	position:absolute;
    z-index:1;
    content: "\e007";
    background:white;
    width: 50%;
    height: 100%;
    right: 46%;
}
</style>