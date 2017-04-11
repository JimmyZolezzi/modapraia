<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="loginmodal-container">
			<div class="row">
			  <div class="col-xs-6 col-md-3">
			    <a href="#" class="thumbnail">
					<img  src="<c:url value="/imgs/LogoSunVibesRedondoPequeno.png" />" >
			    </a>
			  </div>
			  <div class="col-xs-6 col-md-3">
				<h1>Login</h1><br>
			  </div>				
			</div>
		  <form>
			<input type="text" name="user" placeholder="Usuário">
			<input type="password" name="pass" placeholder="Senha">
			<button type="submit" class="btn btn-primary glyphicon glyphicon-log-in "> Acessar</button>
		  </form>
		  <br/>	
		  <div class="login-help">
			<a href="#">Cadastrar</a> - <a href="#">Esqueci a minha senha</a>
		  </div>
		</div>
	</div>
 </div>