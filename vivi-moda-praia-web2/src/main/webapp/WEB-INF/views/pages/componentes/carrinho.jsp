<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<a  class="dropdown-toggle glyphicon glyphicon-shopping-cart"data-toggle="dropdown">
	 ${pedido.produtosPedido.size()}
	<i class="fa fa-envelope"></i> <b class="caret"></b></a>
	<ul class="dropdown-menu message-dropdown " id="carrinho">
		<c:forEach var="produtoPedido" items="${pedido.produtosPedido}">
			<li class="active">
				<a href="<c:url value="/pedido-carrinho" />" >
					<div class="media">
						<span class="item"> 
							<span class="item-left"> 
								<span class="item-info"> 
								  <div>
									<table width="100%">
										<tr>
											<td rowspan="2" width="60px;"  ><img src="<c:url  value="/image?id=${produtoPedido.produto.imagemProduto1.id}&tamanhoImagem=pequeno"/>"alt=""></td>
										</tr>
										<tr>
											<td colspan="2" style="text-align: left !important;">&nbsp;&nbsp;${produtoPedido.produto.descricao}  <fmt:formatNumber value="${produtoPedido.valorUnitario}" type="currency"/></td>
										</tr>
									</table>
								  </div>	
								</span>		
						</span>
					</div>
				</a>
			</li>
		</c:forEach>
	</ul>
