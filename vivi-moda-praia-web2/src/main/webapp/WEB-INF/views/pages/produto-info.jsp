<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<spring:url var="action" value="/info-produto/add/foto" />
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
	<ol class="breadcrumb caminhoPao">
		<li><a href="#">${produto.categoria.descricao}</a></li>
		<li><a href="#">${produto.subcategoria.descricao}</a></li>
		<li class="active">${produto.descricao}</li>
	</ol>

	<div class="media">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title" ><strong>${produto.descricao}</strong></h3>
			</div>
			<div style="padding-left: 1.5em;" class="descricaoProduto">
				<h2>
					<fmt:formatNumber value="${produto.valor}" type="currency"/>
				</h2>
				<br/> 	
					${produto.informacoes}
				<br/>
		    </div>
		 	<div class="panel-body">
				<div class="row">
					<div class="col-md-6" >
						<div>
				  		 	<img class="thumbnail media-object" id="fotoSelecionada" data-toggle="magnify"  width="90%" height="100%" src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=normal" />"  />
							<div class="container">
								<div class="scroller scroller-left"><i class="glyphicon glyphicon-chevron-left"></i></div>
				 				<div class="scroller scroller-right"><i class="glyphicon glyphicon-chevron-right"></i></div>
								<div class="wrapper">
									<ul class="nav nav-tabs list" id="myTab">
										<c:forEach var="imagemProduto" items="${produto.imagensProduto}">
											<li>
												<img width="100%" height="100%" src="<c:url value="/image?id=${imagemProduto.id}&tamanhoImagem=pequeno"/>" onclick="mudarFoto(${imagemProduto.id})"  />
											</li>
										</c:forEach>
									</ul>
								</div>	
							</div>
						</div>
						<div class="row ">
							
						</div>
						<br/>
				  	</div>
				  	<div class="col-md-6">
				  		<div id="formularioProdutoPedido">
					  		<jsp:include page="componentes/form-entrada-produto-carrinho.jsp" />
				  		</div>
					</div>
				</div>
				
		 </div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Avalia&ccedil;&otilde;es</strong></h3>
		</div>
	 	<div class="panel-body">

	  	</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title" ><strong>Tags</strong></h3>
		</div>
	 	<div class="panel-body">

	  	</div>
	</div>
<script src="<c:url value="/js/site/zoomImagem.js"  />"></script>
<script src="<c:url value="/js/site/scroolThumbsImage.js"  />"></script>