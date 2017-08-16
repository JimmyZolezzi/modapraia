<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="modal  modal-login" tabindex="-1" role="dialog" id="modalLogin" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog  modal-ln" role="document">
		<div class="modal-content" style="padding: 2em;">
			<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title">Login</h4>
	     	</div>
			<c:set var="paginaCorrente" value="${pageContext.request.servletPath}"/>
	   		<spring:url var="action" value="/efetuar-login?paginaDestino=${paginaCorrente}&paginaErro=${paginaCorrente}" />
			<c:url var="home" value="/" scope="request" />
			<input id="home" type="hidden" value="${home}"/>  	
			<br/>
			<form method="post" id="form-modal-login" class="form-horizontal"  action="${action}">
				  <div class="form-group">
				    <label for="email" class="col-sm-2 control-label">Email</label>
				    <div class="col-sm-10">
				    	<input type="text" name="email" class="form-control"  placeholder="E-mail"> 
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="senha" class="col-sm-2 control-label">Senha</label>
				    <div class="col-sm-10">
				       <input type="password" name="senha" class="form-control"  placeholder="Senha">
					   <c:if test="${status.error}">
				    	 <label class="control-label" for="email"><form:errors path="senha"/></label>
				       </c:if>
				    </div>
				  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <div class="checkbox">
			          <label><input type="checkbox" id="remember-me" name="remember-me"> Lembrar-me</label>  
			          <label>
			         	<a href="<c:url value="/cadastro-cliente" />">Ainda não sou cadastrado</a>
			          </label>
				      <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			      </div>
			    </div>
			  </div>
			  <div class="col-sm-offset-2 col-sm-10">
			  	 <c:if test="${param.error != null}">
			         <div class="alert alert-danger" role="alert">
						<strong>
							<spring:message code="email.ou.senha.invalido" />		
						</strong>
					</div>
			     </c:if>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">Entrar</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
</div>
<c:if test="${param.error != null}">
<script type="text/javascript">
$('#modalLogin').modal('show')
</script>
</c:if>