<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<c:url var="firstUrl" value="/estoque-produtos/pages/1" />
	<c:url var="lastUrl" value="/estoque-produtos/pages/${produtoPage.totalPages}" />
	<c:url var="prevUrl" value="/estoque-produtos/pages/${currentIndex - 1}" />
	<c:url var="nextUrl" value="/estoque-produtos/pages/${currentIndex + 1}" />
	
	<nav aria-label="...">
	  <ul class="pagination">
	    <c:choose>
	    	<c:when test="${currentIndex == 1}">
			    <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
	    	</c:when>
    	 	<c:otherwise>
			    <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
    	    </c:otherwise>
    	 </c:choose>
    	    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
	            <c:url var="pageUrl" value="/estoque-produtos/pages/${i}" />
	            <c:choose>
	                <c:when test="${i == currentIndex}">
	                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
	                </c:when>
	                <c:otherwise>
	                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	        <c:choose>
	            <c:when test="${currentIndex == produtoPage.totalPages}">
	                <li class="disabled"><a href="#">&gt;</a></li>
	                <li class="disabled"><a href="#">&gt;&gt;</a></li>
	            </c:when>
	            <c:otherwise>
	                <li><a href="${nextUrl}">&gt;</a></li>
	                <li><a href="${lastUrl}">&gt;&gt;</a></li>
	            </c:otherwise>
	        </c:choose>
	  </ul>
	</nav>
	
	<div id="listaProdutos">
		<div class="table-responsive">
		  <!-- Default panel contents -->
		  	<table  class="table table-hover table-striped">
		  		<thead>
		  			<tr>
		  				<th>Imagem</th>
		  				<th>Descri&ccedil;&atilde;o</th>
		  				<th>Categoria</th>
		  				<th>...</th>
		  			</tr>
		  		</thead>
		  		<tbody>
			  		 <c:forEach var="produto" items="${produtoPage.content}">
					  <tr>
					     <td>
					     	<img src="<c:url value="/image?id=${produto.imagemProduto1.id}&tamanhoImagem=pequeno"/>" />
					     </td>
					     <td>${produto.descricao}</td>
					     <td>${produto.categoria.descricao}</td>
					     <td>
					     	<a href="<c:url value="/estoque-produtos/${produto.id}" />" class="btn btn-primary">Estoque</a>
					     </td>
					  </tr>
			  		 </c:forEach>
		  		 </tbody>	
		  	</table>
		</div>
	</div>
</html>