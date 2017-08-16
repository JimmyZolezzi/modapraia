<!DOCTYPE html>
<html lang="pt">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<sec:csrfMetaTags />
<title>
		${title}
</title>

<!-- Bootstrap Core CSS -->

<link rel="stylesheet" type="text/css" href="<c:url value="/css/theme-novo2.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/zoomImagem.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/scrollthumbsImage.css" />" >
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/heroic-features.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/login-modal.css" />">
<script src="<c:url value="/js/site/sonic.js" />"></script>
<script src="<c:url value="/js/site/loading.js" />"></script>
<script src="<c:url value="/js/angular.min.js"  />"></script>
<script src="<c:url value="/js/jquery.js" />"></script>
<script src="<c:url value="/js/site/carregarCliente.js" />"></script>
<script src="<c:url value="/js/site/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/jquery.elevatezoom.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/css/site/jquery-ui.css" />">
<c:url var="home" value="/" scope="request" />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/vivi-moda-praia.css" />" >
</head>


<body onload="carregarUsuarioLogado();" ng-app='appProduto' ng-controller='myCtrlProduto' class="textoVivi">
<div id="resultadoLoading" class=""></div> 
<input id="home" type="hidden" value="${home}"/>
	
	<jsp:include page="header.jsp" />
	<!-- Page Content -->
	<div class="container">
		<div id="conteudo">
			<jsp:include page="${partial}" />
		</div>
		<jsp:include page="footer.jsp" />
		<hr>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
	

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/js/site/pesquisaProdutos.js" />"></script>
		
	<jsp:include page="pages/componentes/modal-login.jsp" />
	
</body>

<script type="text/javascript">
	var app = angular.module('appProduto', []);
	app.controller('myCtrlProduto', function($scope, $http) {
		var $home = $('#home').attr('value');
		$scope.carrinhoProdutos;
		$scope.carregarMenuCategoria = function() {
			$http.get($home + "menu/categoria").then(function(response) {
				$scope.categorias = response.data;
				angular.forEach($scope.categorias, function(value, key) {
					var idCategoria = value.id;
					$scope.carregarMenuSubcategoria(idCategoria,value);
				});
				
		});

		};
	
		$scope.carregarMenuSubcategoria = function(idCategoria, categoria) {
			var config = {
				    params: {
				    	idCategoria: idCategoria
				    }
				};
			$http.get($home + "//menu/subcategoria",config).then(function(response) {
				categoria.subcategorias = response.data;
			});
	
		};
		$scope.carregarCarrinho = function() {

			$http.get($home + "/produto/carregar/carrinho").then(function(response) {
				$scope.carrinhoProdutos = response.data.produtosPedido;		
			});
	
		};
		
		$scope.adicionarProdutoCarrinho = function(idProduto, elementoo) {
			var qtdElemento = "qtd_"+ idProduto;
			var quantidade = document.getElementById(qtdElemento).value;
			var config = {
				    params: {
				    	idProduto: idProduto,
				    	quantidade: quantidade
				    }
				};
			$http.get($home + "produto/add/carrinho",config).then(function(response) {
				$scope.carrinhoProdutos = response.data.produtosPedido;		
			
			});

		};
		
		$scope.removerProdutoCarrinho = function(idProduto) {
			var config = {
				    params: {
				    	idProduto: idProduto,
				    }
				};
			$http.get($home + "/produto/remover/carrinho",config).then(function(response) {
				$scope.carrinhoProdutos = response.data.produtosPedido;		
			
			});

		};
		
		$scope.carregarMenuCategoria();
		atualizarCarrinho();
	});

	function atualizarCarrinho(){
		
		var $home = $('#home').attr('value');
		$.ajax({
		    url: $home + 'produto/carregar/pagina/carrinho',
		    type: 'GET',
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#menuCarrinho").html(returndata);
		    }
		  });
	}
	window.addEventListener("resize", onResize);
	// A $( document ).ready() block.
	$(document).ready(function() {
	});
</script>
<script type="text/javascript">
	

$('body').on('click', 'a', function(e) {
	
	e.preventDefault();
	$("div.zoomContainer").remove();
	
	
	var temOnclick = $(this).attr('onclick');
	var hrefObject =  $(this);
	var href = $(hrefObject).attr('href');
	if(temOnclick === undefined && href !== undefined){
		
		$('#resultadoLoading').html(getAjaxLoading());
		$('#resultadoLoading').addClass("loading");
		
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		
		var headers = {};
		headers[csrfHeader] = csrfToken;
		var parametros = 'async=true';
		$.ajax({
		    url: href,
		    type: 'GET',
		    headers: headers,
		    data: parametros,
		    async: true,
		    cache: false,
		    success: function (returndata) {
		    	var title = $(hrefObject).html();
		    	changeUrl(title, href);
		    	$("#conteudo").html(returndata);
		    	
		    	$(".active").removeClass("active");
		    	var li = $(hrefObject).closest('li');
		    	li.addClass('active');
		    	var ulCategoria = $(li).closest('ul');
		    	var liCategoria = $(ulCategoria).closest('li');
		    	liCategoria.addClass('active');
		    	
		    	scroll(0,0);
		    	var style = $('.modal-usuario-logado').attr('style');
		    	if(style !== undefined && style != 'display: none;') {
		    		$('.modal-usuario-logado').modal('toggle');
		    	}
	    },
		  
		}).done(function() {
			$('#resultadoLoading').html("");
			$('#resultadoLoading').removeClass("loading");
	  });
		
	}
});

	
	function changeUrl(title, url) {
	    if (typeof (history.pushState) != "undefined") {
	        var obj = { Title: title, Url: url };
	        history.pushState(obj, obj.Title, obj.Url);
	        if (document.title != title) {
	            document.title = title;
	        }
	    } else {
	        alert("Browser does not support HTML5.");
	    }
	}
	/*menu handler*/
	$(function(){
	  function stripTrailingSlash(str) {
	    if(str.substr(-1) == '/') {
	      return str.substr(0, str.length - 1);
	    }
	    return str;
	  }

	  var url = window.location.pathname;  
	  var activePage = stripTrailingSlash(url);

	  $('.nav li a').each(function(){  
	    var currentPage = stripTrailingSlash($(this).attr('href'));

	    if (activePage == currentPage) {
	      $(this).parent().addClass('active'); 
	    } 
	  });
	});
	</script>
</html>
