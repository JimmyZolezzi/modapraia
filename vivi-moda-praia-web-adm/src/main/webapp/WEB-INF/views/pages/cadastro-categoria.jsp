<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<!-- Custom CSS -->
<spring:url var="action" value="/categoria/add" />	
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>		
		<h3>Cadastro de Categorias</h3>
		<form:form name="form" id="form" method="post" modelAttribute="categoriaForm"	action="${action}" enctype="multipart/form-data">
			<div id="formulario">
				<spring:bind path="descricao">
					<div class="form-group ${status.error?'has-error':''} ">
						<label for="descricao">Descri&ccedil;&atilde;o</label>
						<form:input path="descricao" type="text" class="form-control"
							id="descricao" placeholder="Descricao" />
						<label class="control-label" for="descricao"><form:errors path="descricao"/></label>	
							
					</div>
				</spring:bind>			
			
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-floppy-disk"> Cadastrar</span>
				</button>
				<br/><br/>
				<div class="alert ${css}">
				  ${msg}
				</div>
			</div>	
			<div class="table-responsive" id="myctrl" ng-app='appCategoria' ng-controller="myCtrlCategoria">
				  <!-- Default panel contents -->
				  	<table  class="table table-hover table-striped">
				  		<thead>
				  			<tr>
				  				<th>Categorias</th>
				  			</tr>
				  		</thead>
				  		<tbody>
						  <tr ng-repeat="categoria in categorias">
						     <td>{{categoria.descricao}}</td>
						     <td align="right">
						     	<button type="button" class="btn btn-warning">Alterar</button>
					     	 	<button type="button" class="btn btn-danger">
							    	<span class="glyphicon glyphicon-floppy-remove"></span>
							    </button>
						     </td>
						  </tr>
				  	</table>
				</div>
			
		</form:form>
	<script type="text/javascript" src="<c:url value="/js/app/cadastro_categoriaJS.js" />">
	</script>


</html>