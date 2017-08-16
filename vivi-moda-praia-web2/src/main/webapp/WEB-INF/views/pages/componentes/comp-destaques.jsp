<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:url var="firstUrl" value="/produtos/destaque/pages/1" />
<c:url var="lastUrl" value="/produtos/destaque/pages/${produtoPage.totalPages}" />
<c:url var="prevUrl" value="/produtos/destaque/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/produtos/destaque/pages/${currentIndex + 1}" /> 

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
            <c:url var="pageUrl" value="/produtos/destaque/pages/${i}" />
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
<div class="row text-center" >
	<c:forEach var="produto" items="${produtoPage.content}">
		<c:if test="${produto.disponivelEstoque }">
			<div class="col-md-3 col-sm-6 hero-feature">
	               <div class="thumbnail"  ${produto.imagemProduto2!=null? 'onmouseover="trocarFotoPara2('.concat(produto.id).concat(')"').concat(' onmouseout="trocarFotoPara1(').concat(produto.id).concat(')"'):'' }>
	               	<c:if test="${produto.imagemProduto1 != null}">
	                    <img id="img_1_${produto.id}" src="<c:url  value="//image?id=${produto.imagemProduto1.id}&tamanhoImagem=normal"/>" alt="">
	               	</c:if>
	               	<c:if test="${produto.imagemProduto2 != null}">
	                   	<img id="img_2_${produto.id}" src="<c:url value="//image?id=${produto.imagemProduto2.id}&tamanhoImagem=normal"/>" alt="" class="none">
	                   </c:if>
	                   <div class="caption">
	                       <h3>${produto.descricao}</h3>
	                       <p>${produto.informacoes}</p>
	                       	<c:if test="${!produto.aplicarDesconto}">
	                       		<h4>
	                        		<fmt:formatNumber value="${produto.valor}" type="currency"/>
	                       		</h4>
	                       	</c:if>
	                       	<c:if test="${produto.aplicarDesconto}">
	                       		<strike>
	                       			<span class="valorProdutoSemDesconto">
		                        		<fmt:formatNumber value="${produto.valor}" type="currency"/>
	                       			</span>
	                       		</strike>
	                       		<span class="percentualDesconto">
	                       			<strong>
	                        		 	&nbsp;(<fmt:formatNumber  value="${produto.descontoPercentual}"/>% de desconto)
	                       			</strong>
	                       		</span>
	                        	<h4>
		                        	<fmt:formatNumber value="${produto.descontoValor}" type="currency"/>
	                        	</h4>
	                       	</c:if>
	                       <p>
	                           <a href="<c:url value="/produto/${produto.id}" />" class="btn btn-default">Comprar</a>
	                       </p>
	                   </div>
	               </div>
	           </div>
		</c:if>
	</c:forEach>
</div>