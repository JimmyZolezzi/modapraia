<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>

 .ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active, a.ui-button:active, .ui-button:active, .ui-state-active.ui-button:hover 
{
  border: 1px solid #E6E1E1;
  background: #E6E1E1;
}
 .ui-autocomplete {
  position: fixed;
}
</style>
<c:url var="home" value="/" scope="request" />
<input id="home" type="hidden" value="${home}"/>
<script>


  
  $(document).ready(function(){
	  
	  function updateTextBox(event,ui){
		  $(this).val(ui.item.descricao);
			return false;
	  }
	  $("#txtBusca").autocomplete({
		minLength: 1,
		focus: updateTextBox,
		select:updateTextBox,
	    source:function( request, response ) {
	    	var pesquisa = document.getElementById('txtBusca').value;
			var parametros = 'pesquisa=' + pesquisa;
			var $home = $('#home').attr('value');
			
			
			xhr = $.ajax({
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
			  valor  =  formataDinheiro(valor);
			  if(produto.imagemProduto1 !== undefined && produto.imagemProduto1 !== null) {
				  imagemProdutoProduto = produto.imagemProduto1.id;
			  }
		  }
		  var urlLinkProduto = $home + 'produto/' + idProduto;
		  var inner_html = '<div><a href="'+ urlLinkProduto +'"><div class="list_item_container"><div class="image"><img src="'+ $home +'/image?id=' + imagemProdutoProduto + '"></div><div>' + descricaoProduto + '</div><div id class="valorAutocomplete ">' +  '<div> R$ ' + valor +  '</div></div></a></div>';
	        return $( "<li></li>" ).append(inner_html).appendTo(ul);
	    };
	});
  
  	function formataDinheiro(n) {
	  return n.toFixed(2).replace('.', ',').replace(/(\d)(?=(\d{3})+\,)/g, "$1.");
	}

  </script>
  <form class="form-inline pull-xs-right">
  	 <div id="divBusca" class="form-group has-feedback">
	     <input type="text" id="txtBusca" class="form-control" placeholder="Procurar" />
	     <span class="form-control-feedback glyphicon glyphicon-search"></span>
	 </div>
  </form>
<style>

</style>
