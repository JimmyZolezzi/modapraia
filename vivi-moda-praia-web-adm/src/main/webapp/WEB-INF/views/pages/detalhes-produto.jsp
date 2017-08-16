<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ol class="breadcrumb">
	<li><a href="#">${produto.categoria.descricao}</a></li>
	<li><a href="#">${produto.subcategoria.descricao}</a></li>
	<li class="active">${produto.descricao}</li>
</ol>    

<jsp:include page="componentes/produto-info.jsp" />