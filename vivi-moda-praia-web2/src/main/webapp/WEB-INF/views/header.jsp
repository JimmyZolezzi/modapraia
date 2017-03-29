<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Navigation -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.navbar-brand {
  padding: 0px;
}
.navbar-brand>img {
  height: 100%;
  padding: 5px;
  width: auto;
}


</style>
<div id="wrapper">

	<nav id="navmenu"  class="fontePadrao navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container" >


		<div class="navbar-header" >
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"> </span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="#">
				<img src="<c:url value="/imgs/LogoSunVibesNormalBrancoAzulStroke.png"  />"/>
			</a>
			<div style="padding-top: 1.1em;padding-left: 1em;">
			</div>
		</div>



		<style type="text/css">
			.semfoco a:focus{
				color:black !important;
				background-color: white !important;
			}
		
		</style>
		<div class="collapse navbar-collapse fonteMenu"
			id="bs-example-navbar-collapse-1">
			<ul  class="nav navbar-nav">
				<li id="menuCarrinho" ng-class="dropdown ">
					
				</li>
				<c:if test="${not empty categoria }">
					<li  ng-repeat="categoria in categorias" class="{{categoria.id == ${categoria.id } ? 'active':''}}">
				</c:if>
				<c:if test="${empty categoria }">
					<li ng-repeat="categoria in categorias">
				</c:if>	
					<a	href="<c:url value="/produtos/{{categoria.id}}" />"
					ng-if="categoria.subcategorias.length==0">{{categoria.descricao}}</a>
					<a href="<c:url value="/produtos/{{categoria.id}}" />"
					ng-if="categoria.subcategorias.length!=0" data-toggle="dropdown"
					aria-expanded="false">{{categoria.descricao}} <b class="caret"
						ng-if="categoria.subcategorias.length!=0"></b></a>
						<ul class="dropdown-menu">
						<c:if test="${not empty subcategoria }">
							<li class="{{subcategoria.id == ${subcategoria.id } ? 'active':''}}" ng-repeat="subcategoria in categoria.subcategorias">
						</c:if>	
						<c:if test="${empty subcategoria }">
							<li ng-repeat="subcategoria in categoria.subcategorias">
						</c:if>	
							<a href="<c:url value="/produtos/{{categoria.id}}/{{subcategoria.id}}" />">{{subcategoria.descricao}}</a></li>
						</ul>
					</li>
			</ul>

		</div>
		<jsp:include page="pages/componentes/pesquisa-produtos.jsp" />
	</div>

	</nav>
</div>