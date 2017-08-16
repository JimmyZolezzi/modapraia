<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Navigation -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
	<nav id="navmenu"  style="border-bottom: 2px #1491D4 solid;"  class="fontePadrao navbar  navbar-inverse navbar-fixed-top navbar-dark" role="navigation">
	<div class="container" >
		<div class="navbar-header" >
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"> </span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<button type="button" onclick="aparecerOuEsconderPesquisa();" class="glyphicon glyphicon-search navbar-toggle">
			</button>
			<sec:authorize access="!hasRole('CLIENTE')">
				<button type="button" class="glyphicon glyphicon-log-in navbar-toggle" data-toggle="modal" data-target=".modal-login"></button>
			</sec:authorize>
			<sec:authorize access="hasRole('CLIENTE')">	
				<button type="button" class="glyphicon glyphicon-user navbar-toggle" data-toggle="modal" data-target=".modal-usuario-logado" >
				</button>
			</sec:authorize>	
			<a class="navbar-brand" href="#">
				<img src="<c:url value="/imgs/LogoSunVibesNormalBrancoAzulStroke.png"  />"/>
			</a>
			<div style="padding-top: 1.1em;padding-left: 1em;">
			</div>
		</div>
		<div id="pesquisaComponente" class="pesquisaMobile none">
			<jsp:include page="pages/componentes/pesquisa-produtos-mobile.jsp" />	
		</div>
		<style type="text/css">
			.semfoco a:focus{
				color:black !important;
				background-color: white !important;
			}
		
		</style>
		
		<div class="collapse navbar-collapse fonteMenu"
			id="bs-example-navbar-collapse-1">
			<ul id="nav-menu-vivi"  class="nav navbar-nav menuCarrinho2">
				<li>
					<a class="glyphicon glyphicon-home" href="<c:url value="/home" />"></a>
				</li>
				<li id="menuCarrinho" ng-class="dropdown ">
					
				</li>
				<c:if test="${not empty categoria }">
					<li  ng-repeat="categoria in categorias" class="{{categoria.id == ${categoria.id } ? 'active':''}}">
				</c:if>
				<c:if test="${empty categoria }">
					<li ng-repeat="categoria in categorias">
				</c:if>	
					<a  href="<c:url value="/produtos/{{categoria.id}}" />"	ng-if="categoria.subcategorias.length==0">{{categoria.descricao}}</a>
					<a class="link-menu"  ng-if="categoria.subcategorias.length!=0" data-toggle="dropdown"	aria-expanded="false">
						{{categoria.descricao}} 
						<b class="caret" ng-if="categoria.subcategorias.length!=0"></b>
					</a>
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
			<div id="pesquisaComponenteDesktop" class="pesquisaDesktop" style="float: right;margin-top: 1em;">
				<jsp:include page="pages/componentes/pesquisa-produtos.jsp" />	
			</div>
			
			<sec:authorize access="!hasRole('CLIENTE')">	
				<button type="button" class="glyphicon glyphicon-log-in botaoUsuario navbar-toggle" data-toggle="modal" data-target=".modal-login">
				</button>	
			</sec:authorize>	
			<sec:authorize access="hasRole('CLIENTE')">
				<button type="button" data-toggle="modal" data-target=".modal-usuario-logado" class="dropdown-toggle glyphicon glyphicon-user botaoUsuario navbar-toggle ">
				</button>
			</sec:authorize>	
		</div>
	</div>
</nav>
</div>
<!-- Small modal -->
<sec:authorize access="hasRole('CLIENTE')">
	<div  class="modal  modal-usuario-logado" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	  <div class="modal-dialog modal-sm" role="document">
	    <div class="modal-content">
	    	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title"><span id="nomeCliente" class="glyphicon glyphicon-user"></span>
	        	</h4>
	     	</div>
	     	<br/>
	      	<ul style="list-style-type:none;">
	   		   <li>
	           	<a href="<c:url value="/dados-pessoais" />"><i class="fa fa-fw fa-user"></i> Dados Pessoais</a>
	           </li>
	           <li>
	              <a href="<c:url value="/meus-pedidos" />"><i class="fa fa-fw fa-envelope"></i>Meus Pedidos</a>
	           </li>
	           <li class="divider"></li>
	           <li>
	               <a href="<c:url value="/logout" />"><i class="fa fa-fw fa-power-off"></i> Sair</a>
	           </li>
	       	</ul>
	    </div>
	  </div>
	</div>
</sec:authorize>
