<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url var="firstUrl" value="/meus-pedidos/pages/1" />
<c:url var="lastUrl" value="/meus-pedidos/pages/${produtoPage.totalPages}" />
<c:url var="prevUrl" value="/meus-pedidos/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/meus-pedidos/pages/${currentIndex + 1}" />
<h3>Meus Pedidos</h3>
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
            <c:url var="pageUrl" value="/meus-pedidos/pages/${i}" />
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
            <c:when test="${currentIndex == page.totalPages}">
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
<div class="table-responsive">
 <!-- Default panel contents -->
 	<table  class="table table-hover table-striped">
 		<thead>
 			<tr>
 				<th>Numero</th>
 				<th>Previsão Entrega</th>
 				<th>Data Entrega</th>
 				<th>Valor</th>
 				<th>Status</th>
 				<th></th>
 			</tr>
 		</thead>
 		<tbody>
  		 <c:forEach var="pedido" items="${page.content}">
		  <tr>
		     <td>
		     	${pedido.id}
		     </td>
		     <td></td>
		     <td></td>
		     <td><fmt:formatNumber value="${pedido.valorTotal}" type="currency"/></td>
		     <td><spring:message code="${pedido.statusPedido}" /></td>
			 <td align="right">
				  <a href="<c:url value="/meus-pedido/${pedido.id }" />" class="btn btn-primary" >Visualizar</a>
		      </td>
		  </tr>
  		 </c:forEach>
 		 </tbody>	
 	</table>
 </div>	
 	
 	



