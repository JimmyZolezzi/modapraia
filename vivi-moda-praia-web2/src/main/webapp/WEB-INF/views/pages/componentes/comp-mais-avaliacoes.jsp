<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:forEach var="avaliacao" items="${avaliacoes}">
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