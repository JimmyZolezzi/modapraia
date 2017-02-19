<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Navigation -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div id="wrapper">

	<nav id="navmenu" class="fontePadrao navbar navbar-inverse navbar-fixed-top"
		role="navigation">
	<div class="container">


		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"> </span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="#"><strong>Sun Vivi
					&nbsp;&nbsp;</stron></a>
		</div>



		<style type="text/css">
			.semfoco a:focus{
				color:black !important;
				background-color: white !important;
			}
		
		</style>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul  class="nav navbar-nav">
				<li  ng-class="dropdown"><a  class="dropdown-toggle glyphicon glyphicon-shopping-cart"data-toggle="dropdown">
					{{carrinhoProdutos.length}}<i class="fa fa-envelope"></i> <b class="caret"></b></a>
					<ul class="dropdown-menu message-dropdown" id="carrinho">
						<li  ng-repeat="produtoPedido in carrinhoProdutos">
							<a href="#" >
								<div class="media">
									<span class="item"> 
										<span class="item-left"> 
											<span class="item-info"> 
											  <div>
												<table width="100%">
													<tr>
														<td rowspan="2" width="60px;"  ><img src="<c:url  value="//image?id={{produtoPedido.produto.imagemProduto1.id}}&tamanhoImagem=pequeno"/>"alt=""></td>
													</tr>
													<tr>
														<td colspan="2" style="text-align: left !important;">&nbsp;&nbsp;{{produtoPedido.produto.descricao}}  {{produtoPedido.valorUnitario | currency:'R$'}}</td>
													</tr>
												</table>
											  </div>	
											</span>		
									</span>
								</div>
							</a>
						</li>
					</ul></li>
				<li ng-repeat="categoria in categorias"><a
					href="<c:url value="/produtos/{{categoria.id}}" />"
					ng-if="categoria.subcategorias.length==0">{{categoria.descricao}}</a>
					<a href="<c:url value="/produtos/{{categoria.id}}" />"
					ng-if="categoria.subcategorias.length!=0" data-toggle="dropdown"
					aria-expanded="false">{{categoria.descricao}} <b class="caret"
						ng-if="categoria.subcategorias.length!=0"></b></a>
					<ul class="dropdown-menu">
						<li ng-repeat="subcategoria in categoria.subcategorias"><a
							href="<c:url value="/produtos/{{categoria.id}}/{{subcategoria.id}}" />">{{subcategoria.descricao}}</a></li>
					</ul></li>


			</ul>

		</div>
	</div>

	</nav>
</div>