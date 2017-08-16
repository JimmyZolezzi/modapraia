<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="app" ng-app='appProduto' ng-controller="myCtrlProduto">
	<spring:url var="action" value="/produto/add" />
	<c:url var="idCategoriaSelecionada" value="{{idCategoriaSelecionada}}" scope="request" />
	<form:form method="post" id="form" modelAttribute="produtoForm" action="${action}" enctype="multipart/form-data">
	<div id="formulario">
			<form:hidden path="aplicarDesconto"/>
			<form:hidden path="disponivelEstoque"/>
			<input id="actionOld" type="hidden" value="" />
			<h3>Cadastro de Produtos</h3>
			<form:hidden path="id" />
			<div class="form-group ">
				<label for="terDestaque">Destaque</label>
				<form:checkbox path="terDestaque" />
			</div>
			<spring:bind path="descricao">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="descricao">Descri&ccedil;&atilde;o</label>
					<form:input  path="descricao" type="text"  class="form-control" id="descricao" placeholder="Descrição" value="${produtoForm.descricao}" />
					<label class="control-label" for="descricao"><form:errors path="descricao"/></label>
				</div>
			</spring:bind>
			<spring:bind path="informacoes">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="informacoes">Informa&ccedil;&otilde;es</label>
					<form:textarea path="informacoes" class="form-control" rows="5" />
					<label class="control-label" for="informacoes"><form:errors path="informacoes"/></label>
				</div>
			</spring:bind>
			<spring:bind path="categoria">
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="categoria">Categoria</label>
	                <form:select path="categoria"  id="categoria" class="form-control"  onchange="categoriaOnChange2()">
	        			<option value=''>selecionar</option>
	        			<form:options  items="${categorias}" itemLabel="descricao" itemValue="id"  />
	   				</form:select>
		   			<label class="control-label" for="categoria"><form:errors path="categoria"/></label>
				</div>
			</spring:bind>
			<spring:bind path="subcategoria">	
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="subcategoria">Subcategoria</label>
		                <form:select path="subcategoria" id="subcategoria"  class="form-control"  >
		               		<form:option  value="">Selecionar</form:option>
			       			<c:forEach items="${subcategorias}" var="subcategoria">
		       					<option value="${subcategoria.id}"  ${subcategoria.id==produtoForm.subcategoria.id?'selected':'' } >${subcategoria.descricao}</option>
			       			</c:forEach>
		   			 	</form:select>
		   			<label class="control-label" for="subcategoria"><form:errors path="subcategoria"/></label>
				</div>
			</spring:bind>
			<c:if test="${produtoForm.id == 0}">
				<div class="panel panel-default" id="pecas">
					<div class="panel-heading">
						<h3 class="panel-title" ><strong>Quantidade de pe&ccedil;as</strong></h3>
					</div>
				 	<div class="panel-body">
							<label>Quantidade de pe&ccedil;as</label>
							<form:input id="quantidadeItemProduto" path="quantidade"  type="number" class="text-left camposFormulario" min="1" max="5" onchange="mudarQuantidadeItemProduto()" />
								<c:forEach var="itemProduto" items="${produtoForm.itensProduto}" varStatus="status">
									<div class="form-group">
										<label>Nome da pe&ccedil;a</label>
										<input type="text" class="form-control"  name="itensProduto[${status.index}].nome" value="${itemProduto.nome}"/>
									</div>
									<div class="form-group">
										<label>Medida</label>
										<form:select path="itensProduto[${status.index}].tipoMedida" class="form-control">
											<form:options   items="${medida}" />
										</form:select>
									</div>
								</c:forEach>
				  	</div>	
				</div>
			</c:if>
			<c:if test="${produtoForm.id != 0}">
				<div class="panel panel-default" id="pecas">
					<div class="panel-heading">
						<h3 class="panel-title" ><strong>Quantidade de pe&ccedil;as</strong></h3>
					</div>
				 	<div class="panel-body">
							<label>Quantidade de pe&ccedil;as:</label>
							${produtoForm.itensProduto.size()}
								<c:forEach var="itemProduto" items="${produtoForm.itensProduto}" varStatus="status">
									<div class="form-group">
										<label>Nome da pe&ccedil;a</label>
										${itemProduto.nome}
									</div>
									<div class="form-group">
										<label>Medida</label>
										${itemProduto.tipoMedida}
									</div>
								</c:forEach>
				  	</div>	
				</div>
			</c:if>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" ><strong>Medidas</strong></h3>
				</div>
				<div class="panel-body">
					<spring:bind path="larguraStr">
						<div class="form-group ${status.error?'has-error':''} ">
							<label>Largura (cm)</label>
							<form:input  path="larguraStr" type="text" onkeyup="formataValor(this)" class="form-control" id="larguraStr" placeholder="Largura" />
							<label class="control-label" for="larguraStr"><form:errors path="larguraStr"/></label>
						</div>
					</spring:bind>
					<spring:bind path="alturaStr">
						<div class="form-group ${status.error?'has-error':''} ">
							<label>Altura (cm)</label>
							<form:input  path="alturaStr" type="text" onkeyup="formataValor(this)" class="form-control" id="alturaStr" placeholder="Altura" />
							<label class="control-label" for="alturaStr"><form:errors path="alturaStr"/></label>
						</div>
					</spring:bind>
					<spring:bind path="comprimentoStr">
						<div class="form-group ${status.error?'has-error':''} ">
							<label>Comprimento (cm)</label>
							<form:input  path="comprimentoStr" type="text" onkeyup="formataValor(this)"  class="form-control" id="comprimentoStr" placeholder="Comprimento" />
							<label class="control-label" for="comprimentoStr"><form:errors path="comprimentoStr"/></label>
						</div>
					</spring:bind>
					<spring:bind path="pesoStr">
						<div class="form-group ${status.error?'has-error':''} ">
							<label>Peso (Kg)</label>
							<form:input  path="pesoStr" type="text" onkeyup="formataValor(this)" class="form-control" id="pesoStr" placeholder="Peso" />
							<label class="control-label" for="pesoStr"><form:errors path="pesoStr"/></label>
						</div>
					</spring:bind>
				</div>
			</div>
			<div class="form-group ">
				<label>Foto 1</label>
				<c:if test="${produtoForm !=null && produtoForm.imagemProduto1 != null && produtoForm.imagemProduto1.id !=0}">
					<img src='<c:url value="/image?id=${produtoForm.imagemProduto1.id}&tamanhoImagem=medio"/>'/>
				</c:if>
				<c:if test="${produtoForm ==null || produtoForm.imagemProduto1 == null || produtoForm.imagemProduto1.id ==0}">
					<form:input path="foto1" type="file" name="foto1" id="foto1"/> 
				</c:if>
			</div>
			<div class="form-group  ">
				<label>Foto 2</label>
				<c:if test="${produtoForm !=null && produtoForm.imagemProduto2 != null && produtoForm.imagemProduto2.id !=0}">
					<img src="<c:url value="/image?id=${produtoForm.imagemProduto2.id}&tamanhoImagem=medio"/>"/>
				</c:if>
				<c:if test="${produtoForm ==null || produtoForm.imagemProduto2 == null || produtoForm.imagemProduto2.id ==0}">
					<form:input type="file" path="foto2" id="foto2"/>  
				</c:if>
			</div>
			<spring:bind path="valorStr">	
				<div class="form-group ${status.error?'has-error':''} ">
					<label class="sr-only" for="valor">Valor (em
						reais)</label>
					<div class="input-group">
						<div class="input-group-addon">R$</div>
						<form:input path="valorStr" type="text" class="form-control" onchange="calcularValorComPercentualDesconto()" onkeyup="formataValor(this)" id="valorStr" placeholder="Valor" />
					</div>
					<label class="control-label" for="valorStr"><form:errors path="valorStr"/></label>
				</div>
			</spring:bind>
			<div class="form-group ">
				<label for="aplicarDesconto">Aplicar Desconto</label>
				<form:checkbox path="terDesconto" />
			</div>
			<spring:bind path="descontoPercentualStr">	
				<div class="form-group ${status.error?'has-error':''} ">
					<label for="descontoPercentualStr">Percentual Desconto</label>
					<div class="input-group">
						<form:input path="descontoPercentualStr" onchange="calcularValorComPercentualDesconto()" type="text" class="form-control" onkeyup="formataValorPercentual(this)" id="descontoPercentualStr" placeholder="Desconto %" />
						<div class="input-group-addon">%</div>
					</div>
					<label class="control-label" for="descontoPercentualStr"><form:errors path="descontoPercentualStr"/></label>
				</div>
			</spring:bind>
				<div class="form-group">
					<label for="descontoPercentualStr">Valor com desconto</label>
					<div id="valorDesconto" class="input-group">
						<fmt:formatNumber value="${produtoForm.descontoValor}" type="currency"/>
					</div>
					<label class="control-label" for="descontoPercentualStr"><form:errors path="descontoPercentualStr"/></label>
				</div>
			<c:if test="${not empty css}">
				<br/>
				<div class="alert ${css}">
				 	${msg}
				</div>
			</c:if>
			<button id="botaoFormulario" type="submit"  class="btn btn-primary">Cadastrar</button>
	</div>	
	</form:form>
