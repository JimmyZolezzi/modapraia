<!DOCTYPE html>
<html lang="pt">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title></title>

<!-- Bootstrap Core CSS -->

<link rel="stylesheet" type="text/css" href="<c:url value="/css/theme-novo2.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/vivi-moda-praia.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/zoomImagem.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site/scrollthumbsImage.css" />" >
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/heroic-features.css" />">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="<c:url value="/js/angular.min.js"  />"></script>

<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
</head>

<body ng-app='appProduto' ng-controller='myCtrlProduto' class="textoVivi">
	
	<jsp:include page="header.jsp" />
	<!-- Page Content -->
	<div class="container">
		<jsp:include page="${partial}" />
		<jsp:include page="footer.jsp" />
		<hr>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="<c:url value="/js/jquery.js" />"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>

</body>
</html>
<script>
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
		$scope.carregarCarrinho();
	});


</script>
<script src="<c:url value="/js/site/zoomImagem.js"  />"></script>
<script src="<c:url value="/js/site/scroolThumbsImage.js"  />"></script>