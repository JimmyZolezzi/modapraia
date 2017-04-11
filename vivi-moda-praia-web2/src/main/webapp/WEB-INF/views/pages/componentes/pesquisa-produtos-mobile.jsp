<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.ui-autocomplete { z-index:2000 !important; }

</style>
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
<script>


  

  $(document).ready(function(){
	  
	  $("#txtBuscaMobile").autocomplete({
		
	    source:function( request, response ) {
	    	var pesquisa = document.getElementById('txtBuscaMobile').value;
			var parametros = 'pesquisa=' + pesquisa;
			var $home = $('#home').attr('value');
			dataType: "jsonp",
			$.ajax({
				  url: $home + 'pesquisa/produtos',
				  type: 'GET',
				  data: parametros,
				  async: true,
				  cache: false,
				  contentType: false,
				  processData: false,
				  success: function (returndata) {
					  response(returndata);
				  }
			});
	    	
	    }
	    
	  }).data( "ui-autocomplete" )._renderItem = function( ul, produto ) {
		  var $home = $('#home').attr('value'); 
		  var descricaoProduto = ''
		  var imagemProdutoProduto = '';
		  var idProduto = '';
		  var valor = '';
		  if(produto.descricao !== undefined && produto.descricao !== null) {
			  descricaoProduto = produto.descricao;
			  idProduto = produto.id;
			  valor = produto.valor;
			  valor  =  formataDinheiroMobile(valor);
			  if(produto.imagemProduto1 !== undefined && produto.imagemProduto1 !== null) {
				  imagemProdutoProduto = produto.imagemProduto1.id;
			  }
		  }
		  var urlLinkProduto = $home + 'info-produto?idProduto=' + idProduto;
		  var inner_html = '<div id="autocompletePesquisa"><a href="'+ urlLinkProduto +'"><div class="list_item_container"><div class="image"><img src="'+ $home +'/image?id=' + imagemProdutoProduto + '"></div><div>' + descricaoProduto + '</div><div id class="valorAutocomplete">' +  '<div> R$ ' + valor +  '</div></div></a></div>';
	        return $( "<li></li>" )
	            .data( "item.autocomplete", produto )
	            .append(inner_html)
	            .appendTo( ul );
	    };
	});
  
  	function formataDinheiroMobile(n) {
	  return n.toFixed(2).replace('.', ',').replace(/(\d)(?=(\d{3})+\,)/g, "$1.");
	}

  </script>
  <form class="form-inline pull-xs-right">
  	 <div id="divBuscaMobile" class="form-group has-feedback">
	     <input type="text" id="txtBuscaMobile" class="form-control" placeholder="Procurar" />
	     <span class="form-control-feedback glyphicon glyphicon-search"></span>
	 </div>
  </form>