</div>
<script>

function mudarQuantidadeItemProduto(){
	var $form = $("#form");
	var $home = $('#home').attr('value');
	var url = $home + "produto/addItem";
	var actionAtual = $form.attr('action');	
	$("#actionOld").attr('value',actionAtual);
	$form.attr('action',url);
	$("#form").submit();
	
}


$("#form").submit(function(event) {
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#form");
	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData($(this)[0]);
	// fire off the request to /action
	var action = $form.attr('action');
	var botaoFormularioNome = '';
	if(action.match(".*/alterar")){
		botaoFormularioNome = 'Alterar';
	}
	if(action.match(".*/cadastrar")){
		botaoFormularioNome = 'Cadastrar';
	}
	$.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
	    headers : headers,
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	  	processData: false,
	    success: function (returndata) {
	    	var action = $form.attr('action');
	    	if(!action.match(".*addItem")){
		    	$("#conteudoProduto").html(returndata);
		    	$("#form").attr('action', action);
				$("#botaoFormulario").html(botaoFormularioNome);
	    	}else{
	    		var actionOld = $("#actionOld").attr('value');
	    		$("#form").attr('action', actionOld);
	    		$("#pecas").html($(returndata).find("#pecas"));
	    	}
	    	
	    	scroll(0,0);
	    }
    });
});


		
</script>
